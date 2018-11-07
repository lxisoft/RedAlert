package com.lxisoft.redalert.service.mapper;

import com.lxisoft.redalert.domain.*;
import com.lxisoft.redalert.service.dto.PostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Post and its DTO PostDTO.
 */
@Mapper(componentModel = "spring", uses = {UserRegistrationMapper.class})
public interface PostMapper extends EntityMapper<PostDTO, Post> {

    @Mapping(source = "userRegistration.id", target = "userRegistrationId")
    PostDTO toDto(Post post);

    @Mapping(source = "userRegistrationId", target = "userRegistration")
    @Mapping(target = "actions", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "attatchments", ignore = true)
    Post toEntity(PostDTO postDTO);

    default Post fromId(Long id) {
        if (id == null) {
            return null;
        }
        Post post = new Post();
        post.setId(id);
        return post;
    }
}
