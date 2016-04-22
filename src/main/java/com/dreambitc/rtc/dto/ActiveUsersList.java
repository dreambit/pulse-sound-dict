package com.dreambitc.rtc.dto;

import java.util.Collection;

import com.dreambitc.rtc.MessageConstants;

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
