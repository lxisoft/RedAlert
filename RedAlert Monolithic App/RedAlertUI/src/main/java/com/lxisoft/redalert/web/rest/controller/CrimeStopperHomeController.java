package com.lxisoft.redalert.web.rest.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.redalert.client.crimestopper.api.CommentResourceApi;
import com.lxisoft.redalert.client.crimestopper.api.ComplaintResourceApi;
import com.lxisoft.redalert.client.crimestopper.api.UserResponseResourceApi;
import com.lxisoft.redalert.client.crimestopper.model.CommentDTO;
import com.lxisoft.redalert.client.crimestopper.model.ComplaintDTO;
import com.lxisoft.redalert.client.crimestopper.model.LocationDTO;
import com.lxisoft.redalert.client.crimestopper.model.UserResponseDTO;
import com.lxisoft.redalert.client.crimestopper.model.UserResponseDTO.FlagEnum;
import com.lxisoft.redalert.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.redalert.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.redalert.domain.User;
import com.lxisoft.redalert.model.ComplaintView;
import com.lxisoft.redalert.model.HomeView;
import com.lxisoft.redalert.repository.UserRepository;
import com.lxisoft.redalert.security.SecurityUtils;

@Controller
@RequestMapping("/crime-stopper")
public class CrimeStopperHomeController {

	Logger log = LoggerFactory.getLogger(CrimeStopperHomeController.class);
	
	@Autowired
	ComplaintResourceApi complaintResourceApi;
	
	@Autowired
	CSTrendingController trendingController;
	
	@Autowired
	UserResponseResourceApi userResponseResourceApi;

	@Autowired
	CommentResourceApi commentResourceApi;

	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;

	@Autowired
	HttpSession session;

	@Autowired
	UserRepository userRepository;

