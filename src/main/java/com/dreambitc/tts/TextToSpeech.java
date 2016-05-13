package com.dreambitc.tts;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

public abstract class TextToSpeech {

    /**
     * 
     * @param text
     * @param lang
     * @return
     */
    public InputStream tts(String text, Language lang) {
        try {
            URL url = new URL(String.format(getURL(), text, lang.getCode()));
            URLConnection connection = url.openConnection();
            connection.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            return new ByteArrayInputStream(IOUtils.toByteArray(connection));
        } catch (Exception e) {
            throw new RuntimeException("Exception while getting speech from text", e);
        }
    }

    /**
     * 
     * @return
     */
    public abstract String getURL();
}
