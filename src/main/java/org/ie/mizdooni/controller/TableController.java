package org.ie.mizdooni.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ie.mizdooni.model.TableModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public class TableController {

    @GetMapping("/tables")
    String getAll() {
        try {
            String json = new ObjectMapper().writeValueAsString(TableModel.getAllObjects());
            return json;
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return "Error!";


    }
}
