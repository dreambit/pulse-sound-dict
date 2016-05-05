package com.dreambitc.rtc.messages.in;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.User;
import com.dreambitc.rtc.messages.Message;

public class MakeCall extends Message {
    private User from;
    private User to;

    public MakeCall(User from, User to) {
        super(MessageConstants.IN_MESSAGE_ID_MAKE_CALL);
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
