package com.dreambitc.rtc.messages;

import com.dreambitc.rtc.MessageConstants;
import com.dreambitc.rtc.dto.RTCSessionDescription;
import com.dreambitc.rtc.dto.User;

public class SDPAnswer extends Message {

    private User to;
    private RTCSessionDescription sdp;

    public SDPAnswer(User to, RTCSessionDescription sdp) {
        super(MessageConstants.IN_OUT_MESSAGE_ID_SDP_ANSWER);
        this.to = to;
        this.sdp = sdp;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public RTCSessionDescription getSdp() {
        return sdp;
    }

    public void setSdp(RTCSessionDescription sdp) {
        this.sdp = sdp;
    }
}
