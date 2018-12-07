package com.lxisoft.redalert.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
import com.codahale.metrics.annotation.Timed;
import com.lxisoft.redalert.client.red_alert.api.ActionResourceApi;
import com.lxisoft.redalert.client.red_alert.api.PostResourceApi;
import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.*;
//import com.lxisoft.redalert.domain.Feed;
//import com.lxisoft.redalert.domain.UserRegistration;
//import com.lxisoft.redalert.domain.enumeration.Alert;
/*import com.lxisoft.redalert.repository.ActionRepository;
import com.lxisoft.redalert.repository.FeedRepository;
import com.lxisoft.redalert.repository.UserRegistrationRepository;
import com.lxisoft.redalert.service.ActionService;
*///import com.lxisoft.redalert.service.dto.FeedDTO;
//import com.lxisoft.redalert.service.mapper.FeedMapper;
import com.lxisoft.redalert.web.rest.errors.BadRequestAlertException;
import com.lxisoft.redalert.web.rest.util.HeaderUtil;

@Controller
@RequestMapping("/newsfeeds")
public class PostController {

	@Autowired
	PostResourceApi postResourceApi;
	@Autowired
	UserRegistrationResourceApi userRegistrationApi;
	@Autowired
	ActionResourceApi actionResourceApi;
	/*private FeedRepository feedRepository;
	private FeedMapper feedMapper;
	private ActionRepository actionRepository;
	private UserRegistrationRepository userRegistrationRepository;*/

	/*public FeedsController(FeedRepository feedRepository, ActionRepository actionRepository,
			UserRegistrationRepository userRegistrationRepository) {
		this.feedRepository = feedRepository;
		this.actionRepository = actionRepository;
		this.userRegistrationRepository = userRegistrationRepository;
	}*/

	@GetMapping("/sahal")
	@Timed
	public String createPage(Model model) {
		UserRegistrationDTO userDto = new UserRegistrationDTO();
		userDto.setFirstName("Mohammed");
		userDto.setLastName("Sahal");
		//user.setCreatedTime(Instant.now());
		userDto.email("sahalche39@gmail.com");
		//user.bloodGroup("AB+ve");
		userDto.setContact(8113096326L);
		userRegistrationApi.createUserRegistrationUsingPOST(userDto);
		//user = userRegistrationRepository.save(user);

		PostDTO postDto = new PostDTO();
		/*feed.setUserName(user.getEmail());
		feed.setUserRegistration(user);
		feed.setCreatedTime(Instant.now());
		feed.setComments("Help me i am in urgent situation");
		feed.setType(Alert.ORANGE_ALERT);
		feed.setStatus(false);*/
        
		postResourceApi.createPostUsingPOST(postDto);
		
		
		
		/*
		 * Action action=new Action(); action.setUserName(feed.getUserName());
		 * action.setFeed(feed); actionRepository.save(action); action=new
		 * Action(); action.setUserName(); action.setFeed(feedSaved);
		 * actionRepository.save(action); action=new Action();
		 * action.setUserName("third action"); action.setFeed(feedSaved);
		 * actionRepository.save(action);
		 */

		// feedRepository.delete((long) 3);
		return "redirect:/newsfeeds/get";
	}

	@GetMapping("/get")
	@Timed
	public String createFeedPage(Model model) {

		/*
		  	
			
			*/
		ResponseEntity<List<PostDTO>> postDto=postResourceApi.getAllPostsUsingGET(null, null, null, null, null, null, null, null, null, null);
		int size = postDto.getBody().size();
		PostDTO post = postDto.getBody().get(size-1);
		//Feed feed = feedRepository.findAll().get(size-1);
		System.out.println("size++++++++++++++++++++++++" + postDto.getBody().size());
		model.addAttribute("feed", post);
		ActionDTO action = new ActionDTO();
		action.setUserName("helper");
		//action.setFeed(feed);
		action.setPostId(post.getId());
		//action.setRequestApproval(false);
		model.addAttribute("newAction", action);
		return "news";

	}

	@PostMapping("/comments")
	@Timed
	public String addComments(@ModelAttribute ActionDTO newAction, Model model, @RequestParam String type)
			throws URISyntaxException {
		System.out.print(type + "***************FEEd**************" + newAction.getPostId());
		System.out.println("*************ACtion**********" + newAction);

		actionResourceApi.createActionUsingPOST(newAction);
		//actionRepository.save(newAction);

		return "redirect:/newsfeeds/get";

	}

	@PostMapping("/request")
	@Timed
	public String addCommentsConfirmation(@ModelAttribute ActionDTO action, Model model)
			throws URISyntaxException {
		
		System.out.println("*************ACtion*** comfomation*******" + action);
	
		 //action =actionResourceApi.getActionUsingGET(action.getId()).getBody();
//action=actionRepository.findOne(action.getId());
System.out.println("*************ACtion** * comfomation*****Find By id**" + action);
action.setApproval(true);
//action.setRequestApproval(true);
	 actionResourceApi.createActionUsingPOST(action);	
//action=actionRepository.save(action);
	
PostDTO post = postResourceApi.getPostUsingGET(action.getPostId()).getBody();

//Feed feed=feedRepository.findOne(action.getFeed().getId());
post.setActive(true);
//feed.setStatus(true);

postResourceApi.createPostUsingPOST(post);
//feed=feedRepository.save(feed);


System.out.println("feed saved"+post);

		return "redirect:/newsfeeds/get";

	}
	
	
	@GetMapping("/sample")
	@Timed
	public String addCommentsConfirmation(Model model)
			throws URISyntaxException {
		
		
		
		return "comments";

	}
	
	
	

}
