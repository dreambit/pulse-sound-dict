package com.dreambitc.rtc.decoders;

import java.util.Map;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.messages.CallAnswer;
import com.dreambitc.rtc.messages.Message;
import com.dreambitc.rtc.messages.OnIceCandidate;
import com.dreambitc.rtc.messages.SDPAnswer;
import com.dreambitc.rtc.messages.SDPOffer;
import com.dreambitc.rtc.messages.UserLogin;
import com.dreambitc.rtc.messages.in.MakeCall;
import com.dreambitc.rtc.messages.in.SendMessage;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

public class JSONDecoder implements Decoder.Text<Message> {

    private static final Map<String, Class<? extends Message>> MESSAGES = new ImmutableMap.Builder<String, Class<? extends Message>>()
                                                                              .put(MessageConstants.IN_OUT_MESSAGE_ID_USER_LOGIN, UserLogin.class)
                                                                              .put(MessageConstants.IN_MESSAGE_ID_SEND_MESSAGE, SendMessage.class)
                                                                              .put(MessageConstants.IN_MESSAGE_ID_MAKE_CALL, MakeCall.class)
                                                                              .put(MessageConstants.IN_OUT_MESSAGE_ID_ANSWER_CALL, CallAnswer.class)
                                                                              .put(MessageConstants.IN_OUT_MESSAGE_ID_ON_ICE_CANDIDATE, OnIceCandidate.class)
                                                                              .put(MessageConstants.IN_OUT_MESSAGE_ID_SDP_OFFER, SDPOffer.class)
                                                                              .put(MessageConstants.IN_OUT_MESSAGE_ID_SDP_ANSWER, SDPAnswer.class)
                                                                              .build();

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {
    }

    @Override
    public Message decode(String s) throws DecodeException {
        for (String message : MESSAGES.keySet()) {
            if (s.contains(message)) {
                return decode(s, MESSAGES.get(message));
            }
        }
        return null;

    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    private Message decode(String message, Class<? extends Message> cl) {
        return new Gson().fromJson(message, cl);
    }

}
