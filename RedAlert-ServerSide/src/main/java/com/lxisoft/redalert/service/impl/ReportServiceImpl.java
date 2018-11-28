package com.lxisoft.redalert.service.impl;

import com.lxisoft.redalert.service.ReportService;
import com.lxisoft.redalert.domain.Post;
import com.lxisoft.redalert.domain.Report;
import com.lxisoft.redalert.repository.PostRepository;
import com.lxisoft.redalert.repository.ReportRepository;
import com.lxisoft.redalert.service.dto.PostDTO;
import com.lxisoft.redalert.service.dto.ReportDTO;
import com.lxisoft.redalert.service.mapper.ReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Report.
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;
    @Autowired
    PostRepository postRepo;
    public ReportServiceImpl(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    /**
     * Save a report.
     *
     * @param reportDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReportDTO save(ReportDTO reportDTO) {
        log.debug("Request to save Report : {}", reportDTO);

        Report report = reportMapper.toEntity(reportDTO);
        report = reportRepository.save(report);
        return reportMapper.toDto(report);
    }

    /**
     * Get all the reports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reports");
        return reportRepository.findAll(pageable)
            .map(reportMapper::toDto);
    }


    /**
     * Get one report by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReportDTO> findOne(Long id) {
        log.debug("Request to get Report : {}", id);
        return reportRepository.findById(id)
            .map(reportMapper::toDto);
    }

    /**
     * Delete the report by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Report : {}", id);
        reportRepository.deleteById(id);
    }



	@Override
	public Optional<ReportDTO> findAllByPost(Long id) {
		// TODO Auto-generated method stub
		Optional<Post> post =postRepo.findById(id);
		
		log.debug("Request to get Report by post : {}", id);
		return reportRepository.findAllByPost(post.get()).map(reportMapper::toDto);
		
	}

/*	@Override
	 @Transactional(readOnly = true)
	public List<ReportDTO> findAllByPostId(Long id) {
		// TODO Auto-generated method stub
		List<PostDTO> post =postRepo.findByPostId(id);
	
		log.debug("Request to get Report by post : {}", id);
		return reportRepository.findAllByPost(post.get()).map(reportMapper::toDto);
				
	}*/
}
