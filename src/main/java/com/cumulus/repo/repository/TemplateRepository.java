package com.cumulus.repo.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cumulus.repo.domain.Template;

/**
 * Spring Data JPA repository for the Template entity.
 */
public interface TemplateRepository extends JpaRepository<Template, Long> {

	List<Template> findByXmlid(String id, Sort s);

	Template findByXmlidAndVersion(String xmlid, BigDecimal version);

	@Modifying
	@Query("update Template t set t.master = false where t.xmlid = ?1")
	int resetAllMaster(String xmlId);
}
