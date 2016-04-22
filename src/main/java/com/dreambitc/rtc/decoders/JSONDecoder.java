package com.dreambitc.rtc.decoders;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.IncomingCall;
import com.dreambitc.rtc.dto.Message;
import com.dreambitc.rtc.dto.SendMessage;
import com.dreambitc.rtc.dto.UserLogin;
import com.google.gson.Gson;

public class JSONDecoder implements Decoder.Text<Message> {

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {
    }

    @Override
    public Message decode(String s) throws DecodeException {
        if (s.contains(MessageConstants.IN_OUT_MESSAGE_ID_USER_LOGIN)) {
            return decode(s, UserLogin.class);
        } else if (s.contains(MessageConstants.IN_OUT_MESSAGE_ID_SEND_MESSAGE)) {
            return decode(s, SendMessage.class);
        } else if (s.contains(MessageConstants.IN_OUT_MESSAGE_ID_INCOMING_CALL)) {
            return decode(s, IncomingCall.class);
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
