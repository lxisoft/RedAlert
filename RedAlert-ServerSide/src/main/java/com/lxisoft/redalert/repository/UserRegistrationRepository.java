package com.lxisoft.redalert.repository;

import com.lxisoft.redalert.domain.User;
import com.lxisoft.redalert.domain.UserRegistration;
import com.lxisoft.redalert.service.dto.UserRegistrationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the UserRegistration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {

    @Query(value = "select distinct user_registration from UserRegistration user_registration left join fetch user_registration.friends",
        countQuery = "select count(distinct user_registration) from UserRegistration user_registration")
    Page<UserRegistration> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct user_registration from UserRegistration user_registration left join fetch user_registration.friends")
    List<UserRegistration> findAllWithEagerRelationships();

    @Query("select user_registration from UserRegistration user_registration left join fetch user_registration.friends where user_registration.id =:id")
    Optional<UserRegistration> findOneWithEagerRelationships(@Param("id") Long id);

    Page<UserRegistration> findAllByFirstName(String firstName, Pageable pageable);
	
    Page<UserRegistration> findAllByLastName(String lastName, Pageable pageable);
	
    Page<UserRegistration> findAllByEmail(String email, Pageable pageable);
	
	@Query("SELECT u FROM UserRegistration u "
			+ "WHERE LOWER(u.firstName) = LOWER(:keyword) "
				+ "OR LOWER(u.lastName) = LOWER(:keyword) "
				+ "OR LOWER(u.email) = LOWER(:keyword)")
	Page<UserRegistration> findByFirstNameLastNameEmail(@Param("keyword") String keyword, Pageable pageable);
	
	UserRegistration findByPassword(String password);
	UserRegistration findByUserName(String userName);
	
	Page<UserRegistration> findAllByFirstNameStartingWith(String firstname, Pageable pageable);

	UserRegistration findByUserId(String id);
	
	Page<UserRegistration> findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrUserNameContainingIgnoreCase(String firstName,String lastName,String userName,Pageable pageable);
	

}
