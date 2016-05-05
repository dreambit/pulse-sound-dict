package com.dreambitc.rtc.messages;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.User;

public class UserLogin extends Message {
    private User user;

    public UserLogin(User user) {
        super(MessageConstants.IN_OUT_MESSAGE_ID_USER_LOGIN);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
