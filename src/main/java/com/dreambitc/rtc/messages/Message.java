package com.dreambitc.rtc.messages;

public abstract class Message {
    protected String messageId;

    public Message(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

}
