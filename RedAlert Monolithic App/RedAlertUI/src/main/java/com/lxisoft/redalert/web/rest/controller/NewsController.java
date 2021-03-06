package com.lxisoft.redalert.web.rest.controller;


import java.util.ArrayList;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxisoft.redalert.client.red_alert.api.*;
import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.*;
import com.lxisoft.redalert.model.NewsFeedViewModel;
import com.lxisoft.redalert.model.NewsFeedViewModels;

@Controller
@RequestMapping("/redAlertUi/newsPage")
public class NewsController {

	@Autowired
	UserRegistrationResourceApi userRegistrationResouceApi;

	
	@Autowired
	MediaResourceApi mediaResouceApi;
	
	@Autowired
	PostResourceApi postResourceApi;

	@Autowired
	ActionResourceApi actionResourceApi;
	@Autowired
	HttpSession session;

	
	@GetMapping("/newsOfFriend")
	public String getNewsOfFriend(Model model, HttpSession session) {
		NewsFeedViewModels newsFeedViews=new NewsFeedViewModels();
		newsFeedViews.setNewsFeedViewList(new ArrayList<NewsFeedViewModel>() );
		UserRegistrationDTO currentUser =   (UserRegistrationDTO) session.getAttribute("cs");

		
		//UserRegistrationDTO userRegistrationDto = userRegistrationResouceApi.getUserRegistrationUsingGET((long)1 )
			//	.getBody();
		

		List<PostDTO> postDtoList = postResourceApi
				.nonClosedPostsOfFriendsUsingGET(currentUser.getId(), null, null, null,
						null, null, null, null, null, null, null)
				.getBody();
		
		
		for(PostDTO post:postDtoList)
		{
		NewsFeedViewModel newsFeedView = new NewsFeedViewModel();
		
		UserRegistrationDTO friend=userRegistrationResouceApi.getUserRegistrationUsingGET(post.getUserRegistrationId()).getBody();
		newsFeedView.setUserRegistrationDTO(friend);
		newsFeedView.setPostDTO(post);
		
		
		
		
		List<ActionDTO> actionDTOList = actionResourceApi.getAllActionsByPostIdUsingGET(post.getId(), null, null,
				null, null, null, null, null, null, null, null).getBody();
		
		newsFeedView.setActionDTOList(actionDTOList);
		
		List<MediaDTO> mediaDTOList=mediaResouceApi.getAllMediaByPostIdUsingGET(post.getId(), null, null,
				null, null, null, null, null, null, null, null).getBody();
		
		
		
		
		
		
		
		newsFeedView.setMediaDTOList(mediaDTOList);
	
		newsFeedView.setNewActionDTO(new ActionDTO());
		
	

		newsFeedViews.getNewsFeedViewList().add(newsFeedView);
		
		
		}
		
	
		model.addAttribute("newsFeedViews", newsFeedViews.getNewsFeedViewList());
		model.addAttribute("newAction", new ActionDTO());
		return "news";
	}

	@PostMapping("/newAction")
	public String newAction(@ModelAttribute ActionDTO  actionDTO) {

	System.out.println("New Actions"+actionDTO);
		actionDTO.setTakenOn(Instant.now());
	actionResourceApi.createActionUsingPOST(actionDTO);
		return "redirect:/redAlertUi/newsPage/newsOfFriend";

	} 

}
