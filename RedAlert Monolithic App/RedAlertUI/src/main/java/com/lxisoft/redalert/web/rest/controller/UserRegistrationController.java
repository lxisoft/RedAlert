package com.lxisoft.redalert.web.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.UserDTO;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.model.View;

/**
 * @author Silpa
 *
 */
@Controller
@RequestMapping("/redAlertUis")
public class UserRegistrationController {
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;

	/**
	 * @param view
	 * @return
	 */
	@PostMapping("/getUserDetails")
	public String getUserDetails(@ModelAttribute View view)
	{
		UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
				userRegistrationDTO = view.getUserRegistrationDTO();
				UserDTO userDTO=new UserDTO();
				
		userRegistrationResourceApi.createUserRegistrationUsingPOST(userRegistrationDTO); 
		System.out.print(userRegistrationDTO.getFirstName());
		return "redirect:/redAlertUi/index";
	}

}
