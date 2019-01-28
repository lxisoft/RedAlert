package com.lxisoft.redalert.repository;

import com.lxisoft.redalert.domain.Media;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Media entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
	
	Page<Media> findAllByPostId(Pageable pageable, Long postId);


}
