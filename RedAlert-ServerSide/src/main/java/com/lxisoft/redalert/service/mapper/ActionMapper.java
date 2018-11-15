package com.lxisoft.redalert.service.mapper;

import com.lxisoft.redalert.domain.*;
import com.lxisoft.redalert.service.dto.ActionDTO;

import java.util.Optional;

import org.mapstruct.*;

/**
 * Mapper for the entity Action and its DTO ActionDTO.
 */
@Mapper(componentModel = "spring", uses = {PostMapper.class})
public interface ActionMapper extends EntityMapper<ActionDTO, Action> {

    @Mapping(source = "post.id", target = "postId")
    ActionDTO toDto(Action action);

    @Mapping(source = "postId", target = "post")
    @Mapping(target = "reports", ignore = true)
    Action toEntity(ActionDTO actionDTO);

    default Action fromId(Long id) {
        if (id == null) {
            return null;
        }
        Action action = new Action();
        action.setId(id);
        return action;
    }
}
