package com.dreambitc.rtc.dto;

import com.dreambitc.rtc.MessageConstants;

public class IncomingCall extends Message {

    private User from;
    private User to;

    public IncomingCall(User from, User to) {
        super(MessageConstants.IN_OUT_MESSAGE_ID_INCOMING_CALL);
        this.from = from;
        this.to = to;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

}
