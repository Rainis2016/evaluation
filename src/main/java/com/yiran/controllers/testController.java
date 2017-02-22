package com.yiran.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Yiran on 17-1-25.
 */
@Controller
public class testController {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
