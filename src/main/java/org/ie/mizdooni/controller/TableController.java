package org.ie.mizdooni.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.ie.mizdooni.dao.TableDao;
import org.ie.mizdooni.model.ReserveTableModel;
import org.ie.mizdooni.model.RestaurantModel;
import org.ie.mizdooni.model.TableModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TableController {

    @GetMapping("/tables")
    String getAll() {
        try {
            String json = new ObjectMapper().writeValueAsString(TableDao.getInstance().findAll());
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Error!";
    }

    @RequestMapping(path = "/table/{restname}/{number}", method = RequestMethod.GET)
    @ResponseBody
    String getDetails(@PathVariable String restname, @PathVariable int number) {
        try {
            Map<String, Object> criteria = new HashMap<>();
            criteria.put("restaurantName", restname);
            criteria.put("tableNumber", number);
            List<String> selectedCols = Arrays.asList("tableNumber", "restaurantName", "seatsNumber");
            return new ObjectMapper().writeValueAsString(TableDao.getInstance().findByCriteria(selectedCols,criteria));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Error!";
    }

//     @GetMapping("/tables/availables")
//     public String getAvailablesInDate(@RequestParam(name = "restId", required =
//     true) int restId, @RequestParam(name = "date", required = true) String dateStr) {
////     var restaurant = RestaurantModel.findById(restId);
//         var
//         if (restaurant == null) {
//         return "Error";
//     }

    // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    // Date date;
    // try {
    // date = formatter.parse(dateStr);
    // var reservesInDate =
    // ReserveTableModel.findForRestaurantInDate(restaurant.getName(), date);
    // var reservedIdsArray = reservesInDate.stream().mapToInt(r ->
    // r.getId()).toArray();
    // var reservedIds = Set.of(reservedIdsArray);

    // String json = new ObjectMapper().writeValueAsString(reservesInDate);
    // return json;
    // } catch (ParseException | JsonProcessingException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

    // return "Error";
    // }

}
