package com.dreambitc.rtc;

import javax.websocket.DecodeException;

import com.dreambitc.rtc.decoders.UserLoginDecoder;
import com.dreambitc.rtc.dto.UserLogin;

public class Main {

    public static void main(String[] args) throws DecodeException {
        UserLoginDecoder d = new UserLoginDecoder();
        UserLogin l = d.decode("{\"messageId\":\"USER_LOGIN\",\"userName\":\"Rezvan\"}");
        System.out.println(l.getUserName());
    }

}
