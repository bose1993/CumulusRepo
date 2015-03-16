package com.cumulus.repo.repository;

import com.cumulus.repo.domain.Template;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Template entity.
 */
public interface TemplateRepository extends JpaRepository<Template,Long> {

}
