package com.dreambitc.rtc.messages.out;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.User;
import com.dreambitc.rtc.messages.Message;

public class SetUserId extends Message {

    private User user;

    public SetUserId(User user) {
        super(MessageConstants.OUT_MESSAGE_ID_SET_USER_ID);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
