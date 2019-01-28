package com.lxisoft.redalert.web.rest.controller;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxisoft.redalert.client.crimestopper.api.ComplaintResourceApi;
import com.lxisoft.redalert.client.crimestopper.model.ComplaintDTO;

@Controller
@RequestMapping("/crime-stopper")
public class CrimeStopperHomeController {
	
	Logger log = LoggerFactory.getLogger(CrimeStopperHomeController.class);
	@Autowired
	ComplaintResourceApi complaintResourceApi;
	
	
	@GetMapping(value="/friends/complaints")         
	
	public String getFriendsComplaints(Model model)
	{
		//log.debug(" get request for collect all friends complaints with userId:"+userId);
		
		//complaintResourceApi.getAllComplaintsOfFriendsUsingGET(userId, eagerload, offset, page, pageNumber, pageSize, paged, size, sort, sortSorted, sortUnsorted, unpaged);

		//ResponseEntity<List<ComplaintDTO>> result=complaintResourceApi.getAllComplaintsOfFriendsUsingGET(userId, null, null, null, null, null, null, null, null, null, null, null);
		ResponseEntity<List<ComplaintDTO>> result=complaintResourceApi.getAllComplaintsUsingGET(null, null, null, null, null, null, null, null, null, null, null);
		
		HashSet<ComplaintDTO>set=new HashSet<ComplaintDTO>(result.getBody());
		for(ComplaintDTO temp:set)log.debug(temp+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> \n");
		model.addAttribute("complaints",set);
		
		return "create_complaint_cs"; 
	}
	
	

}