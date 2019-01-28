package com.lxisoft.redalert.service.impl;

import com.lxisoft.redalert.service.PostService;
import com.lxisoft.redalert.domain.Post;
import com.lxisoft.redalert.domain.enumeration.Alert;
import com.lxisoft.redalert.repository.PostRepository;
import com.lxisoft.redalert.service.dto.MediaDTO;
import com.lxisoft.redalert.service.dto.PostDTO;
import com.lxisoft.redalert.service.dto.UserRegistrationDTO;
import com.lxisoft.redalert.service.mapper.PostMapper;
import com.lxisoft.redalert.web.rest.MediaResource;
import com.lxisoft.redalert.web.rest.UserRegistrationResource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
private JavaMailSender javaMailSender;
@Autowired
private UserRegistrationResource userRegistrationResource;
@Autowired
private MediaResource mediaResource;
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

    
    /**
     * Get all the posts By postId.
     *
     * 
     * @return the list of entities
     */
    
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
    
    /**
     * Get all the posts By UserRegistrationId.
     *
     * @param id the id of the postdto
     * @param alert 
     * @return the list of entities
     */
    @Override
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
	public String sendMailWithAttachment(PostDTO post) throws MessagingException, IOException, MailException {
		// TODO Auto-generated method stub

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		List<UserRegistrationDTO> users = (List<UserRegistrationDTO>) userRegistrationResource.getAllFriends(post.getUserRegistrationId()).getBody();
		
		List<MediaDTO> medias = mediaResource.getAllMediaByPostId(post.getId(), null, null, null, null, null, null, null, null, null, null).getBody();
        for(UserRegistrationDTO user:users)
        {
		helper.setTo(user.getEmail());
		helper.setSubject("Alert Message");
		helper.setText(post.getDescription());

		//FileSystemResource file = new FileSystemResource("/home/rockhard/Desktop/Registration.pdf");
		if(!medias.isEmpty())
		{
		for(MediaDTO media:medias)
		{
			File file=new File("media.png");
			 FileUtils.writeByteArrayToFile(file, media.getFile());
			helper.addAttachment("emergency.png",file);
		}
		}
		else
		{
			System.out.println("media null");
		}

		javaMailSender.send(mimeMessage);
	
        }
        
		return "Send successfully";
	}
}
