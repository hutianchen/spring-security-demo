package com.spring.security.test.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @RequestMapping("/admin")
    @ResponseBody
    public String admin() {
        return "{code:0}";
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login( @RequestParam("username") String username,@RequestParam("password") String password) {
        return "{code:0}";
    }


}
