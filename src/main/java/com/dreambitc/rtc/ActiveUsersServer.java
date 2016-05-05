package com.dreambitc.rtc;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.dreambitc.rtc.decoders.JSONDecoder;
import com.dreambitc.rtc.encoders.BasicEncoder;
import com.dreambitc.rtc.messages.Message;
import com.dreambitc.rtc.messages.out.UserLogout;

import static com.dreambitc.rtc.ActiveUsersServerHelper.getUser;
import static com.dreambitc.rtc.ActiveUsersServerHelper.notifyAllExclusive;
import static com.dreambitc.rtc.ActiveUsersServerHelper.getMessageHandler;

@ServerEndpoint(value = "/users", decoders = { JSONDecoder.class }, encoders = { BasicEncoder.class })
public class ActiveUsersServer {

    @OnOpen
    public void open(Session session) throws IOException {
    }

    @OnClose
    public void close(Session session) throws Exception {
        notifyAllExclusive(session, new UserLogout(getUser(session)));
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    @OnMessage
    public void handleMessage(Message message, Session session) {
        getMessageHandler(message.getMessageId()).handle(message, session);
    }
}
