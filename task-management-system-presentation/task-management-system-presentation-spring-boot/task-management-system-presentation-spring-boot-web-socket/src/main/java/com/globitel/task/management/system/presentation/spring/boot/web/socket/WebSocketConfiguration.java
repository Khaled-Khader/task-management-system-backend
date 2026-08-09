package com.globitel.task.management.system.presentation.spring.boot.web.socket;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration {

    @Bean
    public WebSocketMessageBrokerConfigurer messageBrokerConfigurer() {
        return new TMSWebSocket();
    }

}
