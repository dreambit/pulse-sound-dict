package com.dreambitc.rtc.dto;

public class IceCandidate {
    private String candidate;
    private String sdpMid;
    private int sdpMLineIndex;

    public IceCandidate(String candidate, String sdpMid, int sdpMLineIndex) {
        super();
        this.candidate = candidate;
        this.sdpMid = sdpMid;
        this.sdpMLineIndex = sdpMLineIndex;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getSdpMid() {
        return sdpMid;
    }

    public int getSdpMLineIndex() {
        return sdpMLineIndex;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public void setSdpMid(String sdpMid) {
        this.sdpMid = sdpMid;
    }

    public void setSdpMLineIndex(int sdpMLineIndex) {
        this.sdpMLineIndex = sdpMLineIndex;
    }

}
