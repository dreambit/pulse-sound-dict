package com.dreambitc.rtc.dto;

import com.dreambitc.rtc.MessageConstants;

public class UserLogout extends Message {

    private String userName;

    public UserLogout(String userName) {
        super(MessageConstants.IN_OUT_MESSAGE_ID_USER_LOGOUT);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
