package com.dreambitc.rtc.dto;

import com.dreambitc.rtc.MessageConstants;

public class CallAnswer extends Message {
    User caller;
    RTCSessionDescription desc;
    String answer;

    public CallAnswer(User caller, RTCSessionDescription desc, String answer) {
        super(MessageConstants.IN_OUT_MESSAGE_ID_ANSWER_CALL);
        this.caller = caller;
        this.desc = desc;
        this.answer = answer;
    }

    public RTCSessionDescription getDesc() {
        return desc;
    }

    public void setDesc(RTCSessionDescription desc) {
        this.desc = desc;
    }

    public User getCaller() {
        return caller;
    }

    public String getAnswer() {
        return answer;
    }

    public void setCaller(User caller) {
        this.caller = caller;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
