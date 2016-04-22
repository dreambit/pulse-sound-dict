package com.dreambitc.rtc;

import java.io.IOException;

import javax.websocket.Session;

import com.google.gson.Gson;

public class ActiveUsersServerHelper {
    private void notifyAll(Session session, String message) throws IOException {
        for (Session userSession : session.getOpenSessions()) {
            userSession.getBasicRemote().sendText(message);
        }
    }
}
