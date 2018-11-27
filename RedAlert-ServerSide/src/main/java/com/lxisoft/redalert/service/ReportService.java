package com.lxisoft.redalert.service;

import com.lxisoft.redalert.service.dto.PostDTO;
import com.lxisoft.redalert.service.dto.ReportDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Report.
 */
public interface ReportService {

    /**
     * Save a report.
     *
     * @param reportDTO the entity to save
     * @return the persisted entity
     */
    ReportDTO save(ReportDTO reportDTO);

    /**
     * Get all the reports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ReportDTO> findAll(Pageable pageable);


    /**
     * Get the "id" report.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReportDTO> findOne(Long id);

    /**
     * Delete the "id" report.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
   Optional<ReportDTO> findAllByPost(Long id);

	//Page<ReportDTO> findAllByPostId(Pageable pageable, Long id);
}
