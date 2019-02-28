package com.lxisoft.redalert.web.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.crimestopper.api.ComplaintResourceApi;
import com.lxisoft.redalert.client.crimestopper.api.HashtagResourceApi;
import com.lxisoft.redalert.client.crimestopper.model.ComplaintDTO;
import com.lxisoft.redalert.client.crimestopper.model.HashtagDTO;
import com.lxisoft.redalert.client.crimestopper.model.LocationDTO;
import com.lxisoft.redalert.model.CSHashtagView;
import com.lxisoft.redalert.model.CSTrendingView;

 

/**
 * @author SoOrAj Pn
 *
 */

@Controller
@RequestMapping("/crime-stopper")
public class CSTrendingController {
	
	
	
    private final Logger log = LoggerFactory.getLogger(CSTrendingController.class);
	
    
    
    
	@Autowired
	ComplaintResourceApi complaintResourceApi;	
	
	@Autowired
	HashtagResourceApi hashtagResourceApi;
	
	@GetMapping("/trending/HashtagsAndComplaints")
	public String getTrendingHashtagsAndComplaints(Model model,@RequestParam(value = "url",required=false) String url) {
		log.debug("get request to get all trending hashtags and complaints");
		CSTrendingView trendingView=new CSTrendingView();
		ResponseEntity<List<HashtagDTO>> hashtagResult=hashtagResourceApi.getAllTrendingHashtagsUsingGET(null, null, null, null, null, null, null, null, null, null);
		for(HashtagDTO ht:hashtagResult.getBody())
		{
			CSHashtagView hashtagView=new CSHashtagView();
			ResponseEntity<List<ComplaintDTO>> complaintResult=complaintResourceApi.getAllComplaintsHashtagUsingGET(ht.getName(), null, null, null, null, null, null, null, null, null, null, null);
			hashtagView.setId(ht.getId());
			hashtagView.setName(ht.getName());
			hashtagView.setComplaints(new ArrayList<ComplaintDTO>(complaintResult.getBody()));
			trendingView.getHashtags().add(hashtagView);
		}
		
		log.debug("????????????????????????????????????????????????"+trendingView.getHashtags().get(0).getComplaints());
	
		ComplaintDTO dto=new ComplaintDTO();
		
		dto.setLocation(new LocationDTO()); 
		model.addAttribute("complaintDTO",dto);
		model.addAttribute("trendingView",trendingView);
		return "crimestopper-trending";
	}  
	

}