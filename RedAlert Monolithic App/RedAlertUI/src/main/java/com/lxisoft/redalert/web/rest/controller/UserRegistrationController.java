package com.lxisoft.redalert.web.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.UserDTO;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.domain.Authority;
import com.lxisoft.redalert.domain.User;
import com.lxisoft.redalert.model.View;
import com.lxisoft.redalert.repository.UserRepository;
import com.lxisoft.redalert.service.UserService;

/**
 * @author Silpa
 *
 */
@Controller
@RequestMapping("/redAlertUis")
public class UserRegistrationController {
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
	
	/**
	 * @param view
	 * @return
	 */
	@PostMapping("/getUserDetails")
	public String getUserDetails(@ModelAttribute View view)
	{
		System.out.println("viewwwwwwwwwwwwwwwwwww"+view.getUserRegistrationDTO().getFirstName());
		UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
		 

				userRegistrationDTO = view.getUserRegistrationDTO();
				userRegistrationDTO.setUserId(userRegistrationDTO.getEmail());
				User user=new User();
				user.setId(userRegistrationDTO.getEmail());
				user.setEmail(userRegistrationDTO.getEmail());
				user.setFirstName(userRegistrationDTO.getFirstName());
				user.setPassword(userRegistrationDTO.getPassword());
				user.setLogin(userRegistrationDTO.getEmail());
				user.setActivated(true);
				Set<Authority> roles=new HashSet<Authority>();
				 for (String role:userService.getAuthorities())
				 {
					 Authority authority=new Authority();
					 authority.setName(role);
					 roles.add(authority);
				 }
				user.setAuthorities(roles);
				
				userRepository.save(user);
				
				
		userRegistrationResourceApi.createUserRegistrationUsingPOST(userRegistrationDTO); 
		System.out.print(userRegistrationDTO.getFirstName());
		return "redirect:/redAlertUiIndex/index";
	}

}
