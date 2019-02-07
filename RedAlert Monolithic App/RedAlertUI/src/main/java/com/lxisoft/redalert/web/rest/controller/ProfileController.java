package com.lxisoft.redalert.web.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
//import com.lxisoft.redalert.service.dto.UserRegistrationDTO;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.domain.User;
import com.lxisoft.redalert.security.SecurityUtils;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/crime-stopper")
public class ProfileController {
	@Autowired
	UserRegistrationResourceApi userRegistrationApi;
	
	
	UserRegistrationDTO user=new UserRegistrationDTO();
	
	@GetMapping(value="/homepage")
	public String homePage(Model model) {
		//model.addAttribute("user",new UserRegistrationDTO());
	
		
		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		//System.out.println(currentUserLogin);
		
		user=userRegistrationApi.findByUserIdUsingGET(currentUserLogin).getBody();
		//User user=findOneByLogin(currentUserLogin).get();
		model.addAttribute("result", user);

		return "profilePage";
		
	}
	
	/*@PostMapping(value="/post")
	public String profilePage(@ModelAttribute UserRegistrationDTO user ,Model model) {
		 System.out.println(user);
		user=userRegistrationApi.findByUserIdUsingGET(user.getUserId()).getBody();
		 model.addAttribute("result", user);
		return homePage(model);*/
		
	//}
}
