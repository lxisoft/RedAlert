package com.lxisoft.redalert.repository;

import com.lxisoft.redalert.domain.Post;
import com.lxisoft.redalert.service.dto.PostDTO;
import com.lxisoft.redalert.service.dto.ReportDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Post entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
    //@Query("SELECT * from Post where id = 'id'")
	Optional<Post> findById(Long id);
}
