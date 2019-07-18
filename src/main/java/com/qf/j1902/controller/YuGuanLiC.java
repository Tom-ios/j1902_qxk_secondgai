package com.qf.j1902.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 86181 on 2019/5/28.
 */
@Controller
public class YuGuanLiC {
    @RequestMapping(value = "cert")
    public String cert(){

        return "cert";
    }

}
