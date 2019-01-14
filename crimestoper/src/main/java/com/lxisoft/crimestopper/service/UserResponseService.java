package com.lxisoft.crimestopper.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lxisoft.crimestopper.service.dto.UserResponseDTO;

/**
 * Service Interface for managing UserResponse.
 */
/**
 * @author sooraj pn
 *
 */
/**
 * @author sooraj pn
 *
 */
public interface UserResponseService {

    /**
     * Save a userResponse.
     *
     * @param userResponseDTO the entity to save
     * @return the persisted entity
     */
    UserResponseDTO save(UserResponseDTO userResponseDTO);

    /**
     * Get all the userResponses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserResponseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userResponse.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UserResponseDTO> findOne(Long id);

    /**
     * Delete the "id" userResponse.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
   
	/**
	 * like an complaint
	 * @param id
	 */
	void likeComplaint(Long id);
	
}
