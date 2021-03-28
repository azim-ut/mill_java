package com.example.mill.socket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.mill.MillService;
import com.google.gson.Gson;

@Component
public class SocketHandler extends TextWebSocketHandler {
    private final MillService millService;
    private final Gson gson = new Gson();

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketHandler.class);

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public SocketHandler(MillService millService) {
        this.millService = millService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage msg = new TextMessage(gson.toJson(millService.getState()));
        sessions.forEach(webSocketSession -> {
            try {
                webSocketSession.sendMessage(msg);
            } catch (IOException e) {
                LOGGER.error("Error occurred.", e);
            }
        });
    }
}
