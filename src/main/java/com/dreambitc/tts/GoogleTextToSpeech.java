package com.dreambitc.tts;

public class GoogleTextToSpeech extends TextToSpeech {
    private static final String URL = "http://translate.google.com/translate_tts?ie=UTF-8&q=%s&tl=%s&client=tw-ob";

    @Override
    public String getURL() {
        return URL;
    }
}
