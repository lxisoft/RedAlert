package com.lxisoft.repository;

import com.lxisoft.domain.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

}
