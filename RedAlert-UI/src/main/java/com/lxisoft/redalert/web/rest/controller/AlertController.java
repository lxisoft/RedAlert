package com.lxisoft.redalert.web.rest.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;

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
import com.lxisoft.redalert.client.red_alert.model.ActionDTO;
import com.lxisoft.redalert.client.red_alert.model.MediaDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO.AlertLevelEnum;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.model.View;

@Controller
@RequestMapping("/alertcontroller")
public class AlertController {
	@Autowired
	PostResourceApi postResourceApi;
	@Autowired
	MediaResourceApi mediaResourceApi;
	@GetMapping("/firstpage")
	public String getIndex(Model model)
	{
		
				
		return "index";
	}
	@GetMapping("/secondpage")
	public String getHome(Model model)
	{
		model.addAttribute("view", new View());
		return "home";
	}
	@GetMapping("/newsview")
	public String getNews()
	{
	return "news";
	}
	@GetMapping("/friendsview")
	public String getFriends()
	{
	return "friends";
	}
	@PostMapping("/thirdpage")
	public String getAction(@ModelAttribute View view,Model model ){
		
		
		return "home";
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


