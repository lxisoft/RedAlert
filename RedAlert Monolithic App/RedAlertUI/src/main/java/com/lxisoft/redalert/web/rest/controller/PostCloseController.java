package com.lxisoft.redalert.web.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.red_alert.api.PostResourceApi;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;
@Controller
@RequestMapping("/postclose")
public class PostCloseController {
	@Autowired
	PostResourceApi postResource;
	
	
	
	@RequestMapping(value="/closepost")
	public String postclosed(@ModelAttribute PostDTO postDTO)
	{
	System.out.println("alertlevel************"+postDTO.getAlertLevel());
		postDTO.getDescription();
		Long id=null;
		PostDTO postDTO1=postResource.getPostUsingGET(id).getBody();
		
	     postResource.getClosePostUsingGET(postDTO.getId());
		return "history";
		
    }
	
	
	
	@RequestMapping(value="/alertchange")
	public String changeAlert()
	{
		return "home";
		
	}
	@RequestMapping(value="/changealert")
	public String getChangeAlertLevel()
	{
	PostDTO postDTO1=postResource.changeAlertLevelUsingGET(null, (long)2).getBody();
	//	postResource.changeAlertLevelUsingGET(alertLevel, id);
		return "";
	}
	
	
	@RequestMapping(value="/history")
	public String postclosed(Model model)
	{
		PostDTO postDTO = postResource.getClosePostUsingGET((long)2).getBody();
		model.addAttribute("closeposts",postDTO);
	
		return "history";
		
    }
}
