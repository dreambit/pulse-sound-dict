package com.dreambitc.rtc.messages.out;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.User;
import com.dreambitc.rtc.messages.Message;
import com.dreambitc.rtc.messages.in.SendMessage;

public class IncomingMessage extends Message {
    private String message;
    private User from;
    private User to;

    public IncomingMessage(String message, User from, User to) {
        super(MessageConstants.OUT_MESSAGE_ID_INCOMING_MESSAGE);
        this.message = message;
        this.from = from;
        this.to = to;
    }

    public static IncomingMessage from(SendMessage sendMessage) {
        return new IncomingMessage(sendMessage.getMessage(), sendMessage.getFrom(), sendMessage.getTo());
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
