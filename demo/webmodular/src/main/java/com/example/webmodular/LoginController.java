package com.example.webmodular;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/3/19.
 */
@Controller
@SpringBootApplication
public class LoginController extends SpringBootServletInitializer {

    @RequestMapping(value ="/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value ="/test")
    public String test(){
        return "test";
    }
    @RequestMapping(value ="/")
    public String index(){
        return "index";
    }
}
