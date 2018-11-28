package com.lxisoft.redalert.service;

import com.lxisoft.redalert.service.dto.PostDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Post.
 */
public interface PostService {

    /**
     * Save a post.
     *
     * @param postDTO the entity to save
     * @return the persisted entity
     */
    PostDTO save(PostDTO postDTO);

    /**
     * Get all the posts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PostDTO> findAll(Pageable pageable);


    /**
     * Get the "id" post.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PostDTO> findOne(Long id);

    /**
     * Delete the "id" post.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get all the posts by Registration Id.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Optional<Page<PostDTO>> findAllByUserRegistrationId(Pageable pageable,Long userRegistrationId);




	Optional<PostDTO> closePost(Long id);

	Optional<PostDTO> changeAlert(Long id, String alertLevel);


}
