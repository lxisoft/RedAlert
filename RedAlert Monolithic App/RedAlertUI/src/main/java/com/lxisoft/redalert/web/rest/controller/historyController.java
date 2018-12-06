package com.lxisoft.redalert.web.rest.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxisoft.redalert.client.red_alert.api.MediaResourceApi;
import com.lxisoft.redalert.client.red_alert.api.PostResourceApi;
import com.lxisoft.redalert.client.red_alert.model.MediaDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;

@Controller
@RequestMapping("/redAlertuiHistory")

public class historyController {
	
	@Autowired
	PostResourceApi postResourceApi;
	@Autowired
	MediaResourceApi mediaResourceApi;
	
	@GetMapping("/getPosts")
	public String getPostsFromPostResource()
	{
		ArrayList<PostDTO> posts = (ArrayList<PostDTO>) postResourceApi.getAllPostsUsingGET(null, null, null, null, null, null, null, null, null, null).getBody();
		System.out.println("post size"+posts.size());
		ArrayList<MediaDTO> medias = (ArrayList<MediaDTO>) mediaResourceApi.getAllMediaUsingGET(null, null, null, null, null, null, null, null, null, null).getBody();
		System.out.println("media size"+medias.size());
		return "home";
	}

}
