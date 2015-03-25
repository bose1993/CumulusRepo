package com.cumulus.repo.repository;

import com.cumulus.repo.domain.PropertyAttribute;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PropertyAttribute entity.
 */
public interface PropertyAttributeRepository extends JpaRepository<PropertyAttribute,Long> {

}
