package com.spring.security.test.demo.controller;

import org.apache.dubbo.spring.boot.demo.consumer.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

//    @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:28080")
    private DemoService demoService;

    @RequestMapping("/admin")
    @ResponseBody
    public String admin() {
        String helloWorld = demoService.sayHello("hello world");
        return "{code:0,message="+helloWorld+"}";
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login( @RequestParam("username") String username,@RequestParam("password") String password) {
        return "{code:0}";
    }

    @ResponseBody
    @RequestMapping("redis")
    public String redis(){
       return null;
    }
}
