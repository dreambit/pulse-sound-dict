package com.dreambitc.rtc.messages.out;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.User;
import com.dreambitc.rtc.messages.Message;

public class UserLogout extends Message {

    private User user;

    public UserLogout(User user) {
        super(MessageConstants.OUT_MESSAGE_ID_USER_LOGOUT);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
