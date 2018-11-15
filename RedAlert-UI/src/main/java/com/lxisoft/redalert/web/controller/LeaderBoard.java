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
			/*UserRegistrationDTO user=new UserRegistrationDTO();
			user.setId((long)1);
			user.setUserName("abhina");
			user.setScore((long)5);
			*/
			
			List<UserRegistrationDTO> user=new ArrayList<UserRegistrationDTO>();
			user.add(new UserRegistrationDTO("1","abhina",(long)5));
			user.add(new UserRegistrationDTO("2","shilpa",(long)10));

		System.out.println("***********0 index"+user.get(0).getUserName());
		System.out.println("***********1 index"+user.get(1).getUserName());
			//ResponseEntity<List<UserRegistrationDTO>> dto=userRegApiReso.getAllUserRegistrationsUsingGET(null, null, null, null, 5, null, null, null, null, null, null);
			model.addAttribute("userRegDTO",user);
			return "allUsers";
		}
		
		
		@RequestMapping(value="posts")
		public String doPosts(Model model,String name)
		{
			log.debug("name______________"+name);
			PostDTO dto=new PostDTO();
			dto.setDescription("help me");
			dto.setDescription("Iam in danger");
			AlertLevelEnum alertlevel=AlertLevelEnum.valueOf("RED");
			AlertLevelEnum level2=AlertLevelEnum.valueOf("GREEN");
			dto.setAlertLevel(alertlevel);
			dto.setAlertLevel(level2);
			UserRegistrationDTO userDto=new UserRegistrationDTO(name, null, null);
			
			model.addAttribute("posts",dto);
			model.addAttribute("userdto",userDto);
			
			
			
			
			ActionDTO actiondto=new ActionDTO();
			actiondto.setDescription("I will help you");
			ReactionEnum reaction=ReactionEnum.valueOf("COMMENT");
			actiondto.setReaction(reaction);
			
			model.addAttribute("actions",actiondto);
			return "showPosts";
			
		}
		/*@RequestMapping(value="actions")
		public String doActions(Model model,String name)
		{
			log.debug("name_____________"+name);
			ActionDTO dto=new ActionDTO();
			dto.setDescription("I will help you");
			ReactionEnum reaction=ReactionEnum.valueOf("COMMENT");
			dto.setReaction(reaction);
			
			model.addAttribute("actions",dto);
			return "showActions";
			
		}*/
		
		
	}
		
		
	


