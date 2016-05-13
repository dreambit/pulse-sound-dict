package com.dreambitc.tts.rest;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/text-to-speech")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Collections.singleton(TextToSpeechService.class); 
    }
}
