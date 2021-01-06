package com.cjw.eshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cj.w
 * @date 2021/1/6 23:26
 */
@Controller
public class UserController {

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello";
    }
}
