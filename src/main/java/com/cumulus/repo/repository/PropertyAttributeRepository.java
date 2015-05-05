package com.cumulus.repo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cumulus.repo.domain.PropertyAttribute;

/**
 * Spring Data JPA repository for the PropertyAttribute entity.
 */
public interface PropertyAttributeRepository extends
		JpaRepository<PropertyAttribute, Long> {
	PropertyAttribute findOneByProperty_idAndName(Long id, String name);

	List<PropertyAttribute> findByProperty_id(Long pId);
}
