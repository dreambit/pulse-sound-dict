package com.dreambitc.rtc.decoders;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public abstract class AbstractJSONDecoder<T> implements Decoder.Text<T> {

    private Class<T> typeParameterClass;

    protected AbstractJSONDecoder(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public T decode(String s) throws DecodeException {
       return new Gson().fromJson(s, typeParameterClass);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(EndpointConfig config) {
    }
}
