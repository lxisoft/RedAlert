package com.lxisoft.redalert.service.impl;

import com.lxisoft.redalert.service.LeaderBoardService;
import com.lxisoft.redalert.domain.LeaderBoard;
import com.lxisoft.redalert.repository.LeaderBoardRepository;
import com.lxisoft.redalert.service.dto.LeaderBoardDTO;
import com.lxisoft.redalert.service.mapper.LeaderBoardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LeaderBoard.
 */
@Service
@Transactional
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final Logger log = LoggerFactory.getLogger(LeaderBoardServiceImpl.class);

    private final LeaderBoardRepository leaderBoardRepository;

    private final LeaderBoardMapper leaderBoardMapper;

    public LeaderBoardServiceImpl(LeaderBoardRepository leaderBoardRepository, LeaderBoardMapper leaderBoardMapper) {
        this.leaderBoardRepository = leaderBoardRepository;
        this.leaderBoardMapper = leaderBoardMapper;
    }

    /**
     * Save a leaderBoard.
     *
     * @param leaderBoardDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LeaderBoardDTO save(LeaderBoardDTO leaderBoardDTO) {
        log.debug("Request to save LeaderBoard : {}", leaderBoardDTO);

        LeaderBoard leaderBoard = leaderBoardMapper.toEntity(leaderBoardDTO);
        leaderBoard = leaderBoardRepository.save(leaderBoard);
        return leaderBoardMapper.toDto(leaderBoard);
    }

    /**
     * Get all the leaderBoards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LeaderBoardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LeaderBoards");
        return leaderBoardRepository.findAll(pageable)
            .map(leaderBoardMapper::toDto);
    }


    /**
     * Get one leaderBoard by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LeaderBoardDTO> findOne(Long id) {
        log.debug("Request to get LeaderBoard : {}", id);
        return leaderBoardRepository.findById(id)
            .map(leaderBoardMapper::toDto);
    }

    /**
     * Delete the leaderBoard by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LeaderBoard : {}", id);
        leaderBoardRepository.deleteById(id);
    }
}
