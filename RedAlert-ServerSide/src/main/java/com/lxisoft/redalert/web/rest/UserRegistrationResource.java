package com.lxisoft.redalert.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.redalert.service.UserRegistrationService;
import com.lxisoft.redalert.service.dto.UserRegistrationDTO;
import com.lxisoft.redalert.web.rest.errors.BadRequestAlertException;
import com.lxisoft.redalert.web.rest.util.HeaderUtil;
import com.lxisoft.redalert.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing UserRegistration.
 */
@RestController
@RequestMapping("/apis")
public class UserRegistrationResource {

	private final Logger log = LoggerFactory.getLogger(UserRegistrationResource.class);

	private static final String ENTITY_NAME = "redAlertUserRegistration";

	private final UserRegistrationService userRegistrationService;

	public UserRegistrationResource(UserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
	}

	/**
	 * POST /user-registrations : Create a new userRegistration.
	 *
	 * @param userRegistrationDTO
	 *            the userRegistrationDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         userRegistrationDTO, or with status 400 (Bad Request) if the
	 *         userRegistration has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/user-registrations")
	@Timed
	public ResponseEntity<UserRegistrationDTO> createUserRegistration(
			@RequestBody UserRegistrationDTO userRegistrationDTO) throws URISyntaxException {
		log.debug("REST request to save UserRegistration : {}", userRegistrationDTO);
		if (userRegistrationDTO.getId() != null) {
			throw new BadRequestAlertException("A new userRegistration cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		UserRegistrationDTO result = userRegistrationService.save(userRegistrationDTO);
		return ResponseEntity.created(new URI("/api/user-registrations/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /user-registrations : Updates an existing userRegistration.
	 *
	 * @param userRegistrationDTO
	 *            the userRegistrationDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         userRegistrationDTO, or with status 400 (Bad Request) if the
	 *         userRegistrationDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the userRegistrationDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/user-registrations")
	@Timed
	public ResponseEntity<UserRegistrationDTO> updateUserRegistration(
			@RequestBody UserRegistrationDTO userRegistrationDTO) throws URISyntaxException {
		log.debug("REST request to update UserRegistration : {}", userRegistrationDTO);
		if (userRegistrationDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		UserRegistrationDTO result = userRegistrationService.save(userRegistrationDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userRegistrationDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /user-registrations : get all the userRegistrations.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is applicable
	 *            for many-to-many)
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         userRegistrations in body
	 */
	@GetMapping("/user-registrations")
	@Timed
	public ResponseEntity<List<UserRegistrationDTO>> getAllUserRegistrations(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
		log.debug("REST request to get a page of UserRegistrations");
		Page<UserRegistrationDTO> page;
		if (eagerload) {
			page = userRegistrationService.findAllWithEagerRelationships(pageable);
		} else {
			page = userRegistrationService.findAll(pageable);
		}
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
				String.format("/api/user-registrations?eagerload=%b", eagerload));
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /user-registrations/:id : get the "id" userRegistration.
	 *
	 * @param id
	 *            the id of the userRegistrationDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         userRegistrationDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/user-registrations/{id}")
	@Timed
	public ResponseEntity<UserRegistrationDTO> getUserRegistration(@PathVariable Long id) {
		log.debug("REST request to get UserRegistration : {}", id);
		Optional<UserRegistrationDTO> userRegistrationDTO = userRegistrationService.findOne(id);
		return ResponseUtil.wrapOrNotFound(userRegistrationDTO);
	}

