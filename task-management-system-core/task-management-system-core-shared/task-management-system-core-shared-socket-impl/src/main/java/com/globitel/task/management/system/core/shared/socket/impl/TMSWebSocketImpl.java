package com.globitel.task.management.system.core.shared.socket.impl;


import com.globitel.task.management.system.core.notification.factory.respose.NotificationResponse;
import com.globitel.task.management.system.core.shared.socket.TMSWebSocket;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RequiredArgsConstructor
public class TMSWebSocketImpl implements TMSWebSocket {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendMessage(String userName, NotificationResponse  notificationResponse) {
            messagingTemplate.convertAndSendToUser(
                    userName,
                    "/queue/notifications",
                    notificationResponse);
    }
}