	public String redirectHome(Model model,String url) {
		

		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		log.debug("current user login>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + currentUserLogin);

		User user = userRepository.findOneByLogin(currentUserLogin).get();

		log.debug("user>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + user);

	
		UserRegistrationDTO userRegistrationDTO = userRegistrationResourceApi.findByUserIdUsingGET(user.getLogin())
				.getBody();
		

		ResponseEntity<List<ComplaintDTO>> result = complaintResourceApi.getAllComplaintsOfFriendsUsingGET(
				userRegistrationDTO.getId(), null, null, null, null, null, null, null, null, null, null, null);
		

		List<ComplaintDTO> set = new ArrayList<ComplaintDTO>(result.getBody());
		HomeView homeView = new HomeView(new ArrayList<ComplaintDTO>());

		for (ComplaintDTO temp : set) {
			homeView.getComplaints().add(temp);
			log.debug(" LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLocationres"+temp.getLocation());
		}
		model.addAttribute("homeView", homeView);
		ComplaintDTO dto=new ComplaintDTO();
		dto.setLocation(new LocationDTO()); 
		model.addAttribute("complaintDTO",dto);
		if(url==null || url.equals("") || url.equals("null"))
		return "crimestopper-index";
		else
		return trendingController.getTrendingHashtagsAndComplaints(model, url);
	}

	@PostMapping(value = "/likeComplaint")

	public String likeComplaint(Model model, @RequestParam(value = "flag") String flag,
			@RequestParam(value = "complaintId") String complaintId,
			@RequestParam(value = "responseId", defaultValue = "null") String responseId
			, @RequestParam(value = "url",required=false) String url) {
		log.debug("oooooooooooooooooooooooooooooooooooooooooooooooooooomark an   response:" + flag + responseId
				+ complaintId);

		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		User user = userRepository.findOneByLogin(currentUserLogin).get();
		UserRegistrationDTO userRegistrationDTO = userRegistrationResourceApi.findByUserIdUsingGET(user.getLogin())
				.getBody();

		UserResponseDTO userResponseDTO = new UserResponseDTO();
		
		if (responseId.equals("") || responseId.equals("null")) {
			responseId = null;
		} else {

			userResponseDTO.setId(Long.parseLong(responseId));
		}
		FlagEnum f = FlagEnum.LIKE;
		userResponseDTO.setFlag(f);
		userResponseDTO.setComplaintId(Long.parseLong(complaintId));
		userResponseDTO.setUserId(userRegistrationDTO.getId());

		if (responseId != null) {
			ResponseEntity<UserResponseDTO> result = userResponseResourceApi
					.updateUserResponseUsingPUT(userResponseDTO);
		} else {
			ResponseEntity<UserResponseDTO> result = userResponseResourceApi
					.createUserResponseUsingPOST(userResponseDTO);
		}
		
		
		ComplaintDTO dto=new ComplaintDTO();
		dto.setLocation(new LocationDTO()); 
		model.addAttribute("complaintDTO",dto);
		return redirectHome(model,url);
	}

	@GetMapping(value = "/home")
	public String home(Model model,@RequestParam(value = "url",required=false) String url) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>jjjjjjjjjjjjjjjjjjjjjjjjjjjjj>>ccccc"+url);
		url="null";
		return redirectHome(model,url);
	}

	@PostMapping(value = "/dislikeComplaint")

	public String dislikeComplaint(Model model, @RequestParam(value = "flag") String flag,
			@RequestParam(value = "complaintId") String complaintId,
			@RequestParam(value = "responseId") String responseId,@RequestParam(value = "url",required=false) String url) {
		log.debug("oooooooooooooooooooooooooooooooooooooooooooomark an   response:" + flag + complaintId + responseId);

		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		User user = userRepository.findOneByLogin(currentUserLogin).get();
		UserRegistrationDTO userRegistrationDTO = userRegistrationResourceApi.findByUserIdUsingGET(user.getLogin())
				.getBody();

		UserResponseDTO userResponseDTO = new UserResponseDTO();
		if (responseId.equals("") || responseId.equals("null")) {
			responseId = null;
		} else {

			userResponseDTO.setId(Long.parseLong(responseId));
		}
		FlagEnum f = FlagEnum.DISLIKE;
		userResponseDTO.setFlag(f);
		userResponseDTO.setComplaintId(Long.parseLong(complaintId));
		userResponseDTO.setUserId(userRegistrationDTO.getId());

		if (responseId != null) {
			ResponseEntity<UserResponseDTO> result = userResponseResourceApi
					.updateUserResponseUsingPUT(userResponseDTO);
		} else {
			ResponseEntity<UserResponseDTO> result = userResponseResourceApi
					.createUserResponseUsingPOST(userResponseDTO);
		}
		
		
		return redirectHome(model,url);
	}

	@PostMapping(value = "/comment")

	public String comment(Model model, @RequestParam(value = "comment") String comment,
			@RequestParam(value = "complaintId") String complaintId,@RequestParam(value = "url",required=false) String url) {

		String currentUserLogin = SecurityUtils.getCurrentUserLogin().get();
		User user = userRepository.findOneByLogin(currentUserLogin).get();
		CommentDTO commentDTO = new CommentDTO();
		UserRegistrationDTO userRegistrationDTO = userRegistrationResourceApi.findByUserIdUsingGET(user.getLogin())
				.getBody();
		Long userId = userRegistrationDTO.getId();
		log.debug("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccc comment:" + comment + "  complaintId="
				+ complaintId + "   userId" + userId);
		commentDTO.setComplaintId(Long.parseLong(complaintId));

		commentDTO.setDescription(comment);
		commentDTO.setUserId(userId);
		commentDTO.setComplaintId(Long.parseLong(complaintId));

		commentResourceApi.saveCommentInComplaintUsingPOST(commentDTO);
	
	
		return redirectHome(model,url);
	}

	@GetMapping(value = "/search")

	public String likeComplaint(Model model, @RequestParam(value = "searchContent") String searchContent,@RequestParam(value = "url",required=false) String url) {
		log.debug("oooooooooooooooooooooooooooooooooooooooooooooooooooomark an   response:" + searchContent);

		List<ComplaintDTO> result = complaintResourceApi.getAllComplaintsHashtagUsingGET(searchContent, null, null,
				null, null, null, null, null, null, null, null, null).getBody();
		List<ComplaintDTO> set = new ArrayList<ComplaintDTO>(result);
		HashSet<ComplaintView> complaints = new HashSet<ComplaintView>();
		HomeView homeView = new HomeView(new ArrayList<ComplaintDTO>());

		for (ComplaintDTO temp : set) {
			homeView.getComplaints().add(temp);
		}
		model.addAttribute("homeView", homeView);
		ComplaintDTO dto=new ComplaintDTO();
		dto.setLocation(new LocationDTO()); 
		model.addAttribute("complaintDTO",dto);
		if( url==null|| url.equals("") || url.equals("null") )
			return "crimestopper-index";
			else
			return url;
	}

}
