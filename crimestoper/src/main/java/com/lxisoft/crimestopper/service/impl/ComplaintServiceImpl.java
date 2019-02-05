package com.lxisoft.crimestopper.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.lxisoft.crimestopper.domain.UserResponse;
import com.lxisoft.crimestopper.repository.ComplaintRepository;
import com.lxisoft.crimestopper.repository.UserRepository;
import com.lxisoft.crimestopper.repository.UserResponseRepository;
import com.lxisoft.crimestopper.service.ComplaintService;
import com.lxisoft.crimestopper.service.dto.ComplaintDTO;
import com.lxisoft.crimestopper.service.dto.UserResponseDTO;
import com.lxisoft.crimestopper.service.mapper.ComplaintMapper;
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
    private  UserResponseMapper userResponseMapper;
    
    
   
    private final UserResponseRepository userResponseRepository;
    
    @Autowired
    UserRegistrationResourceApi  userRegistrationResourceApi;

 
    public ComplaintServiceImpl(ComplaintRepository complaintRepository, ComplaintMapper complaintMapper,UserRepository userRepository,UserResponseRepository userResponseRepository) {
    	
    	this.userResponseRepository=userResponseRepository;
        this.complaintRepository = complaintRepository;
        this.complaintMapper = complaintMapper;
        this.userRepository=userRepository;
        
    }

    /**
     * Save a complaint.
     *
     * @param complaintDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ComplaintDTO save(ComplaintDTO complaintDTO) {
        log.debug("Request to save Complaint : {}", complaintDTO);

        Complaint complaint = complaintMapper.toEntity(complaintDTO);
        complaint = complaintRepository.save(complaint);
        return complaintMapper.toDto(complaint);
    }

    /**
     * Get all the complaints.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ComplaintDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Complaints");
        return complaintRepository.findAll(pageable)
            .map(complaintMapper::toDto);
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
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComplaintDTO> findOne(Long id) {
        log.debug("Request to get Complaint : {}", id);
       Optional<Complaint>result=complaintRepository.findOneWithEagerRelationships(id);
       Optional<ComplaintDTO> resultDTO=result.map(complaintMapper::toDto);
       long likes=userResponseRepository.countLikesOfComplaint(result.get());
       log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>noOflike="+likes);
       
       long dislikes=userResponseRepository.countDislikesOfComplaint(result.get());
       log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>noOfdislike="+dislikes);
   
       resultDTO.get().setNoOfLikes(likes);
       resultDTO.get().setNoOfDislikes(dislikes);
        return resultDTO;
    }

    /**
     * Delete the complaint by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Complaint : {}", id);
        complaintRepository.deleteById(id);
    }

    /**
     * Get one complaints of friends by userId.
     *
     * @param id the id of the entity
     * @return the entity
     */
    
    
	@Override
	public Page<ComplaintDTO> findAllComplaintsOfFriends(Pageable pageable, Long userId) {
		
		log.debug("request to get all complaints of friends by userId:"+userId);
		List<ComplaintDTO> list=new ArrayList<ComplaintDTO>(); 
		List<UserRegistrationDTO> users=userRegistrationResourceApi.getAllFriendsUsingGET(userId).getBody();
		complaintRepository.findByUserId(userId,pageable);
		for(UserRegistrationDTO userDTO:users)
		{
			List<ComplaintDTO> complaints=complaintRepository.findByUserId(userDTO.getId(),pageable).map(complaintMapper::toDto).getContent();
			for(ComplaintDTO complaint:complaints)
			{
				complaint.setUserName(userDTO.getFirstName()+" "+userDTO.getLastName());
				log.debug("######################################################"+userId+"###########################"+complaint.getId());
				Optional<UserResponse>optional=userResponseRepository.findUserResponseByUserIdAndComplaintId(userId,complaint.getId());
				log.debug("######################################################"+optional);
				
				Optional<UserResponseDTO>optionalDTO=optional.map(userResponseMapper::toDto);
				if(optionalDTO.isPresent())
				{
					complaint.setUserResponse(optionalDTO.get());
					log.debug("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVv="+optionalDTO.get()+"\n");
				} 
				else
				{
					complaint.setUserResponse(new UserResponseDTO());
				}
				
			}
			list.addAll(complaints);
			
		}
		
		log.debug("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{\n");
		
		Page<ComplaintDTO> pages = new PageImpl<ComplaintDTO>(list, pageable, list.size());
		return pages;
	}
	
	
}
