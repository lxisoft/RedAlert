package com.lxisoft.redalert.web.rest.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lxisoft.redalert.client.crimestopper.api.ComplaintResourceApi;
import com.lxisoft.redalert.client.crimestopper.api.ElasticSearchResourceApi;
import com.lxisoft.redalert.repository.UserRepository;
import com.lxisoft.redalert.security.SecurityUtils;
import com.lxisoft.redalert.client.crimestopper.model.ComplaintDTOElasticSearch;
import com.lxisoft.redalert.client.crimestopper.model.UserDTOElasticSearch;
import com.lxisoft.redalert.client.crimestopper.model.ComplaintDTO;
import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.domain.User;


/**
 * @author Vyshnav
 *
 */

@Controller
@RequestMapping("/crime-stopper/complaint")
public class CrimeStopperAddComplaintController {
	
	@Autowired
	CSTrendingController trendingController ;
	
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
	@Autowired
	ComplaintResourceApi complaintResourceApi;	
	@Autowired
	ElasticSearchResourceApi elasticSearchResourceApi;	
	@Autowired
	CrimeStopperHomeController crimeStopperHomeController; 
	@Autowired
	UserRepository userRepository;
	
	//POST A COMPLAINT
	@PostMapping("/add")
	public String addComplaint(@ModelAttribute ComplaintDTO complaintDTO, @RequestParam MultipartFile multipartFile,Model model,@RequestParam(value = "url",required=false) String url) {
		
		ComplaintDTO complaintDTOnew = new ComplaintDTO();
		
		//BEGINNING OF SETTING COMPLAINT USERID
		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		User user=userRepository.findOneByLogin(currentUserLogin).get();
        UserRegistrationDTO userRegistrationDTO = userRegistrationResourceApi
        		.findByUserIdUsingGET(user.getLogin()).getBody();
		long userId = userRegistrationDTO.getId();
		complaintDTOnew.setUserId(userId);									//Set Complaint UserId
		//ENDING OF SETTING COMPLAINT USERID
		
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
		

		//BEGINNING OF SETTING COMPLAINT MEDIA
		//complaintDTOnew.setLinkedComplaints(linkedComplaints);			//Set Complaint Linked Complaints
		//ENDING OF SETTING COMPLAINT MEDIA
		

		complaintResourceApi.createComplaintUsingPOST(complaintDTOnew);	
		return crimeStopperHomeController.redirectHome(model,url); 
	}
	
	//GET ALL COMPLAINTS FROM ELASTICSEARCH
	@GetMapping("/getAll")
	public void getComplaints(Model model) {
			
		ResponseEntity<List<ComplaintDTOElasticSearch>> result = elasticSearchResourceApi
				.getAllComplaintsUsingGET(null, null, null, null, null, null, 
						null, null, null, null, null);
		List<ComplaintDTOElasticSearch> complaints = 
				new ArrayList<ComplaintDTOElasticSearch>(result.getBody());
		model.addAttribute("complaints", complaints);
			
		return ; 
	}
	
	//TESTING AUTOCOMPLETE FEATURE USING JQUERY AJAX ELASTICSEARCH
	@PostMapping("/search/description")
	public @ResponseBody
	List<ComplaintDTOElasticSearch> getTags(@RequestBody String searchTerm) {

		ResponseEntity<List<ComplaintDTOElasticSearch>> result = elasticSearchResourceApi
				.searchComplaintsByDescriptionUsingPost(searchTerm);
		List<ComplaintDTOElasticSearch> complaints = 
				new ArrayList<ComplaintDTOElasticSearch>(result.getBody());
	
			
		return complaints; 

	}
	
	//AT MENTION USER
	@RequestMapping(value = "/search/user-by-text-phrase", method = RequestMethod.POST)
	public @ResponseBody
	List<UserDTOElasticSearch> getUsers(@RequestBody String searchTerm) {

		ResponseEntity<List<UserDTOElasticSearch>> result = elasticSearchResourceApi
				.searchUsresByTextPhraseUsingPost(searchTerm);
		List<UserDTOElasticSearch> users = 
				new ArrayList<UserDTOElasticSearch>(result.getBody());
	
			
		return users; 

	}
	
}