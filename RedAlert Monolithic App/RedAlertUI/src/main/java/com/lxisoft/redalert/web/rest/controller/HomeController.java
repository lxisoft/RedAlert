package com.lxisoft.redalert.web.rest.controller;

import java.time.Instant;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lxisoft.redalert.client.red_alert.api.MediaResourceApi;
import com.lxisoft.redalert.client.red_alert.api.PostResourceApi;
import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.MediaDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO.AlertLevelEnum;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.domain.User;
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
		@Autowired
		HttpSession session;
		
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
			session.setAttribute("cs",userRegistrationDTO );
			
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
		   // OffsetDateTime o = OffsetDateTime.ofInstant(Instant.now(),ZoneId.systemDefault());
			    
			postDTO.setCreatedOn(Instant.now( ));
			System.out.println("#######################################>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> time  "+	postDTO.getCreatedOn());;
			PostDTO postDto = postResourceApi.createPostUsingPOST(postDTO).getBody();
			MediaDTO mediaDTO = new MediaDTO();
			
		    try{
				for(MultipartFile file:files)
				{
				  mediaDTO.setFile(file.getBytes());
				  mediaDTO.setPostId(postDto.getId());
				  mediaResourceApi.createMediaUsingPOST(mediaDTO);
				  String message =  postResourceApi.sendMailWithAttachmentUsingPOST(postDto).getBody();
	            }
			  }catch(Exception e)
			    {
				   e.printStackTrace();
			    }
		
			return "redirect:/redAlertUi/home";
		}	
	
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
		
		

		 																	
		/*postDTO.setCreatedOn(Instant.now());
		PostDTO postDto = postResourceApi.createPostUsingPOST(postDTO).getBody();
		MediaDTO mediaDTO = new MediaDTO();
		
	    try{
			for(MultipartFile file:files)
			{
			  mediaDTO.setFile(file.getBytes());
			  mediaDTO.setPostId(postDto.getId());
			  mediaResourceApi.createMediaUsingPOST(mediaDTO);
			   String message =  postResourceApi.sendMailWithAttachmentUsingPOST(postDto).getBody();
			
            }
		  }catch(Exception e)
		    {
			   e.printStackTrace();
		    }
	
		return "redirect:/redAlertUi/home";
	}
	*/
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
	
	

	    
	
	
												
	



