package com.example.demo;

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
    public String map1(){
        return "login";
    }

}
