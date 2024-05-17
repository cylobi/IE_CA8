package org.ie.mizdooni.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ie.mizdooni.model.ReviewModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

// @RestController
// public class ReviewController {
// @GetMapping("/reviews")
// String getAll() {
// try {
// String json = new
// ObjectMapper().writeValueAsString(ReviewModel.getAllObjects());
// return json;
// }
// catch (JsonProcessingException e){
// e.printStackTrace();
// }
// return "Error!";
// }

// @RequestMapping(path="/review/{restname}", method = RequestMethod.GET)
// String getDetails(@PathVariable String restname){
// try {
// String json = new
// ObjectMapper().writeValueAsString(ReviewModel.findByRestaurantName(restname));
// return json;
// }
// catch (JsonProcessingException e){
// e.printStackTrace();
// }
// return "Error!";
// }

// }
