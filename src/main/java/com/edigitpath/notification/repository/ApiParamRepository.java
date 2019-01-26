package com.edigitpath.notification.repository;

import com.edigitpath.notification.domain.ApiParam;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApiParam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiParamRepository extends JpaRepository<ApiParam, Long> {

}
