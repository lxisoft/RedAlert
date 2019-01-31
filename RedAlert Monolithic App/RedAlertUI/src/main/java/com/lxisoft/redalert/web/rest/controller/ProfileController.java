package com.lxisoft.redalert.web.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
//import com.lxisoft.redalert.service.dto.UserRegistrationDTO;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/crime-stopper")
public class ProfileController {
	@Autowired
	UserRegistrationResourceApi userRegistrationApi;
	
	@Autowired
	UserRegistrationDTO usersDto;
	
	@GetMapping(value="/homepage")
	public String homePage(Model model) {
		
		return "Home";
		
	}
	@GetMapping(value="/profilepage")
	public String profilePage(@RequestParam String id,Model model) {
		 
		 usersDto=userRegistrationApi.findByUserIdUsingGET("0").getBody();
		 model.addAttribute("user", usersDto);
		return "profilePage";
		
	}
}
