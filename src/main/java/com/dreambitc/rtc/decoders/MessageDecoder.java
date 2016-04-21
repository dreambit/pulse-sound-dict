package com.dreambitc.rtc.decoders;

import java.io.Reader;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<JsonObject> {

    @Override
    public void destroy() {
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public JsonObject decode(String s) throws DecodeException {
        JsonObject message = null;

        try (Reader json = new StringReader(s);
             JsonReader reader = Json.createReader(json)) {

            message = reader.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

}
