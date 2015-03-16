package com.cumulus.repo.repository;

import com.cumulus.repo.domain.Toc;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Toc entity.
 */
public interface TocRepository extends JpaRepository<Toc,Long> {

}
