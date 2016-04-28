package com.dreambitc.rtc.dto;

import com.dreambitc.rtc.MessageConstants;

public class UserLogout extends Message {

    private User user;

    public UserLogout(User user) {
        super(MessageConstants.IN_OUT_MESSAGE_ID_USER_LOGOUT);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
