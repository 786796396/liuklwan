package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//表明这是一个 Controller
@Controller
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {
	//设置访问的url
	@RequestMapping("/")
	//表示返回JSON格式的结果，如果前面使用的是@RestController可以不用写
	@ResponseBody
	String home() {
		return "Hello World!";//返回结果为字符串
	}

	@RequestMapping(value ="/demo")
	@ResponseBody
	public String demo(){
		return "demo";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
