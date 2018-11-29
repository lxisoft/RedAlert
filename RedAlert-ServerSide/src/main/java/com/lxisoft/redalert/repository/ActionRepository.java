package com.lxisoft.redalert.repository;

import com.lxisoft.redalert.domain.Action;
import com.lxisoft.redalert.service.dto.ActionDTO;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Action entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

	Page<Action> findAllByPostId(Pageable pageable, Long postId);

}
