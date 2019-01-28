package com.lxisoft.redalert.service;

import com.lxisoft.redalert.domain.UserRegistration;
import com.lxisoft.redalert.service.dto.UserRegistrationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing UserRegistration.
 */
public interface UserRegistrationService {

    /**
     * Save a userRegistration.
     *
     * @param userRegistrationDTO the entity to save
     * @return the persisted entity
     */
    UserRegistrationDTO save(UserRegistrationDTO userRegistrationDTO);

    /**
     * Get all the userRegistrations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserRegistrationDTO> findAll(Pageable pageable);

    /**
     * Get all the UserRegistration with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<UserRegistrationDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" userRegistration.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UserRegistrationDTO> findOne(Long id);

    /**
     * Delete the "id" userRegistration.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
	Page<UserRegistrationDTO> findByLastName(String lastName,Pageable pageable);

	List<UserRegistration> findAll();
	
	Page<UserRegistrationDTO> getAllUsersByFirstName(String firstName, Pageable pageable);
	
	Page<UserRegistrationDTO> getAllUsersByLastName(String lastName, Pageable pageable);
	
	Page<UserRegistrationDTO> getAllUsersByEmail(String email, Pageable pageable);

	Page<UserRegistrationDTO> getAllUsersByFirstNameLastNameEmail(String keyword, Pageable pageable);
	
	UserRegistrationDTO searchByUserName(String userName);
	
	UserRegistration getUserByPassword(String password);
	
	Page<UserRegistrationDTO> getAllFirstNameStartingWith(String firstname,Pageable pageable);

	UserRegistrationDTO findByUserId(String id);

	UserRegistrationDTO sendSMS(String phoneno, String userId);

	UserRegistrationDTO validate(String phoneno);

	Page<UserRegistrationDTO> getAllFirstNameLastNameUserNameContainingIgnoreCase(String searchTerm, String searchTerm2,
			String searchTerm3, Pageable pageable);

}
