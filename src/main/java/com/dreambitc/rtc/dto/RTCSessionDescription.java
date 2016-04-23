package com.dreambitc.rtc.dto;

public class RTCSessionDescription {
    String type;
    String sdp;

    public String getType() {
        return type;
    }

    public String getSdp() {
        return sdp;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

}
