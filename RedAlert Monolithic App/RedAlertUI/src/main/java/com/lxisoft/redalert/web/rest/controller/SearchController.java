package com.lxisoft.redalert.web.rest.controller;

import java.util .List;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;




@Controller
@RequestMapping("/searchcontroller")
public class SearchController {
	@Autowired
	UserRegistrationResourceApi userApi; 
	
	@GetMapping("firstPage")
	public String searchPage(Model model) {
		
		return "Search";
		
	}
	@PostMapping("/submitpage")
	public List<UserRegistrationDTO> printPage(@RequestParam String search,Model model) {
		
		List<UserRegistrationDTO> users=userApi.inputCharacterContainingUsingGET(search,null,null,null,null,null,null,null,null,null,null).getBody();
		model.addAttribute("user",users );
		return users ;
		
	}
	
}
