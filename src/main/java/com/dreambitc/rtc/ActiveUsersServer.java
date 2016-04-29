package com.dreambitc.rtc;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.dreambitc.rtc.decoders.JSONDecoder;
import com.dreambitc.rtc.dto.ActiveUsersList;
import com.dreambitc.rtc.dto.CallAnswer;
import com.dreambitc.rtc.dto.OnIceCandidate;
import com.dreambitc.rtc.dto.IncomingCall;
import com.dreambitc.rtc.dto.IncomingMessage;
import com.dreambitc.rtc.dto.Message;
import com.dreambitc.rtc.dto.SendMessage;
import com.dreambitc.rtc.dto.SetUserId;
import com.dreambitc.rtc.dto.User;
import com.dreambitc.rtc.dto.UserLogin;
import com.dreambitc.rtc.dto.UserLogout;
import com.dreambitc.rtc.encoders.BasicEncoder;

@ServerEndpoint(value = "/users", decoders = { JSONDecoder.class }, encoders = { BasicEncoder.class })
public class ActiveUsersServer {

    @OnOpen
    public void open(Session session) throws IOException {
    }

    @OnClose
    public void close(Session session) throws Exception {
        notifyAllExclusive(session, new UserLogout(ActiveUsersServerHelper.getUser(session)));
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    @OnMessage
    public void handleMessage(Message message, Session session) throws Exception {

        if (message instanceof UserLogin) {
            handleUserLoginMessage((UserLogin) message, session);
        } else if (message instanceof SendMessage) {
            handleSendMessageMessage((SendMessage) message, session);
        } else if (message instanceof IncomingCall) {
            handleIncomingCallMessage((IncomingCall) message, session);
        } else if (message instanceof CallAnswer) {
            handleAnswerCallMessage((CallAnswer) message, session);
        } else if (message instanceof OnIceCandidate) {
            handleIceCandidateMessage((OnIceCandidate) message, session);
        }

    }

    private void handleUserLoginMessage(UserLogin message, Session session) throws Exception {
        message.getUser().setId(UUID.randomUUID().toString());

        session.getUserProperties().put("user", message.getUser());
        session.getBasicRemote().sendObject(new SetUserId(message.getUser()));

        notifyAllExclusive(session, message);
        sendUsersList(session);

    }

    private void handleSendMessageMessage(SendMessage message, Session session) {
        session.getOpenSessions().stream()
                                 .filter((Session s) -> message.getTo().equals(ActiveUsersServerHelper.getUser(s)))
                                 .findFirst()
                                 .ifPresent((Session s) -> ActiveUsersServerHelper.sendMessage(s, IncomingMessage.from(message)));
    }

    private void handleIncomingCallMessage(IncomingCall message, Session session) {
        session.getOpenSessions().stream()
                                 .filter((Session s) -> message.getTo().equals(ActiveUsersServerHelper.getUser(s)))
                                 .findFirst()
                                 .ifPresent((Session s) -> ActiveUsersServerHelper.sendMessage(s, message));
    }

    private void handleIceCandidateMessage(OnIceCandidate message, Session session) {
        session.getOpenSessions().stream()
                                 .filter((Session s) -> message.getTo().equals(ActiveUsersServerHelper.getUser(s)))
                                 .findFirst()
                                 .ifPresent((Session s) -> ActiveUsersServerHelper.sendMessage(s, message));
    }

    private void handleAnswerCallMessage(CallAnswer message, Session session) {
        session.getOpenSessions().stream()
                                 .filter((Session s) -> message.getCaller().equals(ActiveUsersServerHelper.getUser(s)))
                                 .findFirst()
                                 .ifPresent((Session s) -> ActiveUsersServerHelper.sendMessage(s, message));
    }

    private void notifyAllExclusive(Session session, Message message) throws Exception {
        User currentUser = (User) session.getUserProperties().get("user");

        session.getOpenSessions().stream()
                                 .filter((Session s) -> !currentUser.equals(s.getUserProperties().get("user")))
                                 .forEach((Session s) -> ActiveUsersServerHelper.sendMessage(s, message));
    }

    private void sendUsersList(Session session) throws Exception {
        User currentUser = (User) session.getUserProperties().get("user");

        Collection<User> users = session.getOpenSessions().stream()
                                                          .map((Session s) -> (User) s.getUserProperties().get("user"))
                                                          .filter((User u) -> !u.equals(currentUser))
                                                          .collect(Collectors.toList());

        session.getBasicRemote().sendObject(new ActiveUsersList(users));
    }
}
