package com.nusrat.onlineBanking.service.sharedService;

import com.nusrat.onlineBanking.entities.sharedEntities.Notification;
import com.nusrat.onlineBanking.repository.shared.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.*;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationsByCustomerId(Long customerId) {
        return notificationRepository.findNotificationByCustomerId(customerId);
    }

    public List<Notification> getNotificationsByEmployeeId(Long employeeId) {
        return notificationRepository.findByEmployee_Id(employeeId);
    }

    public Notification createNotification(Notification notification) {
        notification.setCreatedAt(now());
        notification.setRead(false);
        return notificationRepository.save(notification);
    }

    public Notification markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        return notificationRepository.save(notification);
    }

}
