package com.lxisoft.redalert.repository;

import com.lxisoft.redalert.domain.Post;
import com.lxisoft.redalert.domain.Report;
import com.lxisoft.redalert.service.dto.PostDTO;
import com.lxisoft.redalert.service.dto.ReportDTO;

import java.util.List;
import java.util.Optional;

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
	 //List<ReportDTO> findAllByPost(PostDTO postDTO);

	Optional<Report> findAllByPost(Post post);
}
