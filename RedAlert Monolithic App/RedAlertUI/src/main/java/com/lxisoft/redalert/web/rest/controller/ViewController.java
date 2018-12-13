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
import com.lxisoft.redalert.model.View;

@Controller
@RequestMapping("/redAlertUi")
public class ViewController {
	@Autowired
	PostResourceApi postResourceApi;
	@Autowired
	MediaResourceApi mediaResourceApi;
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
	
	@GetMapping("/index")
	public String getIndex(Model model)
	{
		View view = new View();
		view.setUserRegistrationDTO(new UserRegistrationDTO());
		model.addAttribute(view);
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
	

	
	@GetMapping("/friends")
	public String getFriends()
	{
	    return "friends";
	}
	    
	@GetMapping("/history")
	public String getHistory(Model model)
	{
		View view = new View();
		view.setPosts((ArrayList<PostDTO>) postResourceApi.getAllPostsUsingGET(null, null, null, null, null, null, null, null, null, null).getBody());
		view.setMedias((ArrayList<MediaDTO>) mediaResourceApi.getAllMediaUsingGET(null, null, null, null, null, null, null, null, null, null).getBody());
		view.setUsers((ArrayList<UserRegistrationDTO>) userRegistrationResourceApi.getAllUserRegistrationsUsingGET(null, null, null, null, null, null, null, null, null, null, null).getBody());
		view.setPostDTO(view.getPosts().get(0));
		view.setMediaDTO(view.getMedias().get(0));
		System.out.println("Id "+view.getPostDTO().getDescription());
		//System.out.println("name "+view.getUserRegistrationDTO().getFirstName());
		
		view.setUserRegistrationDTO(userRegistrationResourceApi.getUserRegistrationUsingGET(view.getPostDTO().getUserRegistrationId()).getBody());
		model.addAttribute("view",view);
	   return "history";
	}
	
}
	
	/*@GetMapping("/getAlert")
	public String getAction(@ModelAttribute PostDTO postDTO,Model model)
	{
		if ((postDTO.getAlertLevel().equals(PostDTO.AlertLevelEnum.ORANGE))) {
			postDTO.setAlertLevel(PostDTO.AlertLevelEnum.ORANGE);
			//postDTO = PostService.save(postDTO);
			postResourceApi.updatePostUsingPUT(postDTO);
			System.out.println("first feed " + postDTO);
			View view = new View();

			MediaDTO mediaDTO = new MediaDTO();
			mediaDTO.setPostId(postDTO.getId());
			view.setPostDTO(postDTO);
			view.setMediaDTO(mediaDTO);
			System.out.println("first file " + view.getPostDTO() + "*****" + view.getMediaDTO().getPostId());
			model.addAttribute("view", view);

			return "home";
	}else if ((postDTO.getAlertLevel().equals(PostDTO.AlertLevelEnum.RED))) {
		postDTO.setAlertLevel(PostDTO.AlertLevelEnum.RED);
		//feed = feedService.save(feed);
		postResourceApi.updatePostUsingPUT(postDTO);
		System.out.println("first feed " + postDTO);
		View view = new View();

		MediaDTO mediaDTO = new MediaDTO();
		mediaDTO.setPostId(postDTO.getId());
		view.setPostDTO(postDTO);
		view.setMediaDTO(mediaDTO);
		System.out.println("first file " + view.getPostDTO() + "*****" + view.getMediaDTO().getPostId());
		model.addAttribute("view", view);
		return "home";
	} else {
		postDTO.setAlertLevel(PostDTO.AlertLevelEnum.GREEN);
		//postDTO = feedService.save(feed);
		postResourceApi.updatePostUsingPUT(postDTO);
		System.out.println("first feed " + postDTO);
		View view = new View();

		MediaDTO mediaDTO = new MediaDTO();
		mediaDTO.setPostId(postDTO.getId());
		view.setPostDTO(postDTO);
		view.setMediaDTO(mediaDTO);
		System.out.println("first file " + view.getPostDTO() + "*****" + view.getMediaDTO().getPostId());
		model.addAttribute("view", view);
		return "home";
	}
	
	}
	@PostMapping("/getform")
	@Timed
	public String createFeed(@ModelAttribute View view, @RequestParam MultipartFile img,
			RedirectAttributes redirectAttr, Model model) throws URISyntaxException {
		System.out.println("second View Dto" + view);
		System.out.println("second feed dto" + view.getPostDTO() + "id" + view.getMediaDTO());
		
		AlertLevelEnum alertLevel = view.getPostDTO().getAlertLevel();
		System.out.println("testing..."+ alertLevel);
		view.getPostDTO().setAlertLevel(alertLevel);;
		//PostDTO postDto = postService.findOne(view.getPostDTO().getId());
		 ResponseEntity<PostDTO> postDto = postResourceApi.getPostUsingGET(view.getPostDTO().getId());
		 
		postDto.getBody().setDescription(view.getPostDTO().getDescription());
		postDto.getBody().setAlertLevel(view.getPostDTO().getAlertLevel());
		System.out.println("feeddto nnnn" + postDto);

		postResourceApi.updatePostUsingPUT(postDto.getBody());
		ResponseEntity<MediaDTO> mediaDTO = mediaResourceApi.createMediaUsingPOST(view.getMediaDTO());
		mediaDTO.getBody().setPostId(view.getPostDTO().getId());

		try {
			
			String imagedata="data:image/jpg;base64,"+Base64.getEncoder().encodeToString(img.getBytes());
			model.addAttribute("image", imagedata);
			mediaDTO.getBody().setFile(img.getBytes());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("second file dto" + view.getMediaDTO().getFile());
		//fileservice.save(view.getFile());
		mediaResourceApi.updateMediaUsingPUT(view.getMediaDTO());
		System.out.println("successsful");
		return "home";
	}*/


