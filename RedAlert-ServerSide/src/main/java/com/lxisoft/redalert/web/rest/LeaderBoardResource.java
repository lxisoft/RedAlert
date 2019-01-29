package com.lxisoft.redalert.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.redalert.service.LeaderBoardService;
import com.lxisoft.redalert.web.rest.errors.BadRequestAlertException;
import com.lxisoft.redalert.web.rest.util.HeaderUtil;
import com.lxisoft.redalert.web.rest.util.PaginationUtil;
import com.lxisoft.redalert.service.dto.LeaderBoardDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing LeaderBoard.
 */
@RestController
@RequestMapping("/api")
public class LeaderBoardResource {

    private final Logger log = LoggerFactory.getLogger(LeaderBoardResource.class);

    private static final String ENTITY_NAME = "redAlertLeaderBoard";

    private final LeaderBoardService leaderBoardService;

    public LeaderBoardResource(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    /**
     * POST  /leader-boards : Create a new leaderBoard.
     *
     * @param leaderBoardDTO the leaderBoardDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new leaderBoardDTO, or with status 400 (Bad Request) if the leaderBoard has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/leader-boards")
    @Timed
    public ResponseEntity<LeaderBoardDTO> createLeaderBoard(@RequestBody LeaderBoardDTO leaderBoardDTO) throws URISyntaxException {
        log.debug("REST request to save LeaderBoard : {}", leaderBoardDTO);
        if (leaderBoardDTO.getId() != null) {
            throw new BadRequestAlertException("A new leaderBoard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LeaderBoardDTO result = leaderBoardService.save(leaderBoardDTO);
        return ResponseEntity.created(new URI("/api/leader-boards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /leader-boards : Updates an existing leaderBoard.
     *
     * @param leaderBoardDTO the leaderBoardDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated leaderBoardDTO,
     * or with status 400 (Bad Request) if the leaderBoardDTO is not valid,
     * or with status 500 (Internal Server Error) if the leaderBoardDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/leader-boards")
    @Timed
    public ResponseEntity<LeaderBoardDTO> updateLeaderBoard(@RequestBody LeaderBoardDTO leaderBoardDTO) throws URISyntaxException {
        log.debug("REST request to update LeaderBoard : {}", leaderBoardDTO);
        if (leaderBoardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LeaderBoardDTO result = leaderBoardService.save(leaderBoardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, leaderBoardDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /leader-boards : get all the leaderBoards.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaderBoards in body
     */
    @GetMapping("/leader-boards")
    @Timed
    public ResponseEntity<List<LeaderBoardDTO>> getAllLeaderBoards(Pageable pageable) {
        log.debug("REST request to get a page of LeaderBoards");
        Page<LeaderBoardDTO> page = leaderBoardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/leader-boards");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /leader-boards/:id : get the "id" leaderBoard.
     *
     * @param id the id of the leaderBoardDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the leaderBoardDTO, or with status 404 (Not Found)
     */
    @GetMapping("/leader-boards/{id}")
    @Timed
    public ResponseEntity<LeaderBoardDTO> getLeaderBoard(@PathVariable Long id) {
        log.debug("REST request to get LeaderBoard : {}", id);
        Optional<LeaderBoardDTO> leaderBoardDTO = leaderBoardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(leaderBoardDTO);
    }

    /**
     * DELETE  /leader-boards/:id : delete the "id" leaderBoard.
     *
     * @param id the id of the leaderBoardDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/leader-boards/{id}")
    @Timed
    public ResponseEntity<Void> deleteLeaderBoard(@PathVariable Long id) {
        log.debug("REST request to delete LeaderBoard : {}", id);
        leaderBoardService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
