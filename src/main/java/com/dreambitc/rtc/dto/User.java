package com.dreambitc.rtc.dto;

import java.util.Objects;

public class User {
    private String id;
    private String userName;

    public User(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return Objects.equals(id, user.getId());
    }

}
