package com.lxisoft.redalert.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/loginController")
public class UserLoginController {
	
	
	
	
	@RequestMapping("/login")
	public  String test()
	{
		return "index";
	}
	
	

}
