package com.example.mill.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.mill.MillService;


@Configuration
@EnableWebSocket
public class SocketConfiguration implements WebSocketConfigurer {

    private final MillService millService;

    public SocketConfiguration(MillService millService) {
        this.millService = millService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(new SocketHandler(millService), "/ws").setAllowedOrigins("*");
    }
}

