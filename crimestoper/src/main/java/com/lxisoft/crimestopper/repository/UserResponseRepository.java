package com.lxisoft.crimestopper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lxisoft.crimestopper.domain.Complaint;
import com.lxisoft.crimestopper.domain.UserResponse;


/**
 * Spring Data  repository for the UserResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {


/**
 * count likes of complaints.
 */
	@Query(value = "select count(u) from UserResponse u where u.complaint"+"=:complaint and u.flag='like'")
	long countLikesOfComplaint(@Param("complaint") Complaint complaint);
	
	/**
	 * count dislikes of complaint
	 */
	@Query(value = "select count(u) from UserResponse u where u.complaint"+"=:complaint and u.flag='dislike'")
	long countDislikesOfComplaint(@Param("complaint") Complaint complaint);

	
	/**
	 * find  an complaint with compliant id and userId
	 */
	
	@Query(value="select ur from UserResponse ur where ur.id=:complaintId and ur.userId=:userId")
	Optional<UserResponse> findUserResponseWithUserIdAndComplaintId(@Param("complaintId")Long complaintId,@Param("userId") Long userId);

	 
}
