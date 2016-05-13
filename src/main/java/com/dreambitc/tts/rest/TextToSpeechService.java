package com.dreambitc.tts.rest;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.dreambitc.tts.dto.TextToSpeechDTO;

@Path("/")
@Produces({"application/json"})
public class TextToSpeechService {

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response textToSpeech(TextToSpeechDTO textToSpeechDTO) {
        try {
            IOUtils.copy(TextToSpeechHelper.textToSpeech(textToSpeechDTO), new FileOutputStream("Rezvan2"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Response.ok(TextToSpeechHelper.textToSpeech(textToSpeechDTO)).type(MediaType.valueOf("audio/mpeg")).build();
    }
}
