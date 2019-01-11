package com.edigitpath.notification.service;

import com.edigitpath.notification.domain.ApiParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ApiParam.
 */
public interface ApiParamService {

    /**
     * Save a apiParam.
     *
     * @param apiParam the entity to save
     * @return the persisted entity
     */
    ApiParam save(ApiParam apiParam);

    /**
     * Get all the apiParams.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ApiParam> findAll(Pageable pageable);


    /**
     * Get the "id" apiParam.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ApiParam> findOne(Long id);

    /**
     * Delete the "id" apiParam.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
