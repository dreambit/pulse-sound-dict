package com.dreambitc.rtc.encoders;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class BasicEncoder implements Encoder.Text<Object> {

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(Object object) throws EncodeException {
        return new Gson().toJson(object);
    }

}
