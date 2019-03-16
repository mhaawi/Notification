package com.edigitpath.notification.repository;

import com.edigitpath.notification.domain.SystemNotification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SystemNotification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SystemNotificationRepository extends JpaRepository<SystemNotification, Long> {

}
