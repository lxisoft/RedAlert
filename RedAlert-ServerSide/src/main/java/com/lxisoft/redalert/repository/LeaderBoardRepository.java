package com.lxisoft.redalert.repository;

import com.lxisoft.redalert.domain.LeaderBoard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LeaderBoard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaderBoardRepository extends JpaRepository<LeaderBoard, Long> {

}
