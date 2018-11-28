package com.lxisoft.redalert.web.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxisoft.redalert.client.red_alert.api.*;
import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.*;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.model.NewsFeedViewModel;

@Controller
@RequestMapping("/redAlertUi/newsPage")
public class NewsController {

	@Autowired
	UserRegistrationResourceApi userRegistrationResouceApi;
	
	@Autowired
	PostResourceApiClient postResourceApi;
	
	@GetMapping("/dummyData")
	public void getDummyData()
	{
		UserRegistrationDTO user=new UserRegistrationDTO();
		UserRegistrationDTO userdto=userRegistrationResouceApi.getUserRegistrationUsingGET((long)1).getBody();
	    NewsFeedViewModel newsView=new NewsFeedViewModel();
	}
	
	
	
	
	@GetMapping("/newsOfFriend")
	public String getNewsOfFriend(Model model)
	{
		NewsFeedViewModel newsFeedView=new NewsFeedViewModel();
		
		newsFeedView.setUserRegistationDTO(userRegistrationResouceApi.getUserRegistrationUsingGET((long)1).getBody());
		
		
		
		model.addAttribute("newsFeedView",newsFeedView);
		
	return "news";
	}
	
}
