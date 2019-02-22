package com.lxisoft.crimestopper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lxisoft.crimestopper.domain.Location;


/**
 * Spring Data  repository for the Location entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	//Optional<Location> findBylatitudeAndlongitutde(Double latitude, Double longitutde);

}
