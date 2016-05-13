package com.dreambitc.tts;

public enum Language {
    EN ("en"),
    RU ("ru");

    private final String langCode;

    private Language(String langCode) {
        this.langCode = langCode;
    }

    public String getCode() {
        return langCode;
    }
}
