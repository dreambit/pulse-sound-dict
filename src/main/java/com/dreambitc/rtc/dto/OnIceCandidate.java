package com.dreambitc.rtc.dto;

import com.dreambitc.rtc.MessageConstants;

public class OnIceCandidate extends Message {

    private User to;
    private IceCandidate candidate;

    public OnIceCandidate(User to, IceCandidate candidate) {
        super(MessageConstants.OUT_MESSAGE_ID_ON_ICE_CANDIDATE);
        this.to = to;
        this.candidate = candidate;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public IceCandidate getCandidate() {
        return candidate;
    }

    public void setCandidate(IceCandidate candidate) {
        this.candidate = candidate;
    }

}
