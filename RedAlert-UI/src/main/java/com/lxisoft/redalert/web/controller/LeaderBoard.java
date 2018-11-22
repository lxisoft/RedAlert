package com.lxisoft.redalert.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.ActionDTO;
import com.lxisoft.redalert.client.red_alert.model.ActionDTO.ReactionEnum;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO.AlertLevelEnum;
import com.lxisoft.redalert.client.red_alert.model.ReportDTO;
import com.lxisoft.redalert.client.red_alert.model.ReportDTO.ReportTypeEnum;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;


@Controller
//@RequestMapping("/leaderBoard")
public class LeaderBoard {
	
	
	@Autowired

	UserRegistrationResourceApi userRegApiReso;
	  private final Logger log =LoggerFactory.getLogger(LeaderBoard.class);
		@RequestMapping(value="/findAllUsers",method=RequestMethod.GET)
		public String createScores(Model model)
		{
		   List<UserRegistrationDTO> userdtolist=new ArrayList<UserRegistrationDTO>();
			
			UserRegistrationDTO userdto1=new UserRegistrationDTO();
			userdto1.setId((long)1);
			userdto1.setUserName("Abhina");
			userdto1.setEmail("abhina38@gmail.com");
			
			UserRegistrationDTO userdto2=new UserRegistrationDTO();
			userdto2.setId((long)2);
			userdto2.setUserName("Shilpa");
			userdto2.setEmail("shilpa.s@gmail.com");
			
			userdtolist.add(userdto1);
			userdtolist.add(userdto2);

		System.out.println("***********0 index"+userdtolist.get(0).getUserName());
		System.out.println("***********1 index"+userdtolist.get(1).getUserName());
			//ResponseEntity<List<UserRegistrationDTO>> dto=userRegApiReso.getAllUserRegistrationsUsingGET(null, null, null, null, 5, null, null, null, null, null, null);
			model.addAttribute("userRegDTO",userdtolist);
			return "allUsers";
		}
		
		
		@RequestMapping(value="posts")
		public String doPosts(Model model,String name)
		{
			log.debug("name______________"+name);
			
	
			UserRegistrationDTO userdto=new UserRegistrationDTO();
			
			List<PostDTO>postDTO=new ArrayList<PostDTO>();
			
			PostDTO dto1=new PostDTO();
		    dto1.setDescription("iam in danger");
		    String[] array={"help me","anybody here","here is heavy flood"};
		    PostDTO dto2=new PostDTO();
		    dto2.setDescription("help me,possibility of cyclone");
		    PostDTO dto3=new PostDTO();
		    dto3.setDescription("save me");
		    
			postDTO.add(dto1);
			postDTO.add(dto2);
			postDTO.add(dto3);
			
			
			AlertLevelEnum level=AlertLevelEnum.valueOf("RED");
			AlertLevelEnum level2=AlertLevelEnum.valueOf("GREEN");
			AlertLevelEnum level3=AlertLevelEnum.valueOf("ORANGE");
			dto1.setAlertLevel(level);
			dto2.setAlertLevel(level2);
			dto3.setAlertLevel(level3);
			
			
			model.addAttribute("postslist",postDTO);
			model.addAttribute("userdtolist",userdto);
			model.addAttribute("comments",array);
			
			
			
			List<ActionDTO>actiondto=new ArrayList<ActionDTO>();
			
			ActionDTO actiondto1=new ActionDTO();
			actiondto1.setDescription("I will help you");
			ActionDTO actiondto2=new ActionDTO();
			actiondto2.setDescription("I will be there in minutes");
			ActionDTO actiondto3=new ActionDTO();
			actiondto3.setDescription("ok friend,i will save u");
			
			actiondto.add(actiondto1);
			actiondto.add(actiondto2);
			actiondto.add(actiondto3);
			
			ReactionEnum reaction1=ReactionEnum.valueOf("COMMENT");
			ReactionEnum reaction2=ReactionEnum.valueOf("REQUEST_TO_CLOSE");
			ReactionEnum reaction3=ReactionEnum.valueOf("COMMENT");
			
			actiondto1.setReaction(reaction1);
			actiondto2.setReaction(reaction2);
			actiondto3.setReaction(reaction3);
			
			model.addAttribute("actionslist",actiondto);
			return "showPosts";
			
		}
		
		
		
	}
		
		
	


