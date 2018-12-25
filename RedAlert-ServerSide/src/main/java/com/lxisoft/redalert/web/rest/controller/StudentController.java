package com.lxisoft.redalert.web.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/controller")
@Controller
public class StudentController {
	
	@RequestMapping("/homelogin")
	public String home(Model model)
	{
		//List<String>list=new ArrayList<>(Arrays.asList("abhina","abhi"));
		String[] array = new String[] {"one","two","three"}; 
		List list = Arrays.asList(array);
            return "index";
	
	}
}
