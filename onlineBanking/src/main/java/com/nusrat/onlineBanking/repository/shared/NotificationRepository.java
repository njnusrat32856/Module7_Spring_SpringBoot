package com.nusrat.onlineBanking.repository.shared;

import com.nusrat.onlineBanking.entities.sharedEntities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findNotificationByCustomerId(long customer_Id);

    List<Notification> findByEmployee_Id(long employeeId);

//    List<Notification> findByCustomerId(long customerId, Pageable pageable);
}
