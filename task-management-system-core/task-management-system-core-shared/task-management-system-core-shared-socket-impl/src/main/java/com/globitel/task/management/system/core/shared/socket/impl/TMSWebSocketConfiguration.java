package com.globitel.task.management.system.core.shared.socket.impl;

import com.globitel.task.management.system.core.shared.socket.TMSWebSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class TMSWebSocketConfiguration {

    @Bean
    public TMSWebSocket  tmsWebSocket(SimpMessagingTemplate  messagingTemplate) {
        return new TMSWebSocketImpl(messagingTemplate);
    }
}
