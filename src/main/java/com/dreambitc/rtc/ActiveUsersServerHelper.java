package com.dreambitc.rtc;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import com.dreambitc.rtc.dto.Message;
import com.dreambitc.rtc.dto.User;

public class ActiveUsersServerHelper {

    public static void sendMessage(Session s, Message message) {
        try {
            if (s.isOpen()) {
                s.getBasicRemote().sendObject(message);
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(Session session) {
        return (User) session.getUserProperties().get("user");
    }
}
