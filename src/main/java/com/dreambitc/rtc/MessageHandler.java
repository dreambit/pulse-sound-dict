package com.dreambitc.rtc;

import javax.websocket.Session;

import com.dreambitc.rtc.messages.Message;

@FunctionalInterface
public interface MessageHandler {
    public void handle(Message message, Session session);
}
