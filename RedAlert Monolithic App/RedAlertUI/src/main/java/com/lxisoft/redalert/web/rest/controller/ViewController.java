package com.lxisoft.redalert.web.rest.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.lxisoft.redalert.model.ImageView;
import com.lxisoft.redalert.model.View;
/**
 * @author Silpa
 *
 */

@Controller
@RequestMapping("/redAlertUi")
public class ViewController {
	@Autowired
	PostResourceApi postResourceApi;
	@Autowired
	MediaResourceApi mediaResourceApi;
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
	
	/**
	 * @param model
	 * @return String
	 */
	@GetMapping("/index")
	public String getIndex(Model model)
	{
		return "index";
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
		view.setUserRegistrationDTO(new UserRegistrationDTO());
        view.setUserRegistrationId(1);
	    view.getUserRegistrationDTO().setId(userRegistrationResourceApi.getUserRegistrationUsingGET((long) 1).getBody().getId());
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
	@GetMapping("/news")
	public String getNews()
	{
		
	   return "news";
	}
	
	/**
	 * @return
	 */
	
	
	@GetMapping("/friends")
	public String getFriends()
	{
	    return "friends";
	}
	    
	@GetMapping("/history")
	public String getHistory(Model model)
	{
		View view = new View();
		ArrayList<ImageView> imageViews=new ArrayList<ImageView>();
		
		ArrayList<PostDTO> posts = (ArrayList<PostDTO>) postResourceApi.getAllPostsByUserRegistrationIdUsingGET((long)1, null, null, null, null, null, null, null, null, null, null).getBody();

		
		for(PostDTO post:posts)
		{
			ImageView imageView=new ImageView(); 
			
		
			ArrayList<MediaDTO> medias = (ArrayList<MediaDTO>) mediaResourceApi.getAllMediaByPostIdUsingGET(post.getId(), null, null, null, null, null,null, null, null, null, null).getBody();
			ArrayList<String> images = new ArrayList<String>();
			for(MediaDTO media:medias)
			{
				
				String image="data:image/jpg;base64,"+Base64.getEncoder().encodeToString(media.getFile());
				images.add(image);
				imageView.setImages(images);
				
			}
			
			 imageView.setMedia(medias);
			 imageView.setPost(post);
			 imageViews.add(imageView);
		}
		  
		
		
		
		view.setImageViews(imageViews);
		
		view.setUserRegistrationDTO(userRegistrationResourceApi.getUserRegistrationUsingGET((long)1).getBody());
	
		model.addAttribute("view",view);
	   return "history";
	}
	
}
	

