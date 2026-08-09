package com.globitel.task.management.system.core.notification.factory.respose;

import java.time.Instant;

public record NotificationResponse(
        String uuid,
        String title,
        String message,
        String action,
        Instant createdAt,
        boolean isRead
) {
}
