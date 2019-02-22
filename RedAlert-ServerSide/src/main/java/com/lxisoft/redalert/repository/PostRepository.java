package com.lxisoft.redalert.repository;

import com.lxisoft.redalert.domain.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Post entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



	Page<Post> findAllByUserRegistrationId(Pageable pageable, Long userRegistrationId);
	/*Page<Post> findAllByUserRegistrationIdAndActiveIsNull(Pageable pageable, Long userRegistrationId);
	Page<Post> findAllByActiveIsNullOrActiveTrueAndUserRegistrationId(Pageable pageable, Long userRegistrationid);
	Page<Post> findAllByUserRegistrationIdAndActiveNotFalse(Pageable pageable, Long id);
	Page<Post> findAllByUserRegistrationIdAndActiveIsNullOrUserRegistrationIdAndActiveTrue(Pageable pageable, Long id, Long long1);*/
	
	Page<Post> findAllByUserRegistrationIdAndActiveIsNull(Pageable pageable, Long id);

}
