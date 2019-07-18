package com.qf.j1902.controller;

import com.qf.j1902.pojo.Perms;
import com.qf.j1902.pojo.User;
import com.qf.j1902.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 86181 on 2019/5/28.
 */
@Controller

@SessionAttributes("other")

public class QuanXianController {
    @Autowired
    private UserService userService;

    @RequestMapping("/unauth")
    public String showUnauthView(){
        return "unauth";
    }
    @RequestMapping(value = "/assignPermission",method =RequestMethod.GET)
    public String assignPermission(Model model,@RequestParam("uid") int id){
        System.out.println(id);
        List<String> nameByid = userService.findNameByid(id);
        Object[] objects = nameByid.toArray();

        for (int i=0;i<1;i++){
            System.out.println(objects[0]);
        }
        String name=(String) objects[0];
        model.addAttribute("name",name);
        model.addAttribute("id",id);
        return "assignPermission";
    }

    @RequestMapping(value = "/yongHuWeiHu")
    public String yongHuWeiHu(Model model,HttpSession session){
        List<User> all = userService.findAll();
        model.addAttribute("all",all);
        //session.setAttribute("all",all);

        return "user";
    }
    @RequestMapping(value = "/yongHuWeiHu2",method = RequestMethod.GET)
    public String yongHuWeiHu2(Model model, HttpSession session,@RequestParam("id") int id){
        userService.delUser(id);
        List<User> all = userService.findAll();
        model.addAttribute("all",all);
       // session.setAttribute("all",all);

        return "user";
    }
    @RequestMapping(value = "/chaXun",method = RequestMethod.POST)
    public String yongHuWeiHu3(Model model, HttpSession session,@RequestParam("name") String name){
        User oneByName = userService.findOneByName(name);
       // List<User> all = userService.findAll();
        model.addAttribute("all",oneByName);
        //session.setAttribute("all",all);

        return "user";
    }
    @RequestMapping(value = "/yongHuWeiHu3",method = RequestMethod.POST)
    public String yongHuWeiHu(Model model, HttpSession session,@RequestParam("name") String name, @RequestParam("email") String email,@RequestParam("other") String other,@RequestParam("id") int id){

        userService.upUser(name,email,other,id);
        List<User> all = userService.findAll();
        model.addAttribute("all",all);
       // session.setAttribute("all",all);
        return "user";
    }
    @RequestMapping(value ="/edit",method = RequestMethod.GET)
    public String edit(@RequestParam("id") int id,Model model){
        System.out.println(id);
        User one = userService.findOneById(id);
        System.out.println(one);
        model.addAttribute("one",one);
        return "edit";
    }
    @RequestMapping(value ="/jiaoSeWeiHu",method = RequestMethod.GET)
    public String jiaoSeWeiHu(Model model){
        List<Perms> allPerms = userService.findAllPerms();
        model.addAttribute("perms",allPerms);
        return "role";
    }
    @RequestMapping(value ="/jiaoSeWeiHu2",method = RequestMethod.GET)
    public String jiaoSeWeiHu2(Model model,@RequestParam("text") String text,@RequestParam("id") int id,
                              @RequestParam("name") String name){
        System.out.println(text);
        System.out.println(id);
        System.out.println(name);
        if(text!=""){
            userService.xiugaiperm(id,name,text);
            List<Perms> allPerms = userService.findAllPerms();
            model.addAttribute("perms",allPerms);
            return "role";
        }
        return "redirect:jiaoSeWeiHu";
    }
    @RequestMapping(value ="/jiaoSeWeiHu3",method = RequestMethod.GET)
    public String jiaoSeWeiHu3(Model model,@RequestParam("uid") int id){
        System.out.println(id);
        userService.delperm(id);
        List<Perms> allPerms = userService.findAllPerms();
        model.addAttribute("perms",allPerms);
        return "role";
    }
    @RequestMapping(value = "/xuKeWeiHu",method = RequestMethod.GET)
    public String xuKeWeiHu(){

        return "permission";
    }
    @RequestMapping(value = "/assignRole",method = RequestMethod.GET)
    public String assignRole(){

        return "assignRole";
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){

        return "add";
    }
    @RequestMapping(value = "/addquanxianjg",method = RequestMethod.POST)
    public String addquanxianjg(@RequestParam("roleid") int roleid,@RequestParam("username") String username
                                    ,@RequestParam("qxlist") String qxlist){
        System.out.println(roleid);
        System.out.println(username);
        System.out.println(qxlist);
        userService.xiugaiperm(roleid, username, qxlist);
        return "redirect:/jiaoSeWeiHu";
    }
    @RequestMapping(value = "/addquanxian",method = RequestMethod.GET)
    public String addquanxian(){

        return "addquanxian";
    }
    @RequestMapping(value = "/htaddresult",method = RequestMethod.POST)
    public String htaddresult(User user,Model model){

        user.setSort("vip");
        String go;
        if(user.getName()=="" || user.getPassword()=="" || user.getEmail()==""||user.getOther()==""){
                go="failresult";
        }else {
            userService.addUser(user);
            List<User> all = userService.findAll();
            model.addAttribute("all",all);

             go="user";
        }

        return go;
    }
}
