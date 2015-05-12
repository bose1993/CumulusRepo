package com.cumulus.repo.repository;

import com.cumulus.repo.domain.Accreditlab;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Accreditlab entity.
 */
public interface AccreditlabRepository extends JpaRepository<Accreditlab,Long> {

    @Query("select accreditlab from Accreditlab accreditlab where accreditlab.user.login = ?#{principal.username}")
    List<Accreditlab> findAllForCurrentUser();

}
