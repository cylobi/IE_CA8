package org.ie.mizdooni.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MizdooniResponse<T> extends ResponseEntity<T> {
    public MizdooniResponse(HttpStatusCode status) {
        super(status);
    }

    static public <T> ResponseEntity<T> jsonBody(T body) {
        try {
            String json = new ObjectMapper().writeValueAsString(body);
            // contentType(MediaType.APPLICATION_JSON);
            // body(reservations);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
