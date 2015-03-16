package com.cumulus.repo.repository;

import com.cumulus.repo.domain.Property;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Property entity.
 */
public interface PropertyRepository extends JpaRepository<Property,Long> {

}
