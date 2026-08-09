package com.globitel.task.management.system.core.notification.factory.domain.request;


import com.globitel.task.management.system.core.user.identity.UserIdentity;

public record CreateNotificationRequest(

        UserIdentity user,
        String action,
        String title,
        String message
) {
}
