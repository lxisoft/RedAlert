package com.lxisoft.redalert.web.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.redalert.service.PostService;
import com.lxisoft.redalert.service.dto.PostDTO;
import com.lxisoft.redalert.web.rest.errors.BadRequestAlertException;
import com.lxisoft.redalert.web.rest.util.HeaderUtil;
import com.lxisoft.redalert.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import javassist.NotFoundException;

/**
 * REST controller for managing Post.
 */
@RestController
@RequestMapping("/api")
public class PostResource {

    private final Logger log = LoggerFactory.getLogger(PostResource.class);

    private static final String ENTITY_NAME = "redAlertPost";

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    /**
     * POST  /posts : Create a new post.
     *
     * @param postDTO the postDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new postDTO, or with status 400 (Bad Request) if the post has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/posts")
    @Timed
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) throws URISyntaxException {
        log.debug("REST request to save Post : {}", postDTO);
        if (postDTO.getId() != null) {
            throw new BadRequestAlertException("A new post cannot already have an ID", ENTITY_NAME, "idexists");
        }
        System.out.println("#######################################>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> rest method time  "+	postDTO.getCreatedOn());;
        PostDTO result = postService.save(postDTO);
        return ResponseEntity.created(new URI("/api/posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /posts : Updates an existing post.
     *
     * @param postDTO the postDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated postDTO,
     * or with status 400 (Bad Request) if the postDTO is not valid,
     * or with status 500 (Internal Server Error) if the postDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/posts")
    @Timed
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO) throws URISyntaxException {
        log.debug("REST request to update Post : {}", postDTO);
        if (postDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PostDTO result = postService.save(postDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, postDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /posts : get all the posts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of posts in body
     */
    @GetMapping("/posts")
    @Timed
    public ResponseEntity<List<PostDTO>> getAllPosts(Pageable pageable) {
        log.debug("REST request to get a page of Posts");
        Page<PostDTO> page = postService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/posts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    

    /**
     * DELETE  /posts/:id : delete the "id" post.
     *
     * @param id the id of the postDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/posts/{id}")
    @Timed
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        log.debug("REST request to delete Post : {}", id);
        postService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
	
	/**
     * GET  /postsByRegistrationId: : get all the posts by UserRegistration Id.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of posts in body
     * @throws NotFoundException 
     */
    
    
    @GetMapping("/postsByUserRegistrationId/{userRegistrationId}")
    @Timed
    public ResponseEntity<List<PostDTO>> getAllPostsByUserRegistrationId(@PathVariable Long userRegistrationId,Pageable pageable) throws NotFoundException {
        log.debug("REST request to get a page of Posts");
        Page<PostDTO> page = postService.findAllByUserRegistrationId(pageable,userRegistrationId);
        
    
      
       
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/posts");
        
        
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
     
       
        
        
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * GET  /postsByRegistrationId: : get all the posts by UserRegistration Id.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of posts in body
     * @throws NotFoundException 
     */
    @GetMapping("/nonClosedPostsOfFriends/{userRegistrationId}")
    @Timed
    public ResponseEntity<List<PostDTO>> nonClosedPostsOfFriends(@PathVariable Long userRegistrationId,Pageable pageable) throws NotFoundException {
        log.debug("REST request to get a page of Posts");
        Page<PostDTO> page = postService.nonClosedPostsOfFriends(pageable,userRegistrationId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/posts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);    
       
    }
    
    /**
     * GET  /posts/:id : get the "id" post.
     *
     * @param id the id of the postDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the postDTO, or with status 404 (Not Found)
     */
    @GetMapping("/posts/{id}")
    @Timed
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        log.debug("REST request to get Post : {}", id);
        Optional<PostDTO> postDTO = postService.findOne(id);
        return ResponseUtil.wrapOrNotFound(postDTO);
    }
    
	 /**
 * GET  /postclose:id: post is closed by id
 * @param id the id of the PostDTO to close the post
 * @return the ResponseEntity with status 200 (OK) and with body the postDTO, or with status 404 (Not Found)
 */
	 @GetMapping("/postclose/{id}")
	 @Timed
    public ResponseEntity<PostDTO> getClosePost(@PathVariable Long id){
    	   Optional<PostDTO> postDTO=postService.closePost(id);
    
    	return ResponseUtil.wrapOrNotFound(postDTO);
   } 	
  /**
 *  GET /changeAlert:id:alertLevel: change the alertlevel using id	 
 * @param id the id of the PostDTO to change
 * @param alertLevel the enum type of postDTO to change the alertlevel of the post
 * @return the ResponseEntity with status 200 (OK) and with body the postDTO, or with status 404 (Not Found)
 */
	 
   @GetMapping("/changeAlert/{id}/{alertLevel}") 
   @Timed
   public ResponseEntity<PostDTO> changeAlertLevel(@PathVariable Long id,@PathVariable String alertLevel)
   { 
	    Optional<PostDTO>postDTO=Optional.of(postService.changeAlert(id,alertLevel));
	    return ResponseUtil.wrapOrNotFound(postDTO);   
   }
  
   @PostMapping("/posts/mail")
   @Timed
   public String sendMailWithAttachment(@RequestBody PostDTO post) throws MessagingException, IOException,MailException
   {
	   log.info("Mail method");
	   String message = postService.sendMailWithAttachment(post);
	   return message;
   }																									
}