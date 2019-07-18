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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Qxk on 2019/5/29.
 */
@Controller
@SessionAttributes("other")
public class QianTai {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "accttype",method = RequestMethod.GET)
    public String accttype(){

        return "accttype";
    }
    @RequestMapping(value = "apply",method = RequestMethod.GET)
    public String accttype0(@RequestParam(value = "pictype",defaultValue = "未选择的类型") String stype,Model model){
        System.out.println(stype);
        model.addAttribute("stype",stype);



        return "apply";
    }

    @RequestMapping(value = "/apply1",method = RequestMethod.POST)
    public String accttype1(Model model,@RequestParam("stype") String stype,
                            @RequestParam("truename") String truename,
                            @RequestParam("idcard") String idcard,
                            @RequestParam("phonenum") String phonenum
                           ){
        model.addAttribute("idcard",idcard);
        ShIMing shIMing=new ShIMing(truename,idcard,phonenum,stype);
        if(truename=="" || idcard==""){
            return "apply";
        }else{
           userService.addTrueUser2(shIMing);
            return "apply-1";
       }

   }

    @RequestMapping(value="/apply-2", method=RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile mf, HttpServletRequest request ,Model model,@RequestParam("idcard") String idcard ){
       // System.out.println(shIMing);
        model.addAttribute("idcard",idcard);
        String path = "E:\\Maven\\j1902_qxk_secondgai\\src\\main\\resources\\static\\upload";
        String fileName = mf.getOriginalFilename();
        String Name="upload/"+fileName;
        String location = path +"/"+ fileName;
       // System.out.println(location);
        userService.xiuGaiImg(idcard,Name);
        System.out.println(Name);
        File f = new File(location);

        try {
            f.createNewFile();
            mf.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "apply-2";
    }
    //发送验证码
    @RequestMapping(value = "sendyzm",method = RequestMethod.POST)
    @ResponseBody
    public int yxyz(HttpServletRequest request,@RequestParam(defaultValue ="a" ) String exam) {
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
                htmlEmail.setSubject("实名认证验证码");
                int a = (int) ((Math.random() * 9 + 1) * 100000);
                System.out.println(a);
                String aa = String.valueOf(a);
                HttpSession session = request.getSession();
                session.setAttribute("SessionKey.SYS_YX", aa);
                htmlEmail.setMsg("尊贵的会员：您的验证码为" + "<h3>" + aa + "</h3>");
                htmlEmail.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }

            return 200;
        } else {
            return 400;
        }
    }
    @RequestMapping(value = "apply-3",method = RequestMethod.POST)
    public String accttype3( @RequestParam("idcard") String idcard,@RequestParam("email")
                                                String email, Model model){
        userService.xiugaiemail(idcard,email);
        System.out.println(email);
        System.out.println(idcard);
        model.addAttribute("idcard",idcard);
        return "apply-3";
    }
    @RequestMapping(value = "member",method = RequestMethod.POST)
    public String member(@RequestParam("idcard") String idcard,Model model){
        System.out.println(idcard);
        userService.xiuGai(idcard,"未审核");
        String name = userService.findNameByID(idcard);
        userService.xiuGaiShiM(name,"已实名");
        User one = userService.findOneByName(name);
        String renzheng = one.getRenzheng();
        model.addAttribute("zt",renzheng);
        ShIMing oneshiming = userService.findOneshimingBytruename(name);
        String auditstate = oneshiming.getAuditstate();
        System.out.println(auditstate);
        model.addAttribute("oneshiming",auditstate);

        return "member";
    }
    @RequestMapping(value = "minecrowdfunding",method = RequestMethod.GET)
    public String minecrowdfunding(){

        return "minecrowdfunding";
    }
    @RequestMapping(value = "start",method = RequestMethod.GET)
    public String start(){

        return "start";
    }
    @RequestMapping(value = "start-step-1",method = RequestMethod.GET)
    public String startstep1(){

        return "start-step-1";
    }
    @RequestMapping(value = "start-step-2",method = RequestMethod.GET)
    public String startstep2(){

        return "start-step-2";
    }
    @RequestMapping(value = "start-step-3",method = RequestMethod.GET)
    public String startstep3(){

        return "start-step-3";
    }
    @RequestMapping(value = "start-step-4",method = RequestMethod.GET)
    public String startstep4(){

        return "start-step-4";
    }
}
