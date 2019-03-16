package com.edigitpath.notification.service;

import com.edigitpath.notification.domain.SystemNotification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SystemNotification.
 */
public interface SystemNotificationService {

    /**
     * Save a systemNotification.
     *
     * @param systemNotification the entity to save
     * @return the persisted entity
     */
    SystemNotification save(SystemNotification systemNotification);

    /**
     * Get all the systemNotifications.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SystemNotification> findAll(Pageable pageable);


    /**
     * Get the "id" systemNotification.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SystemNotification> findOne(Long id);

    /**
     * Delete the "id" systemNotification.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
