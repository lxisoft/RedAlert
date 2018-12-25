package com.lxisoft.redalert.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/redAlertUiIndex")
public class UserLoginController {
	
	
	
	
	@RequestMapping("/index")
	public  String test()
	{
		return "index";
	}
	
	

}
