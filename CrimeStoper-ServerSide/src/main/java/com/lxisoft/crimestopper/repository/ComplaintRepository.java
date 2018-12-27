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

/**
 * Spring Data  repository for the Complaint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query(value = "select distinct complaint from Complaint complaint left join fetch complaint.departments",
        countQuery = "select count(distinct complaint) from Complaint complaint")
    Page<Complaint> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct complaint from Complaint complaint left join fetch complaint.departments")
    List<Complaint> findAllWithEagerRelationships();

    @Query("select complaint from Complaint complaint left join fetch complaint.departments where complaint.id =:id")
    Optional<Complaint> findOneWithEagerRelationships(@Param("id") Long id);

	/**
	 * @param userId .find all complaints by userId
	 * @param pageable
	 * @return
	 */
	Page<Complaint> findAllComplaintsByUserId(Long userId, Pageable pageable);

}