package com.micromall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈〉
 *
 * @author LewJay
 * @create 2018/6/19 19:06
 */
@Controller
public class LoginController {

    @RequestMapping("login")
    public String toLogin(){
        return "login";
    }
}
