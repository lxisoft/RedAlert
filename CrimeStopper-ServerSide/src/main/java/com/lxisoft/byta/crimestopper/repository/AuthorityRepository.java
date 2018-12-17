package com.lxisoft.byta.crimestopper.repository;

import com.lxisoft.byta.crimestopper.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
