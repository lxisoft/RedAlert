package com.lxisoft.crimestopper.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.crimestopper.service.DepartmentService;
import com.lxisoft.crimestopper.web.rest.errors.BadRequestAlertException;
import com.lxisoft.crimestopper.web.rest.util.HeaderUtil;
import com.lxisoft.crimestopper.web.rest.util.PaginationUtil;
import com.lxisoft.crimestopper.service.dto.DepartmentDTO;
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
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Department.
 */
@RestController
@RequestMapping("/api")
public class DepartmentResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentResource.class);

    private static final String ENTITY_NAME = "crimeStopperDepartment";

    private final DepartmentService departmentService;

    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * POST  /departments : Create a new department.
     *
     * @param departmentDTO the departmentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new departmentDTO, or with status 400 (Bad Request) if the department has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/departments")
    @Timed
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) throws URISyntaxException {
        log.debug("REST request to save Department : {}", departmentDTO);
        if (departmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new department cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepartmentDTO result = departmentService.save(departmentDTO);
        return ResponseEntity.created(new URI("/api/departments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /departments : Updates an existing department.
     *
     * @param departmentDTO the departmentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated departmentDTO,
     * or with status 400 (Bad Request) if the departmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the departmentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/departments")
    @Timed
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO) throws URISyntaxException {
        log.debug("REST request to update Department : {}", departmentDTO);
        if (departmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DepartmentDTO result = departmentService.save(departmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, departmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /departments : get all the departments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of departments in body
     */
    @GetMapping("/departments")
    @Timed
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(Pageable pageable) {
        log.debug("REST request to get a page of Departments");
        Page<DepartmentDTO> page = departmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/departments");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /departments/:id : get the "id" department.
     *
     * @param id the id of the departmentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the departmentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/departments/{id}")
    @Timed
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long id) {
        log.debug("REST request to get Department : {}", id);
        Optional<DepartmentDTO> departmentDTO = departmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(departmentDTO);
    }

    /**
     * DELETE  /departments/:id : delete the "id" department.
     *
     * @param id the id of the departmentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/departments/{id}")
    @Timed
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        log.debug("REST request to delete Department : {}", id);
        departmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/departments?query=:query : search for the department corresponding
     * to the query.
     *
     * @param query the query of the department search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/departments")
    @Timed
    public ResponseEntity<List<DepartmentDTO>> searchDepartments(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Departments for query {}", query);
        Page<DepartmentDTO> page = departmentService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/departments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
