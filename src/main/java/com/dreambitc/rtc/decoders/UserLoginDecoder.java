package com.dreambitc.rtc.decoders;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.UserLogin;

public class UserLoginDecoder extends AbstractJSONDecoder<UserLogin> {
    
    public UserLoginDecoder() {
        super(UserLogin.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s.contains(MessageConstants.IN_MESSAGE_ID_JSON_USER_LOGIN);
    }

}
