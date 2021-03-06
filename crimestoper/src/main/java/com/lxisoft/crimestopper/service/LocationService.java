package com.lxisoft.crimestopper.service;

import com.lxisoft.crimestopper.service.dto.LocationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Location.
 */
public interface LocationService {

	/**
	 * Save a location.
	 *
	 * @param locationDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	LocationDTO save(LocationDTO locationDTO);

	/**
	 * Get all the locations.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	Page<LocationDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" location.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	Optional<LocationDTO> findOne(Long id);

	/**
	 * Delete the "id" location.
	 *
	 * @param id
	 *            the id of the entity
	 */
	void delete(Long id);
	
	Optional<LocationDTO> findByComplaintId(long complaintId);

}
