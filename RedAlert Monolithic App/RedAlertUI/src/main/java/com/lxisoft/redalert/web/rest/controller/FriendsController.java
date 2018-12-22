package com.lxisoft.redalert.web.rest.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.MediaDTO;
import com.lxisoft.redalert.client.red_alert.model.PostDTO;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.domain.User;
import com.lxisoft.redalert.model.View;
import com.lxisoft.redalert.repository.UserRepository;
import com.lxisoft.redalert.security.SecurityUtils;
/**	
 * @author Ansal Khan	
 *	
 */
@Controller
@RequestMapping("/redAlertUiFriends")
public class FriendsController 
{
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping
	public String friendsPageRedirect(Model model)
	{
		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		User user=userRepository.findOneByLogin(currentUserLogin).get();
		UserRegistrationDTO userRegistrationDTO = userRegistrationResourceApi.findByUserIdUsingGET(user.getLogin()).getBody();
		List<UserRegistrationDTO> friends=userRegistrationResourceApi.getAllFriendsUsingGET(userRegistrationDTO.getId()).getBody();
		model.addAttribute("emergencyFriends",friends);
		List<UserRegistrationDTO> allUsers=userRegistrationResourceApi.getAllUserRegistrationsUsingGET(null, null, null, null, null, null, null, null, null, null, null).getBody();
		allUsers.remove(userRegistrationDTO);
		allUsers.removeAll(friends);
		model.addAttribute("allUsers", allUsers);
		return "friends";
	}
	@GetMapping("/find")
	public String findFriend(@RequestParam String searchTerm,Model model)
	{
		Set<UserRegistrationDTO> users=new HashSet<>();
		UserRegistrationDTO user=userRegistrationResourceApi.searchWithUserNameUsingGET(searchTerm).getBody();
		if(user!=null)
			users.add(user);
		userRegistrationResourceApi.searchWithLastNameUsingGET(searchTerm, null, null, null, null, null, null, null, null, null, null).getBody().forEach(users::add);
		userRegistrationResourceApi.searchWithFirstNameLastNameEmailUsingGET(searchTerm, null, null, null, null, null, null, null, null, null, null).getBody().forEach(users::add);
		userRegistrationResourceApi.inputStartingCharacterUsingGET(searchTerm, null, null, null, null, null, null, null, null, null, null).getBody().forEach(users::add);
		model.addAttribute("searchResult", users);
		return friendsPageRedirect(model);
	}
	@PostMapping("/addFriend")
	public String addFriend(@RequestParam Long friendId,Model model)
	{
		UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		User user=userRepository.findOneByLogin(currentUserLogin).get();
		userRegistrationDTO = userRegistrationResourceApi.findByUserIdUsingGET(user.getLogin()).getBody();
		userRegistrationResourceApi.addFriendUsingPOST(friendId, userRegistrationDTO.getId());
		return friendsPageRedirect(model);
	}
}
