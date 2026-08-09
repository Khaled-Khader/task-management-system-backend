package com.globitel.task.management.system.core.notification.usecase.error;

import com.globitel.task.management.system.core.shared.exception.data.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationError implements ErrorMessage {

    NOTIFICATION_NOT_FOUND("TMS-NTF-UC-1000", "Notification not found"),

    ;
    private final String code;
    private final String message;
}
