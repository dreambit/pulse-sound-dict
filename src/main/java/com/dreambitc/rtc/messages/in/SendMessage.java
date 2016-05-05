package com.dreambitc.rtc.messages.in;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.User;
import com.dreambitc.rtc.messages.Message;

public class SendMessage extends Message {

    private String message;
    private User from;
    private User to;

    public SendMessage(String message, User from, User to) {
        super(MessageConstants.IN_MESSAGE_ID_SEND_MESSAGE);
        this.message = message;
        this.from = from;
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
