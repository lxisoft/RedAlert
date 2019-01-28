package com.lxisoft.redalert.repository;

import com.lxisoft.redalert.domain.Post;
import com.lxisoft.redalert.domain.Report;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Report entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

Page<Report> findAllByPost(Post id,Pageable pageable);
}
