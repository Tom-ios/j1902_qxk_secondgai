package com.qf.j1902.controller;

import com.qf.j1902.pojo.ShIMing;
import com.qf.j1902.pojo.User;
import com.qf.j1902.service.UserService;
import com.qf.j1902.utils.ImgCode;
import com.qf.j1902.utils.MD5;
import com.qf.j1902.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by 86181 on 2019/5/27.
 */
@Controller
@SessionAttributes("other")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/dengLu" ,method = RequestMethod.GET)
    public String dengLu(){

        return "login";
    }
    @RequestMapping(value = "/zhuCe" ,method = RequestMethod.GET)
    public String zhuCe(){

        return "reg";
    }
    @RequestMapping(value = "/echartsshow" ,method = RequestMethod.GET)
    public String echartsshow(){

        return "echartsshow";
    }
    @RequestMapping(value = "/echarsshow2" ,method = RequestMethod.GET)
    public String echarsshow2(){

        return "echarsshow2";
    }

   @RequestMapping(value = "/main" ,method = RequestMethod.GET)
   public String main(){

       return "main";
   }

    @RequestMapping(value = "/zhuCeResult", method = RequestMethod.GET)
    public String zhuCe(User user) {
        System.out.println("ok");
        user.setSort("vip");
        user.setRenzheng("未实名");

        if (user.getName() != "" && user.getPassword() != "" && user.getEmail() != "") {
            String md5ofStr = new MD5().getMD5ofStr(user.getPassword());
            /*Md5Hash md5Hash = new Md5Hash("999");
            String s = md5Hash.toString();*/
            user.setPassword(md5ofStr);
            userService.addUser(user);
            return "zhuCeResult";
        }else {
            return "reg";
        }

        }

    @RequestMapping(value = "/panDuan", method = RequestMethod.POST)
    public String main(UserVo userVo, @RequestParam("sort") String sort, Model model,HttpServletRequest request){
        HttpSession session = request.getSession(true);
        String imgcode = (String)session.getAttribute(ImgCode.RANDOMCODEKEY);
       // System.out.println(imgcode);
        //System.out.println(userVo.getPicCode()+"用户输入");
        String name = userVo.getName();
        //System.out.println(name);
        //System.out.println(sort);
        User one = userService.findOneByName(name);
        //System.out.println(one);
        String renzheng = one.getRenzheng();
        String other = one.getOther();
        model.addAttribute("other",other);
        model.addAttribute("zt",renzheng);

        // System.out.println(renzheng);
        if(StringUtils.startsWithIgnoreCase(imgcode,userVo.getPicCode())){
            try {
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(userVo.getName(),userVo.getPassword());
                subject.login(token);
                if(subject.isAuthenticated()){
                    if(one.getSort().equals("admin")){
                        return "main";
                    }else {

                        try {
                            ShIMing oneshiming = userService.findOneshimingBytruename(name);
                           String  auditstate = oneshiming.getAuditstate();
                            model.addAttribute("oneshiming",auditstate);
                        } catch (Exception e){
                            e.printStackTrace();
                        }finally {

                            return "member";
                        }

                    }

                }
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }

        }

        return "login";
    }
    @RequestMapping(value = "/getImg")
    public void getImgCode(HttpServletRequest request ,HttpServletResponse response ){
        ImgCode imgCode = new ImgCode();
        imgCode.getRandcode(request,response);

    }
}