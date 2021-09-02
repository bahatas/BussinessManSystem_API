package com.cybertek.businessmansystem_api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LoginController {


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @GetMapping("/login")
    public String login(){

        Object o = new Object();

        return "/login";
    }

    @GetMapping("/home")
    public String create(){

        return "/home";
    }


    @GetMapping("/index")
    public String create1(){

        return "/index";
    }

    @GetMapping()
    public String homePage(){

        return "/home";
    }
}
