package com.lxisoft.redalert.web.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.red_alert.api.PostResourceApi;
import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.MediaDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.model.ImageView;
import com.lxisoft.redalert.model.View;
/**
 * @author AbhinaS
 *
 */

@Controller
@RequestMapping("/postclose")
public class UserActivityController {
	@Autowired
	PostResourceApi postResource;
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
	@Autowired
	PostResourceApi postResourceApi;
	
	
	
	
	/**
	 * @param postDTO
	 * @return
	 */
	@RequestMapping(value="/closepost",method=RequestMethod.GET)
	public String postclosed(@ModelAttribute PostDTO postDTO)
	{
	     
	      postResource.getClosePostUsingGET(postDTO.getId());
	    

		  return "redirect:/redAlertuiHistory/history";
		 
	 }
	
	
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/home")
	public String changeAlerts(Model model)
	{
		
		View view = new View();
		view.setMediaDTO(new MediaDTO());
		view.setPostDTO(new PostDTO());
		view.setUserRegistrationDTO(new UserRegistrationDTO());
        view.setUserRegistrationId(1);
        view.setUserRegistrationDTO(userRegistrationResourceApi.getUserRegistrationUsingGET((long) 1).getBody());
		model.addAttribute("view", view);
		
		return "home";
		
	}
	/**
	 * @param postDTO
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/alertchange")
	public String ChangeAlert(@RequestParam Long postId, String newAlertType)
	{  
		
		postResource.changeAlertLevelUsingGET(newAlertType,postId);
		return "redirect:/redAlertuiHistory/history";
		
	}
	
	
	
	
}
