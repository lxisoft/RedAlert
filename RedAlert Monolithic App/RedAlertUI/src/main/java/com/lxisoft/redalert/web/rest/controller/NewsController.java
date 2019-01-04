package com.lxisoft.redalert.web.rest.controller;

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

@Controller
@RequestMapping("/redAlertUi/newsPage")
public class NewsController {

	@Autowired
	UserRegistrationResourceApi userRegistrationResouceApi;

	@Autowired
	PostResourceApi postResourceApi;

	@Autowired
	ActionResourceApi actionResourceApi;
	@Autowired
	HttpSession session;

	@GetMapping("/dummyData")
	public void getDummyData() {
		UserRegistrationDTO user = new UserRegistrationDTO();
		UserRegistrationDTO userdto = userRegistrationResouceApi.getUserRegistrationUsingGET((long) 1).getBody();
		NewsFeedViewModel newsView = new NewsFeedViewModel();
	}

	@GetMapping("/newsOfFriend")
	public String getNewsOfFriend(Model model) {
		NewsFeedViewModel newsFeedView = new NewsFeedViewModel();
		
		//UserRegistrationDTO userRegistrationDto = userRegistrationResouceApi.getUserRegistrationUsingGET((long)1 )
			//	.getBody();
		UserRegistrationDTO userRegistrationDto = (UserRegistrationDTO) session.getAttribute("cs");
		System.out.println("Userregistration is ******************* " + userRegistrationDto);
		newsFeedView.setUserRegistrationDTO(userRegistrationDto);
		List<PostDTO> postDtoList = postResourceApi
				.getAllPostsByUserRegistrationIdUsingGET(newsFeedView.getUserRegistrationDTO().getId(), null, null, null,
						null, null, null, null, null, null, null)
				.getBody();
		System.out.println("postDtoList is set ******************* " + postDtoList);
		PostDTO postDto = postDtoList.get(postDtoList.size() - 1);
		newsFeedView.setPostDTO(postDto);
		List<ActionDTO> actionDTOList = actionResourceApi.getAllActionsByPostIdUsingGET(postDto.getId(), null, null,
				null, null, null, null, null, null, null, null).getBody();
		newsFeedView.setActionDTOList(actionDTOList);
		System.out.println("actionDtoList is **ee***************** " + actionDTOList);
		/* newsFeedView.setActionDTOList(actionDtoList); */
		newsFeedView.setNewActionDTO(new ActionDTO());
		System.out.println("-------------------------33333333333--------------------- ");

		model.addAttribute("postDTO", postDto);
		model.addAttribute("newsFeedView", newsFeedView);

		return "news";
	}

	@PostMapping("/newAction")
	public String newAction(@ModelAttribute NewsFeedViewModel newsFeedView) {

		System.out.println("NewsFeedView Model****************************************************************"
				+ newsFeedView.toString());
		System.out.println("POst********************************************************************* Model"
				+ newsFeedView.getPostDTO());
		System.out.println(
				"New Action View Model*******************************************" + newsFeedView.getNewActionDTO());

		ActionDTO actionDTO = newsFeedView.getNewActionDTO();
		actionDTO.setPostId(newsFeedView.getPostDTO().getId());
		actionDTO.setTakenOn(Instant.now());
	actionResourceApi.createActionUsingPOST(actionDTO);
		
		/*
		 * System.out.println("NewsFeedView Model"+newsFeedView);
		 * System.out.println("NewsFeedView Model"+newsFeedView);
		 * System.out.println("NewsFeedView Model"+newsFeedView);
		 */
		return "redirect:/redAlertUi/newsPage/newsOfFriend";

	} 

}
