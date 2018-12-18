package com.lxisoft.redalert.web.rest.controller;

import java.util.ArrayList;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxisoft.redalert.client.red_alert.api.MediaResourceApi;
import com.lxisoft.redalert.client.red_alert.api.PostResourceApi;
import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.MediaDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;
import com.lxisoft.redalert.model.ImageView;
import com.lxisoft.redalert.model.View;

@Controller
@RequestMapping("/redAlertuiHistory")

public class HistoryController {
	
	@Autowired
	PostResourceApi postResourceApi;
	@Autowired
	MediaResourceApi mediaResourceApi;
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
	
	

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
