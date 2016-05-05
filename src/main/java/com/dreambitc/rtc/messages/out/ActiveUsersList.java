package com.dreambitc.rtc.messages.out;

import java.util.Collection;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.User;
import com.dreambitc.rtc.messages.Message;

public class ActiveUsersList extends Message {

    Collection<User> users;

    public ActiveUsersList(Collection<User> users) {
        super(MessageConstants.OUT_MESSAGE_ID_USERSLIST);
        this.users = users;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

}
