package com.qf.j1902.controller;

import com.qf.j1902.pojo.ShIMing;
import com.qf.j1902.pojo.User;
import com.qf.j1902.service.UserService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 86181 on 2019/5/28.
 */
@Controller

public class YeWuShenHeC {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/authcert",method = RequestMethod.GET)
    public String authcert(Model model){
        List<ShIMing> allShiM = userService.getAllShiM();
        model.addAttribute("allShiM",allShiM);
        return "auth_cert";
    }
    @RequestMapping(value = "/authadv",method = RequestMethod.GET)
    public String authadv(){

        return "auth_adv";
    }
    @RequestMapping(value = "/authproject",method = RequestMethod.GET)
    public String authproject(){

        return "auth_project";
    }
    @RequestMapping(value ="/edit2",method = RequestMethod.GET)
    public String edit(@RequestParam("id") int id, Model model){
        System.out.println(id);
        ShIMing shiMingOne = userService.findShiMingOne(id);
        model.addAttribute("shiMingOne",shiMingOne);
        return "edit2";
    }
    @RequestMapping(value = "/shenhe",method = RequestMethod.POST)
    public String shenhe(@RequestParam("idcard") String idcard,@RequestParam("fail") String fail){
        System.out.println("......"+idcard);
        System.out.println(fail);

        String status="否定";
        if(status.equals(fail)){
            userService.xiugaibyidcard(idcard,"未通过");
            ShIMing oneshiming = userService.findOneshimingByidcard(idcard);
            String truename = oneshiming.getTruename();
            userService.xiuGaiShiM(truename,"未实名");
            userService.delshiming(idcard);
        }else {
            userService.xiugaibyidcard(idcard,"已通过");
        }

        return "redirect:/authcert";
    }
    //发送审核未通过信息
    @RequestMapping(value = "shenhefail",method = RequestMethod.POST)
    @ResponseBody
    public int yxyz(HttpServletRequest request, @RequestParam(defaultValue ="a" ) String exam) {
        String regEx1 = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(exam);
        if (m.matches()) {
            try {
                HtmlEmail htmlEmail = new HtmlEmail();
                htmlEmail.setHostName("smtp.qq.com");
                htmlEmail.setCharset("utf-8");
                htmlEmail.addTo(exam);
                htmlEmail.setFrom("969662816@qq.com", "众筹系统");
                htmlEmail.setAuthentication("969662816@qq.com", "kuyhimgdfkzgbdih");
                htmlEmail.setSubject("众筹系统审核信息");
               // int a = (int) ((Math.random() * 9 + 1) * 100000);
               // System.out.println(a);
                String aa = String.valueOf("请尽快重新提交");
                HttpSession session = request.getSession();
                session.setAttribute("SessionKey.SYS_YX", aa);
                htmlEmail.setMsg("尊贵的会员：您的众筹系统审核信息未通过" + "<h3>" + aa + "</h3>");
                htmlEmail.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }

            return 200;
        } else {
            return 400;
        }
    }
}
