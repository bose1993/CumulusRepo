package com.cumulus.repo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cumulus.repo.domain.Property;

/**
 * Spring Data JPA repository for the Property entity.
 */
public interface PropertyRepository extends JpaRepository<Property, Long> {
	Property findOneByRules(String rules);
}
