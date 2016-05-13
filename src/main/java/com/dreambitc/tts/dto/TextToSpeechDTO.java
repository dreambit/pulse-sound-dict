package com.dreambitc.tts.dto;

import java.util.Collection;

import com.dreambitc.tts.Language;

public class TextToSpeechDTO {

    private Language sourceLang;
    private Language targetLang;

    private Collection<String> sources;
    private Collection<String> targets;

    public Language getSourceLang() {
        return sourceLang;
    }

    public void setSourceLang(Language sourceLang) {
        this.sourceLang = sourceLang;
    }

    public Language getTargetLang() {
        return targetLang;
    }

    public void setTargetLang(Language targetLang) {
        this.targetLang = targetLang;
    }

    public Collection<String> getSources() {
        return sources;
    }

    public void setSources(Collection<String> sources) {
        this.sources = sources;
    }

    public Collection<String> getTargets() {
        return targets;
    }

    public void setTargets(Collection<String> targets) {
        this.targets = targets;
    }

}
