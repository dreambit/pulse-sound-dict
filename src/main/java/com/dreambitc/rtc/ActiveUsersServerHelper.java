package com.dreambitc.rtc;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import com.dreambitc.rtc.dto.User;
import com.dreambitc.rtc.messages.CallAnswer;
import com.dreambitc.rtc.messages.Message;
import com.dreambitc.rtc.messages.OnIceCandidate;
import com.dreambitc.rtc.messages.SDPAnswer;
import com.dreambitc.rtc.messages.SDPOffer;
import com.dreambitc.rtc.messages.UserLogin;
import com.dreambitc.rtc.messages.in.MakeCall;
import com.dreambitc.rtc.messages.in.SendMessage;
import com.dreambitc.rtc.messages.out.ActiveUsersList;
import com.dreambitc.rtc.messages.out.IncomingCall;
import com.dreambitc.rtc.messages.out.IncomingMessage;
import com.dreambitc.rtc.messages.out.SetUserId;
import com.google.common.collect.ImmutableMap;
import static com.dreambitc.rtc.MessageConstants.IN_OUT_MESSAGE_ID_USER_LOGIN;
import static com.dreambitc.rtc.MessageConstants.IN_MESSAGE_ID_MAKE_CALL;
import static com.dreambitc.rtc.MessageConstants.IN_MESSAGE_ID_SEND_MESSAGE;
import static com.dreambitc.rtc.MessageConstants.IN_OUT_MESSAGE_ID_ANSWER_CALL;
import static com.dreambitc.rtc.MessageConstants.IN_OUT_MESSAGE_ID_ON_ICE_CANDIDATE;
import static com.dreambitc.rtc.MessageConstants.IN_OUT_MESSAGE_ID_SDP_OFFER;
import static com.dreambitc.rtc.MessageConstants.IN_OUT_MESSAGE_ID_SDP_ANSWER;

public class ActiveUsersServerHelper {

    private static Map<String, MessageHandler> HANDLERS = new ImmutableMap.Builder<String, MessageHandler>()
                                                              .put(IN_OUT_MESSAGE_ID_USER_LOGIN, ActiveUsersServerHelper::handleUserLoginMessage)
                                                              .put(IN_MESSAGE_ID_MAKE_CALL, ActiveUsersServerHelper::handleMakeCallMessage)
                                                              .put(IN_MESSAGE_ID_SEND_MESSAGE, ActiveUsersServerHelper::handleSendMessage)
                                                              .put(IN_OUT_MESSAGE_ID_ANSWER_CALL, ActiveUsersServerHelper::handleCallAnswerMessage)
                                                              .put(IN_OUT_MESSAGE_ID_ON_ICE_CANDIDATE, ActiveUsersServerHelper::handleIceCandidateMessage)
                                                              .put(IN_OUT_MESSAGE_ID_SDP_OFFER, ActiveUsersServerHelper::handleSDPOfferMessage)
                                                              .put(IN_OUT_MESSAGE_ID_SDP_ANSWER, ActiveUsersServerHelper::handleSDPAnswerMessage)
                                                              .build();

    public static MessageHandler getMessageHandler(String messageId) {
        return HANDLERS.getOrDefault(messageId, ActiveUsersServerHelper::emptyMessageHandler);
    }

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

    public static void notifyAllExclusive(Session session, Message message) throws Exception {
        User currentUser = (User) session.getUserProperties().get("user");

        session.getOpenSessions().stream()
                                 .filter((Session s) -> !currentUser.equals(s.getUserProperties().get("user")))
                                 .forEach((Session s) -> ActiveUsersServerHelper.sendMessage(s, message));
    }

    public static void sendUsersList(Session session) throws Exception {
        User currentUser = (User) session.getUserProperties().get("user");

        Collection<User> users = session.getOpenSessions().stream()
                                                          .map((Session s) -> (User) s.getUserProperties().get("user"))
                                                          .filter((User u) -> !u.equals(currentUser))
                                                          .collect(Collectors.toList());

        session.getBasicRemote().sendObject(new ActiveUsersList(users));
    }

    private static void emptyMessageHandler(Message message, Session session) {
        System.out.println("Not supported message: " + message);
    }

    private static void handleUserLoginMessage(Message message, Session session) {
        try {
            UserLogin userLoginMessage = (UserLogin) message;
            userLoginMessage.getUser().setId(UUID.randomUUID().toString());
    
            session.getUserProperties().put("user", userLoginMessage.getUser());
            session.getBasicRemote().sendObject(new SetUserId(userLoginMessage.getUser()));
            notifyAllExclusive(session, message);
            sendUsersList(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleMakeCallMessage(Message message, Session session) {
        MakeCall makeCall = (MakeCall) message;
        
        session.getOpenSessions().stream()
                                 .filter((Session s) -> makeCall.getTo().equals(getUser(s)))
                                 .findFirst()
                                 .ifPresent((Session s) -> sendMessage(s, IncomingCall.from(makeCall)));
    }

    private static void handleSendMessage(Message message, Session session) {
        SendMessage sendMessage = (SendMessage) message;

        session.getOpenSessions().stream()
                                 .filter((Session s) -> sendMessage.getTo().equals(getUser(s)))
                                 .findFirst()
                                 .ifPresent((Session s) -> sendMessage(s, IncomingMessage.from(sendMessage)));
    }

    private static void handleCallAnswerMessage(Message message, Session session) {
        CallAnswer callAnswer = (CallAnswer) message;

        session.getOpenSessions().stream()
                                 .filter((Session s) -> callAnswer.getCaller().equals(getUser(s)))
                                 .findFirst()
                                 .ifPresent((Session s) -> sendMessage(s, callAnswer));
    }

    private static void handleIceCandidateMessage(Message message, Session session) {
        OnIceCandidate onIceCandidate = (OnIceCandidate) message;

        session.getOpenSessions().stream()
                                 .filter((Session s) -> onIceCandidate.getTo().equals(getUser(s)))
                                 .findFirst()
                                 .ifPresent((Session s) -> sendMessage(s, onIceCandidate));
    }

    private static void handleSDPOfferMessage(Message message, Session session) {
        SDPOffer sdpOffer = (SDPOffer) message;

        session.getOpenSessions().stream()
                                 .filter((Session s) -> sdpOffer.getTo().equals(getUser(s)))
                                 .findFirst()
                                 .ifPresent((Session s) -> sendMessage(s, sdpOffer));
    }
    
    private static void handleSDPAnswerMessage(Message message, Session session) {
        SDPAnswer sdpAnswer = (SDPAnswer) message;

        session.getOpenSessions().stream()
                                 .filter((Session s) -> sdpAnswer.getTo().equals(getUser(s)))
                                 .findFirst()
                                 .ifPresent((Session s) -> sendMessage(s, sdpAnswer));
    }
}
