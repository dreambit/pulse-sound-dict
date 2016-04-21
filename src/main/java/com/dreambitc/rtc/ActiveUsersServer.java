package com.dreambitc.rtc;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.dreambitc.rtc.decoders.UserLoginDecoder;
import com.dreambitc.rtc.dto.UserLogin;

@ServerEndpoint(value = "/users", decoders = { UserLoginDecoder.class })
public class ActiveUsersServer {

    private static final String OUT_MESSAGE_USER_LEFT = "{ \"messageId\": \"USER_LEFT\", \"userName\": \"%s\" }";

    @OnOpen
    public void open(Session session) throws IOException {
    }

    @OnClose
    public void close(Session session) throws IOException {
        notifyAll(session, String.format(OUT_MESSAGE_USER_LEFT, session.getUserProperties().get("userName")));
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    @OnMessage
    public void handleMessage(UserLogin message, Session session) throws IOException {
        session.getUserProperties().put("userName", message.getUserName());
    }

    private void hanldeGetUsersMessage(Session session) throws IOException {
        JsonArrayBuilder users = Json.createArrayBuilder();
        JsonObjectBuilder message = Json.createObjectBuilder();

        message.add("messageId", "USERS_LIST");

        for (Session s : session.getOpenSessions()) {
            users.add(Json.createObjectBuilder().add("userName", (String) s.getUserProperties().get("userName")));
        }
        message.add("users", users);
        String json = message.build().toString();

        System.out.println(json);

        session.getBasicRemote().sendText(json);
    }

    private void notifyAll(Session session, String message) throws IOException {
        for (Session userSession : session.getOpenSessions()) {
            userSession.getBasicRemote().sendText(message);
        }
    }
}
