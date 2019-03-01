 package com.lxisoft.crimestopper.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.crimestopper.client.red_alert.api.UserRegistrationResourceApi;
import com.lxisoft.crimestopper.client.red_alert.model.UserRegistrationDTO;
import com.lxisoft.crimestopper.domain.Complaint;
import com.lxisoft.crimestopper.domain.Hashtag;
import com.lxisoft.crimestopper.domain.Location;
import com.lxisoft.crimestopper.domain.UserResponse;
import com.lxisoft.crimestopper.repository.CommentRepository;
import com.lxisoft.crimestopper.repository.ComplaintRepository;
import com.lxisoft.crimestopper.repository.HashtagRepository;
import com.lxisoft.crimestopper.repository.LocationRepository;
import com.lxisoft.crimestopper.repository.UserRepository;
import com.lxisoft.crimestopper.repository.UserResponseRepository;
import com.lxisoft.crimestopper.service.ComplaintService;
import com.lxisoft.crimestopper.service.dto.CommentDTO;
import com.lxisoft.crimestopper.service.dto.ComplaintDTO;
import com.lxisoft.crimestopper.service.dto.HashtagDTO;
import com.lxisoft.crimestopper.service.dto.LocationDTO;
import com.lxisoft.crimestopper.service.dto.UserResponseDTO;
import com.lxisoft.crimestopper.service.mapper.CommentMapper;
import com.lxisoft.crimestopper.service.mapper.ComplaintMapper;
import com.lxisoft.crimestopper.service.mapper.HashtagMapper;
import com.lxisoft.crimestopper.service.mapper.LocationMapper;
import com.lxisoft.crimestopper.service.mapper.UserResponseMapper;

/**
 * Service Implementation for managing Complaint.
 */
@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

	private final Logger log = LoggerFactory.getLogger(ComplaintServiceImpl.class);

	private final ComplaintRepository complaintRepository;

	private final UserRepository userRepository;

	private final ComplaintMapper complaintMapper;

	@Autowired
	HashtagMapper hashtagMapper;
	
	@Autowired
	LocationMapper locationMapper;
	
	
	@Autowired
	private UserResponseMapper userResponseMapper;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private HashtagRepository hashtagRepository;

	@Autowired
	private CommentMapper commentMapper;

	private final UserResponseRepository userResponseRepository;
   
	@Autowired
	UserRegistrationResourceApi userRegistrationResourceApi;
	
	@Autowired
	LocationRepository locationRepository;

	public ComplaintServiceImpl(ComplaintRepository complaintRepository, ComplaintMapper complaintMapper,
			UserRepository userRepository, UserResponseRepository userResponseRepository) {

		this.userResponseRepository = userResponseRepository;
		this.complaintRepository = complaintRepository;
		this.complaintMapper = complaintMapper;
		this.userRepository = userRepository;
		

	}

	/**
	 * Save a complaint.
	 *
	 * @param complaintDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public ComplaintDTO save(ComplaintDTO complaintDTO) {
		log.debug("Request to save Complaint : {}", complaintDTO);
		
		//set complaint current time the war:time is now in uts not indian 
		Instant instant=Instant.now();        
		instant=instant.plus(5, ChronoUnit.HOURS).plus(30, ChronoUnit.MINUTES);
		complaintDTO.setTime(instant);	
		// hash tag finding start
		
		Pattern pattern = Pattern.compile("#{1}\\w+");
		String subject = complaintDTO.getDescription();
		int i = 0;
		Matcher matcher = pattern.matcher(subject);
		System.out.println("......................." + subject);
		Set<HashtagDTO> hashtags = new HashSet<HashtagDTO>();
		while (matcher.find()) {

		
			HashtagDTO hashtagDTO = new HashtagDTO();

			Optional<Hashtag> optional = hashtagRepository.findByName(matcher.group(0));
			if (optional.isPresent()) {
				hashtagDTO.setId(optional.get().getId());
				optional.get().setCount(optional.get().getCount()+1);
				hashtagRepository.save(optional.get());
				
			} else {
				Hashtag hashtag = new Hashtag();
				hashtag.setCount( 1L);
				hashtag.setName(matcher.group(0));
				hashtagDTO = hashtagMapper.toDto(hashtagRepository.save(hashtag));
			}
			hashtags.add(hashtagDTO);
		/*	log.debug("hashtag dtos:::::::::::::::::::;;;;" + hashtagDTO);
			System.out.println("end:::::::::::::::::::;;;;" + matcher.end());*/

		}
		complaintDTO.setHashtags(hashtags);
		// hash tag finding end
		

