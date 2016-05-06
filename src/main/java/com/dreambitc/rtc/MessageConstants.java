package com.dreambitc.rtc;

public interface MessageConstants {
    public static final String IN_OUT_MESSAGE_ID_USER_LOGIN = "USER_LOGIN";
    public static final String IN_OUT_MESSAGE_ID_ANSWER_CALL= "CALL_ANSWER";
    public static final String IN_OUT_MESSAGE_ID_ON_ICE_CANDIDATE = "ICE_CANDIDATE";
    public static final String IN_OUT_MESSAGE_ID_SDP_OFFER = "SDP_OFFER";
    public static final String IN_OUT_MESSAGE_ID_SDP_ANSWER = "SDP_ANSWER";

    public static final String IN_MESSAGE_ID_MAKE_CALL= "MAKE_CALL";
    public static final String IN_MESSAGE_ID_SEND_MESSAGE= "SEND_MESSAGE";

    public static final String OUT_MESSAGE_ID_USER_LOGOUT = "USER_LOGOUT";
    public static final String OUT_MESSAGE_ID_SET_USER_ID = "SET_USER_ID";
    public static final String OUT_MESSAGE_ID_INCOMING_MESSAGE = "INCOMING_MESSAGE";
    public static final String OUT_MESSAGE_ID_USERSLIST= "USERS_LIST";
    public static final String OUT_MESSAGE_ID_INCOMING_CALL= "INCOMING_CALL";
}
