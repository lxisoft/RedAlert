package com.lxisoft.redalert.web.rest.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.codahale.metrics.annotation.Timed;
import com.lxisoft.redalert.client.red_alert.api.MediaResourceApi;
import com.lxisoft.redalert.client.red_alert.api.PostResourceApi;
import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.ActionDTO;
import com.lxisoft.redalert.client.red_alert.model.MediaDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;	
import com.lxisoft.redalert.client.red_alert.model.PostDTO.AlertLevelEnum;	
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.domain.User;
import com.lxisoft.redalert.model.ImageView;	
import com.lxisoft.redalert.model.View;
import com.lxisoft.redalert.repository.UserRepository;
import com.lxisoft.redalert.security.SecurityUtils;
/**	
 * @author Silpa	
 *	
 */
@Controller
@RequestMapping("/redAlertUi")
public class HomeController {
	@Autowired	 	
 	PostResourceApi postResourceApi;	 
 	@Autowired	 	
 	MediaResourceApi mediaResourceApi;	 	
	@Autowired		
	UserRegistrationResourceApi userRegistrationResourceApi;
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/signUp")	
	public String getIndex(Model model)		
	{
		View view = new View();	 		
 		view.setUserRegistrationDTO(new UserRegistrationDTO());	 		
 		model.addAttribute("view",view);	 
 		return "SignUp";
	}
	
	/**
	 * @param model
	 * @return
	 */
	@GetMapping("/home")
	public String getHome(Model model)
	{
		View view = new View();
		view.setMediaDTO(new MediaDTO());
		view.setPostDTO(new PostDTO());
		UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
		
		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		 
		
		User user=userRepository.findOneByLogin(currentUserLogin).get();
        userRegistrationDTO = userRegistrationResourceApi.findByUserIdUsingGET(user.getLogin()).getBody();
        view.setUserRegistrationDTO(userRegistrationDTO);
       // view.setUserRegistrationId(1);
	    //view.getUserRegistrationDTO().setId(userRegistrationResourceApi.getUserRegistrationUsingGET((long) 1).getBody().getId());
		model.addAttribute("view", view);
		return "home";
	}
	
	/**
	 * @param view
	 * @param files
	 * @param users
	 * @param medias
	 * @param userAlertLevel
	 * @return
	 */
	@PostMapping("/getAlertDetailsToPost")
	public String getAlertDetailsToPost(@ModelAttribute View view,@RequestParam("files") MultipartFile[] files,
			@ModelAttribute UserRegistrationDTO users,
			@ModelAttribute MediaDTO medias,@RequestParam AlertLevelEnum userAlertLevel)
			
	{
		
		
		PostDTO postDTO = new PostDTO();
		postDTO.setUserRegistrationId(view.getUserRegistrationId());
		postDTO.setAlertLevel(userAlertLevel);
		postDTO.setDescription(view.getPostDTO().getDescription());
		postDTO.setLatitude(view.getPostDTO().getLatitude());
		postDTO.setLongitude(view.getPostDTO().getLongitude());
		postDTO.setCreatedOn(view.getPostDTO().getCreatedOn());
		ResponseEntity<PostDTO> postDto = postResourceApi.createPostUsingPOST(postDTO);
		MediaDTO mediaDTO = new MediaDTO();
		
	    try{
			for(MultipartFile file:files)
			{
			  mediaDTO.setFile(file.getBytes());
			  mediaDTO.setPostId(postDto.getBody().getId());
			  mediaResourceApi.createMediaUsingPOST(mediaDTO);
            }
		  }catch(Exception e)
		    {
			   e.printStackTrace();
		    }
	
		return "redirect:/redAlertUi/home";
	}
	
	/**
	 * @return
	 */
	/*@GetMapping("/news")
	public String getNews(Model model)
	{
		View view = new View();
		view.setPosts((ArrayList<PostDTO>) postResourceApi.getAllPostsUsingGET(null, null, null, null, null, null, null, null, null, null).getBody());

		model.addAttribute("view",view);

	   return "news";
	}*/
	
	/**
	 * @return
	 */
	
	

	    
	
	
}
	


