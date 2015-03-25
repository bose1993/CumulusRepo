package com.cumulus.repo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cumulus.repo.domain.Toc;

/**
 * Spring Data JPA repository for the Toc entity.
 */
public interface TocRepository extends JpaRepository<Toc, Long> {
	Toc findOneByName(String name);

}