	/**
	 * DELETE /user-registrations/:id : delete the "id" userRegistration.
	 *
	 * @param id
	 *            the id of the userRegistrationDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/user-registrations/{id}")
	@Timed
	public ResponseEntity<Void> deleteUserRegistration(@PathVariable Long id) {
		log.debug("REST request to delete UserRegistration : {}", id);
		userRegistrationService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	@GetMapping("/user-registrations/finduser/{lastName}")
	@Timed
	public ResponseEntity<List<UserRegistrationDTO>> searchWithLastName(@PathVariable String lastName,
			Pageable pageable) {
		log.debug("REST request to find UserRegistration by lastname : {}", lastName);

		Page<UserRegistrationDTO> users = userRegistrationService.findByLastName(lastName, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(users, "/apis/user-registrations");
		return new ResponseEntity<>(users.getContent(), headers, HttpStatus.OK);

	}

	@GetMapping("/user-registrations/find/{userName}")
	@Timed
	public ResponseEntity<UserRegistrationDTO> searchWithUserName(@PathVariable String userName) {
		log.debug("REST request to find UserRegistration by username : {}", userName);
		UserRegistrationDTO user = userRegistrationService.searchByUserName(userName);
		return new ResponseEntity<UserRegistrationDTO>(user, HttpStatus.OK);
	}

	@GetMapping("/user-registrations/findAll/{keyword}")
	@Timed
	public ResponseEntity<List<UserRegistrationDTO>> searchWithFirstNameLastNameEmail(@PathVariable String keyword,
			Pageable pageable) {
		log.debug("REST request to find UserRegistration by keyword : {}", keyword);
		Page<UserRegistrationDTO> users = userRegistrationService.getAllUsersByFirstNameLastNameEmail(keyword,
				pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(users, "/apis/user-registrations");
		return new ResponseEntity<>(users.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping("/user-registrations/findstartcharacter/{charname}")
	@Timed
	public ResponseEntity<List<UserRegistrationDTO>> inputStartingCharacter(@PathVariable String charname,
			Pageable pageable) {
		// log.debug("REST request to find UserRegistration by findFirstCharname :
		// {}",userName);
		Page<UserRegistrationDTO> users = userRegistrationService.getAllFirstNameStartingWith(charname, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(users, "/apis/user-registrations");
		return new ResponseEntity<>(users.getContent(), headers, HttpStatus.OK);

	}

	@GetMapping("/user-registration/{id}")
	@Timed
	public ResponseEntity<UserRegistrationDTO> findByUserId(@PathVariable String id) {
		log.debug("REST request to get UserRegistration using user id : {}", id);
		UserRegistrationDTO userRegistrationDTO = userRegistrationService.findByUserId(id);
		return new ResponseEntity<UserRegistrationDTO>(userRegistrationDTO, HttpStatus.OK);
	}

	@PostMapping("/user-registrations/addFriend/{userId}/{friendId}")
	@Timed
	public void addFriend(@PathVariable Long userId, @PathVariable Long friendId) {
		UserRegistrationDTO user = userRegistrationService.findOne(userId).get();
		UserRegistrationDTO friend = userRegistrationService.findOne(friendId).get();
		user.getFriends().add(friend);
		userRegistrationService.save(user);
	}
<<<<<<< HEAD
	
=======

>>>>>>> 0b1e1a02b3a1c0c834d923537deac2c0b1803a8a
	@PostMapping("/user-registrations/unFriend/{userId}/{friendId}")
	@Timed
	public void unFriend(@PathVariable Long userId, @PathVariable Long friendId) {
		UserRegistrationDTO user = userRegistrationService.findOne(userId).get();
		UserRegistrationDTO friend = userRegistrationService.findOne(friendId).get();
		user.getFriends().remove(friend);
		userRegistrationService.save(user);
	}

	@GetMapping("/user-registrations/getFriends/{userId}")
	@Timed
	public ResponseEntity<Set<UserRegistrationDTO>> getAllFriends(@PathVariable Long userId) {
		UserRegistrationDTO user = userRegistrationService.findOne(userId).get();
		return new ResponseEntity<Set<UserRegistrationDTO>>(user.getFriends(), HttpStatus.OK);
	}
<<<<<<< HEAD
	
=======

	@GetMapping("/user-registration/startcharacter")
	@Timed
	public ResponseEntity<List<UserRegistrationDTO>> inputCharacterContaining(@RequestParam String searchTerm,
			Pageable pageable) {
		Page<UserRegistrationDTO> users = userRegistrationService
				.getAllFirstNameLastNameUserNameContainingIgnoreCase(searchTerm, searchTerm, searchTerm, pageable);

		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(users, "/apis/user-registrations");
		return new ResponseEntity<>(users.getContent(), headers, HttpStatus.OK);

	}

>>>>>>> 0b1e1a02b3a1c0c834d923537deac2c0b1803a8a
}
