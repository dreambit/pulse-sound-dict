package com.dreambitc.rtc;

import java.io.IOException;
import java.util.Collection;
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
import com.dreambitc.rtc.dto.Message;
import com.dreambitc.rtc.dto.SendMessage;
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
        notifyAll(session, new UserLogout((String) session.getUserProperties().get("userName")));
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
        session.getUserProperties().put("userName", message.getUserName());
        notifyAll(session, message);
        sendUsersList(session);

    }

    private void handleSendMessageMessage(SendMessage message, Session session) {
        session.getOpenSessions().stream().filter((Session s) -> {
            return s.getUserProperties().get("userName").equals(message.getTo().getUserName());
        }).findFirst().ifPresent((Session s) -> {
            try {
                s.getBasicRemote().sendObject(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void handleIncomingCallMessage(IncomingCall message, Session session) {
        session.getOpenSessions().stream().filter((Session s) -> {
            return s.getUserProperties().get("userName").equals(message.getTo().getUserName());
        }).findFirst().ifPresent((Session s) -> {
            try {
                s.getBasicRemote().sendObject(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void handleIceCandidateMessage(OnIceCandidate message, Session session) {
        session.getOpenSessions().stream().filter((Session s) -> {
            return s.getUserProperties().get("userName").equals(message.getTo().getUserName());
        }).findFirst().ifPresent((Session s) -> {
            try {
                s.getBasicRemote().sendObject(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void handleAnswerCallMessage(CallAnswer message, Session session) {
        session.getOpenSessions().stream().filter((Session s) -> {
            return s.getUserProperties().get("userName").equals(message.getCaller().getUserName());
        }).findFirst().ifPresent((Session s) -> {
            try {
                s.getBasicRemote().sendObject(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void notifyAll(Session session, Object message) throws Exception {
        for (Session userSession : session.getOpenSessions()) {
            userSession.getBasicRemote().sendObject(message);
        }
    }

    private void sendUsersList(Session session) throws Exception {

        Collection<User> users = session.getOpenSessions().stream().map((Session s) -> {
            return new User((String) s.getUserProperties().get("userName"));
        }).collect(Collectors.toList());

        session.getBasicRemote().sendObject(new ActiveUsersList(users));
    }
}
