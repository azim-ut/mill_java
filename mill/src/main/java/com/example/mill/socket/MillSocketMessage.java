package com.example.mill.socket;

import org.springframework.web.socket.WebSocketMessage;

import com.example.mill.MillService;
import com.example.mill.bean.MillState;

public class MillSocketMessage implements WebSocketMessage<MillState> {

    private final MillService millService;

    public MillSocketMessage(MillService millService) {
        this.millService = millService;
    }

    @Override
    public MillState getPayload() {
        return millService.getState();
    }

    @Override
    public int getPayloadLength() {
        return millService.getState().toString().length();
    }

    @Override
    public boolean isLast() {
        return false;
    }
}
