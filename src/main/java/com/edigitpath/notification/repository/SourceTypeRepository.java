package com.edigitpath.notification.repository;

import com.edigitpath.notification.domain.SourceType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SourceType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SourceTypeRepository extends JpaRepository<SourceType, Long> {

}