package com.lxisoft.redalert.service.impl;

import com.lxisoft.redalert.service.PostService;
import com.lxisoft.redalert.service.UserRegistrationService;
import com.lxisoft.redalert.domain.Post;

import com.lxisoft.redalert.domain.UserRegistration;

import com.lxisoft.redalert.domain.enumeration.Alert;

import com.lxisoft.redalert.repository.PostRepository;
import com.lxisoft.redalert.repository.UserRegistrationRepository;
import com.lxisoft.redalert.service.dto.PostDTO;
import com.lxisoft.redalert.service.mapper.PostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Post.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {
	

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;

    private final PostMapper postMapper;
    
    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    /**
     * Save a post.
     *
     * @param postDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PostDTO save(PostDTO postDTO) {
        log.debug("Request to save Post : {}", postDTO);

        Post post = postMapper.toEntity(postDTO);
        post = postRepository.save(post);
        return postMapper.toDto(post);
    }

    /**
     * Get all the posts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PostDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Posts");
        return postRepository.findAll(pageable)
            .map(postMapper::toDto);
    }


    /**
     * Get one post by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PostDTO> findOne(Long id) {
        log.debug("Request to get Post : {}", id);
        return postRepository.findById(id)
            .map(postMapper::toDto);
    }

    /**
     * Delete the post by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Post : {}", id);
        postRepository.deleteById(id);
    }


    /**
     * Get all the posts By UserRegistrationId.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
	public Page<PostDTO> findAllByUserRegistrationId(Pageable pageable,Long userRegistrationId) {
    	
    	
    	
    
   
   return postRepository.findAllByUserRegistrationId(pageable,userRegistrationId)
                .map(postMapper::toDto);
    }

    
    
    @Override
    public Optional<PostDTO> closePost(Long id)
    {
    	 Optional<Post> optionalPost=postRepository.findById(id);
    	
    	 Post post= optionalPost.get();
    	 post.setActive(false);
   
    	postRepository.save(post);
    	//postRepository.delete(post);
    	optionalPost=postRepository.findById(id);
    	
    	return optionalPost.map(postMapper::toDto);
    
    	
     }
    
    
    public Optional<PostDTO> changeAlert(Long id,String alert)
    {
    	
    	Optional<Post>optionalPost=postRepository.findById(id);
    	//Optional<UserRegistration>optionalUser=userRegistrationRepository.findById(id);
    	
   Page<Post>posts=postRepository.findAllByUserRegistrationId(null,id);
   for(Post post0:posts)
   {
   	if(((Post) post0).getAlertLevel().equals("ORANGE")||((Post) post0).getAlertLevel().equals("GREEN"))
   	{
    	if(alert.equals("RED"))
    	{
    		 Post post= optionalPost.get();
    		post.setAlertLevel(Alert.RED);
    		postRepository.save(post);
    		
    	}
   	
 	
    	else if(alert.equals("GREEN"))
    	{
    		 Post post= optionalPost.get();
    		optionalPost.get().setAlertLevel(Alert.GREEN);
    		postRepository.save(post);
    	}
   	
    	else if(alert.equals("ORANGE"))
    	{
    		 Post post= optionalPost.get();
    	optionalPost.get().setAlertLevel(Alert.ORANGE);
    	postRepository.save(post);
    	}
   	}
   	
   }
   
    	
    	//post.setAlertLevel(alertLevel);
    	//optionalPost=postRepository.findById(id);
    	//return optionalPost.map(postMapper::toDto);
    
   
    optionalPost=postRepository.findById(id);
    posts=postRepository.findAllByUserRegistrationId(null,id);
    
	return optionalPost.map(postMapper::toDto);

    }
}



