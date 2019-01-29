package com.lxisoft.redalert.repository;

import com.lxisoft.redalert.domain.Action;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Action entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
Page<Action> findAllByPostId(Pageable pageable, Long postId);
}
