package com.lxisoft.redalert.web.rest.controller;

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
import com.lxisoft.redalert.model.View;
@Controller
@RequestMapping("/postclose")
public class PostCloseController {
	@Autowired
	PostResourceApi postResource;
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
	
	
	
	@RequestMapping(value="/closepost",method=RequestMethod.GET)
	public String postclosed(@ModelAttribute PostDTO postDTO)
	{
	      System.out.println("posts ********************************"+postDTO);
		  //postDTO.getDescription();
         // System.out.println("posts ********************************"+postDTO);
	      postResource.getClosePostUsingGET(postDTO.getId());
	    
		  return "redirect:/postclose/history";
	 }
	
	
	
	@RequestMapping(value="/changealert")
	public String changeAlerts(Model model)
	{
		PostDTO postDTO1=postResource.getPostUsingGET((long)1).getBody();
		System.out.println("postdto@@@@@@@@@@@@@@@@@@@@"+postDTO1);
		
		View view = new View();
		view.setMediaDTO(new MediaDTO());
		view.setPostDTO(new PostDTO());
		view.setUserRegistrationDTO(new UserRegistrationDTO());
        view.setUserRegistrationId(1);
        view.setUserRegistrationDTO(userRegistrationResourceApi.getUserRegistrationUsingGET((long) 1).getBody());
		model.addAttribute("view", view);
		model.addAttribute("postDTO",postDTO1);
		return "home";
		
	}
	@RequestMapping(value="/alertchange")
	public String ChangeAlert(@ModelAttribute PostDTO postDTO, String name)
	{
		postResource.changeAlertLevelUsingGET(name,postDTO.getId());
		return "home";
		
	}
	
	@RequestMapping(value="/history")
	public String postclosed(Model model)
	{
		PostDTO postDTO = postResource.getPostUsingGET((long)5).getBody();
		System.out.println("postsclosed ********************************"+postDTO);
		model.addAttribute("closeposts",postDTO);
        return "history";
		
     }
	
}
