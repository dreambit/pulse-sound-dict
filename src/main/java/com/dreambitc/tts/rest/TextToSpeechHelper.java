package com.dreambitc.tts.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;

import com.dreambitc.tts.GoogleTextToSpeech;
import com.dreambitc.tts.TextToSpeech;
import com.dreambitc.tts.dto.TextToSpeechDTO;

public class TextToSpeechHelper {
    private static TextToSpeech TEXT_TO_SPEECH = new GoogleTextToSpeech();

    public static InputStream textToSpeech(TextToSpeechDTO textToSpeechDTO) {
        Collection<String> sources = textToSpeechDTO.getSources();
        Collection<String> targets = textToSpeechDTO.getTargets();

        ByteArrayOutputStream result = new ByteArrayOutputStream();

        assert sources != null;
        assert targets != null;
        assert sources.size() == targets.size();

        Iterator<String> sourcesIterator = sources.iterator();
        Iterator<String> targetsIterator = targets.iterator();

        while (sourcesIterator.hasNext() && targetsIterator.hasNext()) {

            try {
                String source = URLEncoder.encode(sourcesIterator.next(), "UTF-8");
                String target = URLEncoder.encode(targetsIterator.next(), "UTF-8");
                IOUtils.copy(TEXT_TO_SPEECH.tts(source, textToSpeechDTO.getSourceLang()), result);
                IOUtils.copy(TEXT_TO_SPEECH.tts(target, textToSpeechDTO.getTargetLang()), result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ByteArrayInputStream(result.toByteArray());
    }
}
