package com.edigitpath.notification.service.impl;

import com.edigitpath.notification.service.SystemNotificationService;
import com.edigitpath.notification.domain.SystemNotification;
import com.edigitpath.notification.repository.SystemNotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SystemNotification.
 */
@Service
@Transactional
public class SystemNotificationServiceImpl implements SystemNotificationService {

    private final Logger log = LoggerFactory.getLogger(SystemNotificationServiceImpl.class);

    private final SystemNotificationRepository systemNotificationRepository;

    public SystemNotificationServiceImpl(SystemNotificationRepository systemNotificationRepository) {
        this.systemNotificationRepository = systemNotificationRepository;
    }

    /**
     * Save a systemNotification.
     *
     * @param systemNotification the entity to save
     * @return the persisted entity
     */
    @Override
    public SystemNotification save(SystemNotification systemNotification) {
        log.debug("Request to save SystemNotification : {}", systemNotification);
        return systemNotificationRepository.save(systemNotification);
    }

    /**
     * Get all the systemNotifications.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SystemNotification> findAll(Pageable pageable) {
        log.debug("Request to get all SystemNotifications");
        return systemNotificationRepository.findAll(pageable);
    }


    /**
     * Get one systemNotification by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SystemNotification> findOne(Long id) {
        log.debug("Request to get SystemNotification : {}", id);
        return systemNotificationRepository.findById(id);
    }

    /**
     * Delete the systemNotification by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SystemNotification : {}", id);
        systemNotificationRepository.deleteById(id);
    }
}
