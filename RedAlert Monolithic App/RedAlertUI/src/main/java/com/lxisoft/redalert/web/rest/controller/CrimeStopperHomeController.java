package com.lxisoft.redalert.web.rest.controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxisoft.redalert.client.crimestopper.api.ComplaintResourceApi;
import com.lxisoft.redalert.client.crimestopper.api.UserResponseResourceApi;
import com.lxisoft.redalert.client.crimestopper.model.ComplaintDTO;
import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.domain.User;
import com.lxisoft.redalert.model.ComplaintView;
import com.lxisoft.redalert.model.HomeView;
import com.lxisoft.redalert.repository.UserRepository;
import com.lxisoft.redalert.security.SecurityUtils;

@Controller
@RequestMapping("/crime-stopper")
public class CrimeStopperHomeController {
	
	Logger log = LoggerFactory.getLogger(CrimeStopperHomeController.class);
	@Autowired
	ComplaintResourceApi complaintResourceApi;
	
	@Autowired
	UserResponseResourceApi userResponseResourceApi;
	
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserRepository userRepository;
	        
	
	public String redirectHome(Model model)
	{
		//log.debug(" get request for collect all friends complaints with userId:"+userId);
		
		//complaintResourceApi.getAllComplaintsOfFriendsUsingGET(userId, eagerload, offset, page, pageNumber, pageSize, paged, size, sort, sortSorted, sortUnsorted, unpaged);

		
		
		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		 log.debug("current user login>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+currentUserLogin);
		
		User user=userRepository.findOneByLogin(currentUserLogin).get();
		
		log.debug("user>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+user);
		
		log.debug("VVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+user.getLogin());
        UserRegistrationDTO userRegistrationDTO = userRegistrationResourceApi.findByUserIdUsingGET(user.getLogin()).getBody();
    	log.debug("DTO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+userRegistrationDTO);
		
		ResponseEntity<List<ComplaintDTO>> result=complaintResourceApi.getAllComplaintsOfFriendsUsingGET(userRegistrationDTO.getId(), null, null, null, null, null, null, null, null, null, null, null);
		//ResponseEntity<List<ComplaintDTO>> result=complaintResourceApi.getAllComplaintsUsingGET(null, null, null, null, null, null, null, null, null, null, null);
	
		List<ComplaintDTO>set=new ArrayList<ComplaintDTO>(result.getBody());
		HashSet<ComplaintView>complaints=new HashSet<ComplaintView>();
		HomeView homeView=new HomeView(new ArrayList<ComplaintDTO>());
		
		for(ComplaintDTO temp:set)
		{
			homeView.getComplaints().add(temp);
			for(ComplaintDTO tem:homeView.getComplaints())
			{
				log.debug("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+temp.getUserName());
			} 
		}
		model.addAttribute("homeView",homeView);

		return "create_complaint"; 
	}
	
	@PostMapping(value="/likeComplaint")         
	
	public String likeComplaint(Model model)
	{
		log.debug("mark an user response:");       
		
	
		
		//complaintResourceApi.getAllComplaintsOfFriendsUsingGET(userId, eagerload, offset, page, pageNumber, pageSize, paged, size, sort, sortSorted, sortUnsorted, unpaged);
/*
		ResponseEntity<UserResponseDTO>result=userResponseResourceApi.createUserResponseUsingPOST(userResponse);
		HomeView homeView=new HomeView(new ArrayList<ComplaintDTO>());
		
		
		model.addAttribute("userResponce",result.getBody());*/

		return redirectHome(model); 
	}
	
	@GetMapping(value="/home")
	public String home(Model model)
	{
		
		return redirectHome(model);
	}
	@GetMapping()
	public String getAllComplaints(Model model) {
		
		ResponseEntity<List<ComplaintDTO>> results=complaintResourceApi.getAllComplaintsUsingGET(null, null, null, null, null, null, null, null, null, null, null);
		model.addAttribute("res", results);
		return null;
		
	}
	


}