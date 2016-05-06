package com.dreambitc.rtc.messages;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.User;

public class CallAnswer extends Message {
    User caller;
    boolean answer;

    public CallAnswer(User caller, boolean answer) {
        super(MessageConstants.IN_OUT_MESSAGE_ID_ANSWER_CALL);
        this.caller = caller;
        this.answer = answer;
    }

    public User getCaller() {
        return caller;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setCaller(User caller) {
        this.caller = caller;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

}
