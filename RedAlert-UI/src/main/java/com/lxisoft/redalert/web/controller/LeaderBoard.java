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
			
			
			PostDTO dto=new PostDTO();
			dto.setDescription("help me");
			dto.setDescription("Iam in danger");
			AlertLevelEnum alertlevel=AlertLevelEnum.valueOf("RED");
			AlertLevelEnum level2=AlertLevelEnum.valueOf("GREEN");
			dto.setAlertLevel(alertlevel);
			dto.setAlertLevel(level2);
			//UserRegistrationDTO userDto=new UserRegistrationDTO(name, null, null);
			
			model.addAttribute("posts",dto);
			model.addAttribute("userdtolist",userdto);
			
			
			
			
			ActionDTO actiondto=new ActionDTO();
			actiondto.setDescription("I will help you");
			ReactionEnum reaction=ReactionEnum.valueOf("COMMENT");
			actiondto.setReaction(reaction);
			
			model.addAttribute("actions",actiondto);
			return "showPosts";
			
		}
		
		
		
	}
		
		
	


