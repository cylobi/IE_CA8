package org.ie.mizdooni.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ie.mizdooni.model.RestaurantModel;
import org.ie.mizdooni.model.ReviewModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

class RestaurantSearchInfo {
    public String name, type, city;
}

// @RestController
// public class RestaurantController {
// String getAll() {
// try {
// return buildJsonList(RestaurantModel.getAllObjects());
// } catch (JsonProcessingException e) {
// e.printStackTrace();
// }
// return "Error!";
// }

// @GetMapping("/restaurants/all_cities")
// String getAllCities() {
// try {
// Set<String> cities = new LinkedHashSet<String>();
// for (var rest : RestaurantModel.getAllObjects()) {
// cities.add(
// rest.getCity());
// }
// var elements = cities.toArray();
// String json = new ObjectMapper().writeValueAsString(elements);
// return json;
// } catch (JsonProcessingException e) {
// e.printStackTrace();
// }
// return "Error!";
// }

// @GetMapping("/restaurants/all_types")
// String getAllTypes() {
// try {
// Set<String> cities = new LinkedHashSet<String>();
// for (var rest : RestaurantModel.getAllObjects()) {
// cities.add(rest.getType());
// }
// var elements = cities.toArray();
// String json = new ObjectMapper().writeValueAsString(elements);
// return json;
// } catch (JsonProcessingException e) {
// e.printStackTrace();
// }
// return "Error!";
// }

// @GetMapping("/restaurants/best_ones")
// String getBestOnes(@RequestParam(name = "count", defaultValue = "6") int
// count) {
// try {
// var restaurants = RestaurantModel.getAllObjects();
// Map<String, Float> ratingMap = new HashMap<>();
// for (var rest : restaurants) {
// ratingMap.put(rest.getName(),
// ReviewModel.calculateAverageForRestaurant(rest.getName()).getOverallRate());
// }
// float defaultRating = 0;
// var bestOnes = restaurants.stream().sorted(
// (r1, r2) -> ratingMap.getOrDefault(r1.getName(), defaultRating) > ratingMap
// .getOrDefault(r2.getName(), defaultRating)
// ? -1
// : 1)
// .limit(count).toList();

// return buildJsonList(bestOnes);
// } catch (JsonProcessingException e) {
// e.printStackTrace();
// }
// return "Error!";
// }

// @GetMapping("/restaurants/recommend")
// public String getRecommentedOnes(@RequestParam(name = "count", defaultValue =
// "6") int count) {
// var restaurants = RestaurantModel.getAllObjects();
// var list = restaurants.subList(0, Math.min(count, restaurants.size()));
// try {
// return buildJsonList(list);
// } catch (JsonProcessingException e) {
// e.printStackTrace();
// }
// return "Error!";
// }

// @RequestMapping(path = "/restaurant/{id}", method = RequestMethod.GET)
// String getDetailsById(@PathVariable int id) {
// try {
// var restaurant = RestaurantModel.findById(id);
// String json = new
// ObjectMapper().writeValueAsString(appendReviewsCountAverageMap(restaurant));
// return json;
// } catch (JsonProcessingException e) {
// e.printStackTrace();
// }
// return "Error!";
// }

// @RequestMapping(path = "/restaurant/", method = RequestMethod.GET)
// String getDetailsByName(@RequestParam(value = "name", required = true) String
// name) {
// try {
// String json = new
// ObjectMapper().writeValueAsString(RestaurantModel.findByName(name));
// return json;
// } catch (JsonProcessingException e) {
// e.printStackTrace();
// }
// return "Error!";
// }

// @RequestMapping(path = "/restaurants/", method = RequestMethod.GET)
// String searchRestaurants(@RequestParam(value = "name", required = false)
// String name,
// @RequestParam(value = "city", required = false) String city,
// @RequestParam(value = "type", required = false) String type) {
// if (city == null && type == null && name == null) {
// return getAll();
// }

// try {
// var restaurants = RestaurantModel.getAllObjects().stream().filter(
// city == null ? r -> true : r ->
// r.getAddress().city.equalsIgnoreCase(city)).filter(
// name == null ? r -> true : r -> r.getName().equalsIgnoreCase(name))
// .filter(
// type == null ? r -> true : r -> r.getType().equalsIgnoreCase(type))
// .toList();
// String json = new ObjectMapper().writeValueAsString(restaurants);
// return json;
// } catch (JsonProcessingException e) {
// e.printStackTrace();
// }
// return "Error!";
// }

// private Map<String, Object> appendReviewsCountAverageMap(RestaurantModel
// restaurant) {
// ObjectMapper mapper = new ObjectMapper();

// var mapObject = mapper.convertValue(restaurant, Map.class);

// var reviews = ReviewModel.findByRestaurantName(restaurant.getName());
// double overall = reviews.stream().mapToDouble(r ->
// r.getOverallRate()).average().orElse(0);
// var reviewsCount = reviews.size();
// mapObject.put("reviewsCount", reviewsCount);
// mapObject.put("overall", overall);
// return mapObject;
// }

// private String buildJsonList(List<RestaurantModel> restaurants) throws
// JsonProcessingException {
// ObjectMapper mapper = new ObjectMapper();
// ArrayList<Map> objects = new ArrayList<>();

// for (var iter : restaurants) {
// var mapObject = appendReviewsCountAverageMap(iter);
// objects.add(mapObject);
// }

// var jsonString =
// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objects);
// return jsonString;
// }
// }