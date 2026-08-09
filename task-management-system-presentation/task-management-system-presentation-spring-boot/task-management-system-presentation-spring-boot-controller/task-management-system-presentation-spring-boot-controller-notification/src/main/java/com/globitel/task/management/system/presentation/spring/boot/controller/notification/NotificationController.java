package com.globitel.task.management.system.presentation.spring.boot.controller.notification;

import com.globitel.task.management.system.core.notification.factory.NotificationUseCaseFactory;
import com.globitel.task.management.system.core.notification.factory.respose.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationUseCaseFactory notificationUseCaseFactory;

    @GetMapping
    public List<NotificationResponse> findAllUserNotificationUseCase() {
        return notificationUseCaseFactory.findAllUserNotificationUseCase().execute();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("mark/all")
    public void markAllAsReadUseCase(@RequestBody String userUuid) {
        notificationUseCaseFactory.markAllAsReadUseCase().execute(userUuid);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("mark/one")
    public void markNotificationAsRead(@RequestBody String notificationId ) {
        notificationUseCaseFactory.markNotificationAsReadUseCase().execute(notificationId);
    }
}
