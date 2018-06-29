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
@RequestMapping("/map")
public class MapController extends SpringBootServletInitializer {

    @RequestMapping(value ="/mapDemo")
    public String map1(){
        return "map1";
    }
}
