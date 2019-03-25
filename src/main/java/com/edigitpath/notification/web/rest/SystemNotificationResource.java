package com.edigitpath.notification.web.rest;
import com.edigitpath.notification.domain.SystemNotification;
import com.edigitpath.notification.service.SystemNotificationService;
import com.edigitpath.notification.web.rest.errors.BadRequestAlertException;
import com.edigitpath.notification.web.rest.util.HeaderUtil;
import com.edigitpath.notification.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SystemNotification.
 */
@RestController
@RequestMapping("/api")
public class SystemNotificationResource {

    private final Logger log = LoggerFactory.getLogger(SystemNotificationResource.class);

    private static final String ENTITY_NAME = "systemNotification";

    private final SystemNotificationService systemNotificationService;

    public SystemNotificationResource(SystemNotificationService systemNotificationService) {
        this.systemNotificationService = systemNotificationService;
    }

    /**
     * POST  /system-notifications : Create a new systemNotification.
     *
     * @param systemNotification the systemNotification to create
     * @return the ResponseEntity with status 201 (Created) and with body the new systemNotification, or with status 400 (Bad Request) if the systemNotification has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/system-notifications")
    public ResponseEntity<SystemNotification> createSystemNotification(@Valid @RequestBody SystemNotification systemNotification) throws URISyntaxException {
        log.debug("REST request to save SystemNotification : {}", systemNotification);
        if (systemNotification.getId() != null) {
            throw new BadRequestAlertException("A new systemNotification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SystemNotification result = systemNotificationService.save(systemNotification);
        return ResponseEntity.created(new URI("/api/system-notifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /system-notifications : Updates an existing systemNotification.
     *
     * @param systemNotification the systemNotification to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated systemNotification,
     * or with status 400 (Bad Request) if the systemNotification is not valid,
     * or with status 500 (Internal Server Error) if the systemNotification couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/system-notifications")
    public ResponseEntity<SystemNotification> updateSystemNotification(@Valid @RequestBody SystemNotification systemNotification) throws URISyntaxException {
        log.debug("REST request to update SystemNotification : {}", systemNotification);
        if (systemNotification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SystemNotification result = systemNotificationService.save(systemNotification);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, systemNotification.getId().toString()))
            .body(result);
    }

    /**
     * GET  /system-notifications : get all the systemNotifications.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of systemNotifications in body
     */
    @GetMapping("/system-notifications")
    public ResponseEntity<List<SystemNotification>> getAllSystemNotifications(Pageable pageable) {
        log.debug("REST request to get a page of SystemNotifications");
        Page<SystemNotification> page = systemNotificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/system-notifications");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /system-notifications/:id : get the "id" systemNotification.
     *
     * @param id the id of the systemNotification to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the systemNotification, or with status 404 (Not Found)
     */
    @GetMapping("/system-notifications/{id}")
    public ResponseEntity<SystemNotification> getSystemNotification(@PathVariable Long id) {
        log.debug("REST request to get SystemNotification : {}", id);
        Optional<SystemNotification> systemNotification = systemNotificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(systemNotification);
    }

    /**
     * DELETE  /system-notifications/:id : delete the "id" systemNotification.
     *
     * @param id the id of the systemNotification to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/system-notifications/{id}")
    public ResponseEntity<Void> deleteSystemNotification(@PathVariable Long id) {
        log.debug("REST request to delete SystemNotification : {}", id);
        systemNotificationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
