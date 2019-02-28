package com.lxisoft.redalert.web.rest.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lxisoft.redalert.client.crimestopper.api.ComplaintResourceApi;
import com.lxisoft.redalert.client.crimestopper.model.ComplaintDTO;
import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.domain.User;
import com.lxisoft.redalert.repository.UserRepository;
import com.lxisoft.redalert.security.SecurityUtils;

/**
 * @author Vyshnav
 *
 */

@Controller
@RequestMapping("/crimestopper-complaint")
public class CrimeStopperAddComplaintController {
	
	@Autowired
	CSTrendingController trendingController ;
	
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
	
	@Autowired
	
	CrimeStopperHomeController crimeStopperHomeController; 
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ComplaintResourceApi complaintResourceApi;	
	
	@PostMapping("/add")
	public String addComplaint(@ModelAttribute ComplaintDTO complaintDTO, @RequestParam MultipartFile multipartFile,Model model,@RequestParam(value = "url",required=false) String url) {
		
		ComplaintDTO complaintDTOnew = new ComplaintDTO();
		complaintDTOnew.setSubject(complaintDTO.getSubject());				//Set Complaint Subject
		complaintDTOnew.setDescription(complaintDTO.getDescription());		//Set Complaint Descriptiont
		Instant.now();
		System.out.println("#######################################>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> time  "+Instant.now());

		System.out.println("#######################################>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> time  "+ZonedDateTime.now( ZoneId.of( "Asia/Kolkata" )).toInstant());
			//Set Complaint Current Time
		complaintDTOnew.setLocation(complaintDTO.getLocation());            //Set Complaint location
				
		
		//BEGINNING OF SETTING COMPLAINT MEDIA
		try {
			complaintDTOnew.setMedia(multipartFile.getBytes());				//Set Complaint Media
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//ENDING OF SETTING COMPLAINT MEDIA
		

		//BEGINNING OF SETTING COMPLAINT USERID
		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		User user=userRepository.findOneByLogin(currentUserLogin).get();
        UserRegistrationDTO userRegistrationDTO = userRegistrationResourceApi
        		.findByUserIdUsingGET(user.getLogin()).getBody();
		long userId = userRegistrationDTO.getId();
		complaintDTOnew.setUserId(userId);									//Set Complaint UserId
		//ENDING OF SETTING COMPLAINT USERID		
		complaintResourceApi.createComplaintUsingPOST(complaintDTOnew);	
		return crimeStopperHomeController.redirectHome(model,url); 
	}
	
}