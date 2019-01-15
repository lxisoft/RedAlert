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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

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
    	
    	optionalPost=postRepository.findById(id);
    	
    	return optionalPost.map(postMapper::toDto);
    
    	
     }
    
    
    public PostDTO changeAlert(Long id,String alert)
    {
    	
    	Optional<Post>optionalPost=postRepository.findById(id);
    	Post post = null;
    	   
    	if(alert.equals("RED"))
    	{
    		
    		optionalPost.get().setAlertLevel(Alert.RED);
    		post=postRepository.save(optionalPost.get());
    		
    	}
   	
 	
    	else if(alert.equals("GREEN"))
    	{
    		 
    		optionalPost.get().setAlertLevel(Alert.GREEN);
    		post=postRepository.save(optionalPost.get());
    	}
   	
    	else if(alert.equals("ORANGE"))
    	{
    		
    	optionalPost.get().setAlertLevel(Alert.ORANGE);
    	post=postRepository.save(optionalPost.get());
    	}
   	
            return postMapper.toDto(post);

    }

    
    
    
    
    
	@Override
	public Page<PostDTO> nonClosedPostsOfFriends(Pageable pageable, Long userRegistrationId) {
	
		Optional<UserRegistration> userRegistration=userRegistrationRepository.findById(userRegistrationId);
		ArrayList<Post> posts=new ArrayList<Post>();
	
		Set<UserRegistration> friends=userRegistration.get().getFriends();
		
		
		for(UserRegistration friend:friends)
		{
	
			Page<Post> friendPosts=postRepository.findAllByUserRegistrationIdAndActiveIsNull(pageable, friend.getId());
			log.debug("id : "+friend.getId()+"after geting friends post size :");
			if(friendPosts.hasContent())
			{
				for(Post friendPost:friendPosts.getContent())
				{
					posts.add(friendPost);
				
				}
			}
			
			
			
		}
		Page<Post> page=new PageImpl<Post>(posts);
		
		
		
		return page
        .map(postMapper::toDto);
	}
}



