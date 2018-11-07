package com.lxisoft.redalert.service.mapper;

import com.lxisoft.redalert.domain.*;
import com.lxisoft.redalert.service.dto.LeaderBoardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LeaderBoard and its DTO LeaderBoardDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LeaderBoardMapper extends EntityMapper<LeaderBoardDTO, LeaderBoard> {



    default LeaderBoard fromId(Long id) {
        if (id == null) {
            return null;
        }
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard.setId(id);
        return leaderBoard;
    }
}
