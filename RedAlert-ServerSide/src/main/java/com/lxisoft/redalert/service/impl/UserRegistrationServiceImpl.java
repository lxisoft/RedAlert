package com.lxisoft.redalert.service.impl;

import com.lxisoft.redalert.service.UserRegistrationService;
import com.lxisoft.redalert.domain.UserRegistration;
import com.lxisoft.redalert.repository.UserRegistrationRepository;
import com.lxisoft.redalert.service.dto.UserRegistrationDTO;
import com.lxisoft.redalert.service.mapper.UserRegistrationMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import com.twilio.type.PhoneNumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing UserRegistration.
 */
@Service
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final Logger log = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

    private final UserRegistrationRepository userRegistrationRepository;

    private final UserRegistrationMapper userRegistrationMapper;

    public UserRegistrationServiceImpl(UserRegistrationRepository userRegistrationRepository, UserRegistrationMapper userRegistrationMapper) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.userRegistrationMapper = userRegistrationMapper;
    }

    /**
     * Save a userRegistration.
     *
     * @param userRegistrationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserRegistrationDTO save(UserRegistrationDTO userRegistrationDTO) {
        log.debug("Request to save UserRegistration : {}", userRegistrationDTO);

        UserRegistration userRegistration = userRegistrationMapper.toEntity(userRegistrationDTO);
        userRegistration = userRegistrationRepository.save(userRegistration);
        return userRegistrationMapper.toDto(userRegistration);
    }

    /**
     * Get all the userRegistrations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserRegistrationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserRegistrations");
        return userRegistrationRepository.findAll(pageable)
            .map(userRegistrationMapper::toDto);
    }

    /**
     * Get all the UserRegistration with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<UserRegistrationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return userRegistrationRepository.findAllWithEagerRelationships(pageable).map(userRegistrationMapper::toDto);
    }
    

    /**
     * Get one userRegistration by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserRegistrationDTO> findOne(Long id) {
        log.debug("Request to get UserRegistration : {}", id);
        return userRegistrationRepository.findOneWithEagerRelationships(id)
            .map(userRegistrationMapper::toDto);
    }

    /**
     * Delete the userRegistration by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserRegistration : {}", id);
        userRegistrationRepository.deleteById(id);
    }
	@Override
	public List<UserRegistration> findAll() {
		return userRegistrationRepository.findAll();	//inbuilt in repository
	}
	
    @Override

    public Page<UserRegistrationDTO> findByLastName(String lastName,Pageable pageable){
    	Page<UserRegistration> users=userRegistrationRepository.findAllByLastName(lastName,pageable);
    	return users.map(userRegistrationMapper::toDto);
    }
    
    

	public Page<UserRegistrationDTO> getAllUsersByFirstName(String firstName, Pageable pageable){
		return userRegistrationRepository.findAllByFirstName(firstName, pageable).map(userRegistrationMapper::toDto);
	}
	
    @Override
	public Page<UserRegistrationDTO> getAllUsersByLastName(String lastName, Pageable pageable){
		return userRegistrationRepository.findAllByLastName(lastName, pageable).map(userRegistrationMapper::toDto);
	}
	
    @Override
	public Page<UserRegistrationDTO> getAllUsersByEmail(String email, Pageable pageable){
		return userRegistrationRepository.findAllByEmail(email, pageable).map(userRegistrationMapper::toDto);
	}

	@Override
	public Page<UserRegistrationDTO> getAllUsersByFirstNameLastNameEmail(String keyword, Pageable pageable) {
		return userRegistrationRepository.findByFirstNameLastNameEmail(keyword, pageable).map(userRegistrationMapper::toDto);
	}

	@Override
	public UserRegistration getUserByPassword(String password) {
		// TODO Auto-generated method stub
		return userRegistrationRepository.findByPassword(password);
	}

	@Override
	public UserRegistrationDTO searchByUserName(String userName) {
		UserRegistration user=userRegistrationRepository.findByUserName(userName);
		if(user==null)
			return null;
		return userRegistrationMapper.toDto(user);
	}
	@Override
	public Page<UserRegistrationDTO> getAllFirstNameStartingWith(String firstname,Pageable pageable) {
		return userRegistrationRepository.findAllByFirstNameStartingWith(firstname,pageable).map(userRegistrationMapper::toDto);
		
	}

	@Override
	public UserRegistrationDTO findByUserId(String id) {
		 log.debug("Request to get UserRegistration using userId: {}", id);
		UserRegistration userRegistration = userRegistrationRepository.findByUserId(id);
	        
		 return userRegistrationMapper.toDto(userRegistration);
	}



	public UserRegistrationDTO sendSMS(Long phoneNo,String userId) {
		// TODO Auto-generated method stub
		final String ACCOUNT_SID = "AC5b7eefb2b599f290036b2780f4815df6";
	    final String AUTH_TOKEN = "5bcfdda6555bd4e769a8807203be423c";
	    final String TWILIO_NUMBER = "+14232265359";
	    
	    
		 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
         Message message = Message.creator(
                 new com.twilio.type.PhoneNumber(Long.toString(phoneNo)),
                new com.twilio.type.PhoneNumber("+14232265359"),
                 "MESSAGE FROM TWILIO")
             .create();
         UserRegistration user=userRegistrationRepository.findByUserId(userId);
		return userRegistrationMapper.toDto(user);

		//return userRegistrationRepository.;
	}
	

	public UserRegistrationDTO validate(String phoneno){
		
		final String ACCOUNT_SID = "AC5b7eefb2b599f290036b2780f4815df6";
	    final String AUTH_TOKEN = "5bcfdda6555bd4e769a8807203be423c";
	    final String TWILIO_NUMBER = "+14232265359";
	    
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	
		 ValidationRequest validationRequest = ValidationRequest.creator(new PhoneNumber(phoneno))
			        .setFriendlyName("Mom Number")
			        .create();
		 UserRegistration user=userRegistrationRepository.findByUserId(phoneno);
			return userRegistrationMapper.toDto(user);

		}

	
	@Override
	public Page<UserRegistrationDTO> getAllFirstNameLastNameUserNameContainingIgnoreCase(String searchTerm,
			String searchTerm2, String searchTerm3, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
	


