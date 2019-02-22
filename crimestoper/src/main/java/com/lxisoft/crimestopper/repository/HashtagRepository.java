package com.lxisoft.crimestopper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lxisoft.crimestopper.domain.Hashtag;


/**
 * Spring Data  repository for the Hashtag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

	Optional<Hashtag> findByName(String searchContent);

}
