package com.cumulus.repo.repository;

import com.cumulus.repo.domain.Cm;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Cm entity.
 */
public interface CmRepository extends JpaRepository<Cm,Long> {

}
