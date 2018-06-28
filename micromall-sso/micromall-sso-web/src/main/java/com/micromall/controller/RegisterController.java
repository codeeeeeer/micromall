package com.micromall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈〉
 *
 * @author LewJay
 * @create 2018/6/19 19:07
 */
@Controller
public class RegisterController {

    @RequestMapping("register")
    public String toRegister(){
        return "register";
    }
}
