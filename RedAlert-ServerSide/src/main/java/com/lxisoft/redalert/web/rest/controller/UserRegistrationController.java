package com.lxisoft.redalert.web.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lxisoft.redalert.domain.UserRegistration;
import com.lxisoft.redalert.service.UserRegistrationService;
import com.lxisoft.redalert.service.dto.UserRegistrationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/user")
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@RequestMapping("/userList")
	public List<UserRegistration> getUserList(){
		return userRegistrationService.findAll();
	}
	
	//USING PAGE
	@RequestMapping(value = "/searchByFirstName", method = RequestMethod.POST)
	public Page<UserRegistrationDTO> searchByFirstName(@RequestBody UserRegistration userReg, Pageable pageble){
		return userRegistrationService.getAllUsersByFirstName(userReg.getFirstName(), pageble);
	}

	//USING LIST
	@RequestMapping(value = "/searchByLastName", method = RequestMethod.POST)
	public Page<UserRegistrationDTO> searchByLastName(@RequestBody UserRegistration userReg, Pageable pageble){
		return userRegistrationService.getAllUsersByLastName(userReg.getLastName(), pageble);
	}
	
	@RequestMapping(value = "/searchByEmail", method = RequestMethod.POST)
	public Page<UserRegistrationDTO> searchByEmail(@RequestBody UserRegistration userReg, Pageable pageble){
		return userRegistrationService.getAllUsersByEmail(userReg.getEmail(), pageble);
	}
	
	//USES MAP INSTEAD OF USERREG TO GET A STRING FROM JSON INPUT
	@RequestMapping(value = "/searchByFirstNameLastNameEmail", method = RequestMethod.POST)
	public Page<UserRegistrationDTO> searchByFirstNameLastNameEmail(@RequestBody Map<String, Object> object, Pageable pageble){
		return userRegistrationService.getAllUsersByFirstNameLastNameEmail(object.get("keyword").toString(), pageble);
	}
	
	//USING PATHVARIABLE AND GET
	@RequestMapping(value = "/searchByPassword/{password}", method = RequestMethod.GET)
	public UserRegistration getUserPathVariable(@PathVariable("password") String password){
		return userRegistrationService.getUserByPassword(password);
	}
	
	//USING REQUESTBODY AND POST
	@RequestMapping(value = "/searchByPassword", method = RequestMethod.POST)
	public UserRegistration getUserRequestBody(@RequestBody UserRegistration userReg){	
		return userRegistrationService.getUserByPassword(userReg.getPassword());
	}
}
