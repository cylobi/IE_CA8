package org.ie.mizdooni.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ie.mizdooni.model.RestaurantModel;
import org.ie.mizdooni.model.ReviewModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RestaurantController {
    @GetMapping("/restaurants")
    String getAll() {
        try {
            return buildJsonList(RestaurantModel.getAllObjects());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Error!";
    }

    @GetMapping("/restaurants/best_ones")
    String getBestOnes(@RequestParam(name = "count", defaultValue = "6") int count) {
        try {
            var restaurants = RestaurantModel.getAllObjects();
            Map<String, Float> ratingMap = new HashMap<>();
            for (var rest : restaurants) {
                ratingMap.put(rest.getName(),
                        ReviewModel.calculateAverageForRestaurant(rest.getName()).getOverallRate());
            }
            float defaultRating = 0;
            var bestOnes = restaurants.stream().sorted(
                    (r1, r2) -> ratingMap.getOrDefault(r1.getName(), defaultRating) > ratingMap
                            .getOrDefault(r2.getName(), defaultRating)
                                    ? -1
                                    : 1)
                    .limit(count).toList();

            return buildJsonList(bestOnes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Error!";
    }

    @GetMapping("/restaurants/recommend")
    public String getRecommentedOnes(@RequestParam(name = "count", defaultValue = "6") int count) {
        var restaurants = RestaurantModel.getAllObjects();
        var list = restaurants.subList(0, Math.min(count, restaurants.size()));
        try {
            return buildJsonList(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Error!";
    }

    @RequestMapping(path = "/restaurant/{name}", method = RequestMethod.GET)
    String getDetails(@PathVariable String name) {
        try {
            String json = new ObjectMapper().writeValueAsString(RestaurantModel.findByName(name));
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Error!";
    }

    private String buildJsonList(List<RestaurantModel> restaurants) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Map> objects = new ArrayList<>();

        for (var iter : restaurants) {
            var mapObject = mapper.convertValue(iter, Map.class);

            var reviews = ReviewModel.findByRestaurantName(iter.getName());
            double overall = reviews.stream().mapToDouble(r -> r.getOverallRate()).average().orElse(0);
            var reviewsCount = reviews.size();
            // var overall = overallList.average();/

            mapObject.put("reviewsCount", reviewsCount);
            mapObject.put("overall", overall);
            mapObject.remove("description"); // description length is too large
            objects.add(mapObject);
        }

        var jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objects);
        return jsonString;
    }
}