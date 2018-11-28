package com.lxisoft.redalert.repository;

import com.lxisoft.redalert.domain.Post;
import com.lxisoft.redalert.domain.UserRegistration;
import com.lxisoft.redalert.service.dto.ActionDTO;
import com.lxisoft.redalert.service.dto.PostDTO;

import java.util.Optional;

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

	Optional<Page<Post>> findAllByUserRegistration(Pageable pageable, UserRegistration userRegistration);

	

}
