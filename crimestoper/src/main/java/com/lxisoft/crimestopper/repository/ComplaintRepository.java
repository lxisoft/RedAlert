package com.lxisoft.crimestopper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lxisoft.crimestopper.domain.Complaint;
import com.lxisoft.crimestopper.domain.Hashtag;

/**
 * Spring Data  repository for the Complaint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query(value = "select distinct complaint from Complaint complaint left join fetch complaint.departments left join fetch complaint.linkedComplaints",
        countQuery = "select count(distinct complaint) from Complaint complaint")
    Page<Complaint> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct complaint from Complaint complaint left join fetch complaint.departments left join fetch complaint.linkedComplaints")
    List<Complaint> findAllWithEagerRelationships();

    @Query("select complaint from Complaint complaint left join fetch complaint.departments left join fetch complaint.linkedComplaints where complaint.id =:id")
    Optional<Complaint> findOneWithEagerRelationships(@Param("id") Long id);

    /**
     * to get complaint of an userId
     * @param id
     * @param pageable
     * @return
     */
    @Query(value="select complaint from  Complaint complaint where complaint.userId=:id")
	Page<Complaint> findByUserId(@Param("id") Long id,Pageable pageable);


    /**
     * to get complaints by hash tag
     * @param id
     * @param pageable
     * @return
     */
   
	Page<Complaint> findAllComplaintsByHashtags(Pageable pageable,Hashtag hashtag);
}
 