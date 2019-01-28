package com.lxisoft.redalert.service;

import com.lxisoft.redalert.service.dto.LeaderBoardDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LeaderBoard.
 */
public interface LeaderBoardService {

    /**
     * Save a leaderBoard.
     *
     * @param leaderBoardDTO the entity to save
     * @return the persisted entity
     */
    LeaderBoardDTO save(LeaderBoardDTO leaderBoardDTO);

    /**
     * Get all the leaderBoards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LeaderBoardDTO> findAll(Pageable pageable);


    /**
     * Get the "id" leaderBoard.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LeaderBoardDTO> findOne(Long id);

    /**
     * Delete the "id" leaderBoard.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