/*		log.debug("request to get all complaints of friends by userId:"+userId);
		List<ComplaintDTO> list=new ArrayList<ComplaintDTO>(); 
		List<UserRegistrationDTO> users=userRegistrationResourceApi.getAllFriendsUsingGET(userId).getBody();
		complaintRepository.findByUserId(userId,pageable);
		for(UserRegistrationDTO userDTO:users)
		{
			List<ComplaintDTO> complaints=complaintRepository.findAllByUserId(userDTO.getId(),pageable).map(complaintMapper::toDto).getContent();
			for(ComplaintDTO complaint:complaints)
			{
				complaint.setUserName(userDTO.getFirstName()+" "+userDTO.getLastName());
				Optional<UserResponse>optional=userResponseRepository.findUserResponseByUserIdAndComplaintId(userDTO.getId(),complaint.getId());
				Optional<UserResponseDTO>optionalDTO=optional.map(userResponseMapper::toDto);
				if(optionalDTO.isPresent())
				{
					complaint.setUserResponse(optionalDTO.get());
=======*/
		//CAUTION location stuffs start
		log.debug("location:::::::::::::::::::;;;;" +complaintDTO.getLocation());
		Location location=new Location();
		location.setLatitude(complaintDTO.getLocation().getLatitude());
		location.setLongitude(complaintDTO.getLocation().getLongitude());
		Location savedLocation=locationRepository.save(location);
		complaintDTO.setLocationId(savedLocation.getId());
		//Optional<Location>location=locationRepository.findBylatitudeAndLongitude(complaintDTO.getLocation().getLatitude(),complaintDTO.getLocation().getLongitude()); 
		
		/*	LocationDTO locationDTO=new LocationDTO();
		if(location.isPresent())
		{
			complaintDTO.getLocation().setId(location.get().getId());
			
		}
		else
		{
			
		}*/
		//CAUTION location stuffs end
		 
		Complaint complaint = complaintMapper.toEntity(complaintDTO);
		complaint = complaintRepository.save(complaint);
		return complaintMapper.toDto(complaint);
	}

	/**
	 * Get all the complaints.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<ComplaintDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Complaints");
		return complaintRepository.findAll(pageable).map(complaintMapper::toDto);
	}

	/**
	 * Get all the Complaint with eager load of many-to-many relationships.
	 *
	 * @return the list of entities
	 */
	@Override
	public Page<ComplaintDTO> findAllWithEagerRelationships(Pageable pageable) {
		return complaintRepository.findAllWithEagerRelationships(pageable).map(complaintMapper::toDto);
	}

	/**
	 * Get one complaint by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<ComplaintDTO> findOne(Long id) {
		log.debug("Request to get Complaint : {}", id);
		Optional<Complaint> result = complaintRepository.findOneWithEagerRelationships(id);
		log.debug("##########################<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>#" + result.get().getHashtags());
		;
		Optional<ComplaintDTO> resultDTO = result.map(complaintMapper::toDto);
		long likes = userResponseRepository.countLikesOfComplaint(result.get());
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>noOflike=" + likes);
 
		long dislikes = userResponseRepository.countDislikesOfComplaint(result.get());
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>noOfdislike=" + dislikes);

		resultDTO.get().setNoOfLikes(likes);
		resultDTO.get().setNoOfDislikes(dislikes);
		return resultDTO;
	}

	/**
	 * Delete the complaint by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Complaint : {}", id);
		complaintRepository.deleteById(id);
	}

	/**
	 * Get one complaints of friends by userId.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */

	@Override
	public Page<ComplaintDTO> findAllComplaintsOfFriends(Pageable pageable, Long userId) {

		log.debug("request to get all complaints of friends by userId:" + userId);
		List<ComplaintDTO> list = new ArrayList<ComplaintDTO>();
		List<UserRegistrationDTO> users = userRegistrationResourceApi.getAllFriendsUsingGET(userId).getBody();
		UserRegistrationDTO currentUser = userRegistrationResourceApi.getUserRegistrationUsingGET(userId).getBody();
		users.add(currentUser);
		complaintRepository.findByUserId(userId, pageable);
		for (UserRegistrationDTO userDTO : users) {
			List<ComplaintDTO> complaints = complaintRepository.findByUserId(userDTO.getId(), pageable)
					.map(complaintMapper::toDto).getContent();
			for (ComplaintDTO complaint : complaints) {
				complaint.setLocation(new LocationDTO());
				complaint.setUserName(userDTO.getFirstName() + " " + userDTO.getLastName());
				String image="data:image/jpg;base64,"+Base64.getEncoder().encodeToString(complaint.getMedia());
				complaint.setImage(image);
				Optional<UserResponse> optional = userResponseRepository.findUserResponseByUserIdAndComplaintId(userId,
						complaint.getId());
				Pageable pageable2 = null;
				HashSet<CommentDTO> comments = new HashSet<CommentDTO>(commentRepository
						.findAllByComplaintId(pageable2, complaint.getId()).map(commentMapper::toDto).getContent());
				Iterator<CommentDTO> itr = comments.iterator();
				while (itr.hasNext()) {
					CommentDTO temp = itr.next();
					UserRegistrationDTO userRegistarion = userRegistrationResourceApi
							.getUserRegistrationUsingGET(temp.getUserId()).getBody();
					temp.setUserName(userRegistarion.getFirstName() + " " + userRegistarion.getLastName());

				}
				LocationDTO location=locationRepository.findById(complaint.getLocationId()).map(locationMapper::toDto).get();
				complaint.setComments(comments);
				complaint.setLocation(location);
				Optional<UserResponseDTO> optionalDTO = optional.map(userResponseMapper::toDto);
				if (optionalDTO.isPresent()) {
					complaint.setUserResponse(optionalDTO.get());

				} else {
					complaint.setUserResponse(new UserResponseDTO());
				}

			}
			list.addAll(complaints);

		}

		Page<ComplaintDTO> pages = new PageImpl<ComplaintDTO>(list, pageable, list.size());
		return pages;
	}

	@Override
	public Page<ComplaintDTO> findAllComplaintsByHashtag(Pageable pageable, String searchContent) {
  
		log.debug("request to get all complaints by search content:" + searchContent);
		List<ComplaintDTO> list = new ArrayList<ComplaintDTO>();
		// List<ComplaintDTO>
		// complaints=complaintRepository.findAllComplaintsByHashtags(pageable,hashtag.get()).map(complaintMapper::toDto).getContent();
		Optional<Hashtag> hashtag = hashtagRepository.findByName(searchContent);
		List<ComplaintDTO> complaints = new ArrayList<ComplaintDTO>();
		if (hashtag.isPresent()) {
			Page<Complaint> pageComplaint = complaintRepository.findAllComplaintsByHashtags(pageable, hashtag.get());
			if (pageComplaint.hasContent())
				complaints = pageComplaint.map(complaintMapper::toDto).getContent();

			for (ComplaintDTO complaint : complaints) {
				
				String image="data:image/jpg;base64,"+Base64.getEncoder().encodeToString(complaint.getMedia());
				
				complaint.setImage(image);
				UserRegistrationDTO userDTO = userRegistrationResourceApi
						.getUserRegistrationUsingGET(complaint.getUserId()).getBody();
				complaint.setUserName(userDTO.getFirstName() + " " + userDTO.getLastName());

				Optional<UserResponse> optional = userResponseRepository
						.findUserResponseByUserIdAndComplaintId(complaint.getUserId(), complaint.getId());
				Pageable pageable2 = null;
				HashSet<CommentDTO> comments = new HashSet<CommentDTO>(commentRepository
						.findAllByComplaintId(pageable2, complaint.getId()).map(commentMapper::toDto).getContent());
				Iterator<CommentDTO> itr = comments.iterator();
				while (itr.hasNext()) {
					CommentDTO temp = itr.next();
					UserRegistrationDTO userRegistarion = userRegistrationResourceApi
							.getUserRegistrationUsingGET(temp.getUserId()).getBody();

					temp.setUserName(userRegistarion.getFirstName() + " " + userRegistarion.getLastName());

				}

				complaint.setComments(comments);

				Optional<UserResponseDTO> optionalDTO = optional.map(userResponseMapper::toDto);
				if (optionalDTO.isPresent()) {
					complaint.setUserResponse(optionalDTO.get());

				} else {
					complaint.setUserResponse(new UserResponseDTO());
				}

			}
			list.addAll(complaints);

		}

		Page<ComplaintDTO> pages = new PageImpl<ComplaintDTO>(list, pageable, list.size());
		return pages;

	}

	public Page<ComplaintDTO> findAllComplaintsOfUserId(Pageable pageable, Long userId){
		
		//List<ComplaintDTO> complaints=complaintRepository.findAllByUserId(userId,pageable).map(complaintMapper::toDto).getContent();
		Page<ComplaintDTO> complaints=complaintRepository.findByUserId(userId,pageable).map(complaintMapper::toDto);

		return complaints;
		
	}

}
