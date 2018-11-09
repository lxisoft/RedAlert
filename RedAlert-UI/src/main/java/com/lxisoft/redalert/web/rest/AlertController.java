package com.lxisoft.redalert.web.rest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;

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
import com.lxisoft.redalert.client.red_alert.model.ActionDTO;
import com.lxisoft.redalert.client.red_alert.model.MediaDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.model.View;

@Controller
@RequestMapping("/alertController")
public class AlertController {
	@GetMapping("/firstpage")
	public String getAlert(Model model)
	{
		//model.addAttribute("post", new PostDTO());
		return "home";
	}

	
	@GetMapping("/getAlert")
	public String getAction(@ModelAttribute PostDTO postDTO,Model model)
	{
		if ((postDTO.getAlertLevel().equals(PostDTO.AlertLevelEnum.ORANGE))) {
			postDTO.setAlertLevel(PostDTO.AlertLevelEnum.ORANGE);
			//postDTO = PostService.save(postDTO);
			System.out.println("first feed " + postDTO);
			View view = new View();

			MediaDTO mediaDTO = new MediaDTO();
			mediaDTO.setPostId(postDTO.getId());
			view.setPostDTO(postDTO);
			view.setMediaDTO(mediaDTO);
			System.out.println("first file " + view.getPostDTO() + "*****" + view.getMediaDTO().getPostId());
			model.addAttribute("view", view);

			return "orangealert";
	}else if ((postDTO.getAlertLevel().equals(PostDTO.AlertLevelEnum.RED))) {
		postDTO.setAlertLevel(PostDTO.AlertLevelEnum.RED);
		//feed = feedService.save(feed);
		System.out.println("first feed " + postDTO);
		View view = new View();

		MediaDTO mediaDTO = new MediaDTO();
		mediaDTO.setPostId(postDTO.getId());
		view.setPostDTO(postDTO);
		view.setMediaDTO(mediaDTO);
		System.out.println("first file " + view.getPostDTO() + "*****" + view.getMediaDTO().getPostId());
		model.addAttribute("view", view);
		return "redalert";
	} else {
		postDTO.setAlertLevel(PostDTO.AlertLevelEnum.GREEN);
		//postDTO = feedService.save(feed);
		System.out.println("first feed " + postDTO);
		View view = new View();

		MediaDTO mediaDTO = new MediaDTO();
		mediaDTO.setPostId(postDTO.getId());
		view.setPostDTO(postDTO);
		view.setMediaDTO(mediaDTO);
		System.out.println("first file " + view.getPostDTO() + "*****" + view.getMediaDTO().getPostId());
		model.addAttribute("view", view);
		return "greenalert";
	}
	
	}
	@PostMapping("/file")
	@Timed
	public String createFeed(@ModelAttribute View view, @RequestParam MultipartFile img,
			RedirectAttributes redirectAttr, Model model) throws URISyntaxException {
		System.out.println("second View Dto" + view);
		System.out.println("second feed dto" + view.getPostDTO() + "id" + view.getMediaDTO());
		//PostDTO postDto = postService.findOne(view.getPostDTO().getId());
		/*postDto.setComments(view.getPostDTO().getComments());
		postDto.setType(view.getpostdto().getAlertLevel());
		System.out.println("feeddto nnnn" + postDto);

		postService.save(postDto);*/
		MediaDTO mediaDTO = view.getMediaDTO();
		mediaDTO.setPostId(view.getPostDTO().getId());

		try {
			
			String imagedata="data:image/jpg;base64,"+Base64.getEncoder().encodeToString(img.getBytes());
			model.addAttribute("image", imagedata);
			mediaDTO.setFile(img.getBytes());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("second file dto" + view.getMediaDTO().getFile());
		//fileservice.save(view.getFile());
		System.out.println("successsful");
		return "news";
	}

}
