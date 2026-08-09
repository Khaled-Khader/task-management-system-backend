package com.globitel.task.management.system.core.shared.socket;

import com.globitel.task.management.system.core.notification.factory.respose.NotificationResponse;

public interface TMSWebSocket {

    void sendMessage(String message, NotificationResponse  response);
}
