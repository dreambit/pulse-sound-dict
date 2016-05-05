package com.dreambitc.rtc.messages.out;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.User;
import com.dreambitc.rtc.messages.Message;
import com.dreambitc.rtc.messages.in.MakeCall;

public class IncomingCall extends Message {

    private User from;

    public IncomingCall(User from) {
        super(MessageConstants.OUT_MESSAGE_ID_INCOMING_CALL);
        this.from = from;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public static IncomingCall from(MakeCall makeCall) {
        return new IncomingCall(makeCall.getFrom());
    }

}
