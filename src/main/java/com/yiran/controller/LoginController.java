package com.yiran.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yiran on 17-1-25.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping(value = "/home",method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String signin(){

        return "staffView";
    }
}
