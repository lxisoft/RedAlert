package com.lxisoft.redalert.web.rest.controller;


import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;

@Controller
@RequestMapping("/redAlertUiFriends")
public class FriendsController 
{
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
	
	@GetMapping
	public String friendsPageRedirect()
	{
		return "friends";
	}
	@GetMapping("/find")
	public String findFriend(@RequestParam String searchTerm,Model model)
	{
		Set<UserRegistrationDTO> users=new HashSet<>();
		UserRegistrationDTO user=userRegistrationResourceApi.searchWithUserNameUsingGET(searchTerm).getBody();
		if(user!=null)
			users.add(user);
		userRegistrationResourceApi.searchWithLastNameUsingGET(searchTerm, null, null, null, null, null, null, null, null, null, null).getBody().forEach(users::add);
		userRegistrationResourceApi.searchWithFirstNameLastNameEmailUsingGET(searchTerm, null, null, null, null, null, null, null, null, null, null).getBody().forEach(users::add);
		userRegistrationResourceApi.inputStartingCharacterUsingGET(searchTerm, null, null, null, null, null, null, null, null, null, null).getBody().forEach(users::add);
		model.addAttribute("searchResult", users);
		System.out.println(users);
		return "friends";
	}
}
