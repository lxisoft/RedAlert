package com.lxisoft.redalert.web.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexMappingController 
{
	@GetMapping("/")
	public String indexMapper()
	{
		return "redirect:/redAlertUi/index";
	}
	@GetMapping("/signup")
	public String signupMapper()
	{
		return "SignUp";
	}
}

