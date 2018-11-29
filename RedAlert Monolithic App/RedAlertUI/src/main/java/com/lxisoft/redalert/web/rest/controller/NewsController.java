package com.lxisoft.redalert.web.rest.controller;

import java.util.List;

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
	PostResourceApi postResourceApi;
	
	@Autowired
	ActionResourceApi actionResourceApi;
	
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
		UserRegistrationDTO userRegistrationDto=userRegistrationResouceApi.getUserRegistrationUsingGET((long)1).getBody();
	System.out.println("Userregistration is ******************* "+userRegistrationDto);
		newsFeedView.setUserRegistationDTO(userRegistrationDto);
		List<PostDTO> postDtoList=postResourceApi.getAllPostsByUserRegistrationIdUsingGET(newsFeedView.getUserRegistationDTO().getId(), null, null, null, null, null,null, null,null, null, null).getBody();
		System.out.println("postDtoList is set ******************* "+postDtoList);
		PostDTO postDto=postDtoList.get(postDtoList.size()-1);
		newsFeedView.setPostDTO(postDto);
		int actionDtoList=actionResourceApi.getAllActionsByPostIdUsingGET(postDto.getId(), null, null, null, null, null,null, null,null, null, null).getStatusCodeValue();
		System.out.println("actionDtoList is ******************* "+actionDtoList);
		/*newsFeedView.setActionDTOList(actionDtoList);
		newsFeedView.setNewActionDTO(new ActionDTO());*/
		
		
		
		
		model.addAttribute("newsFeedView",newsFeedView);
		
	return "news";
	}
	
}
