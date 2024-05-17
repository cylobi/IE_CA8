package org.ie.mizdooni.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;

import org.ie.mizdooni.model.ReserveTableModel;
import org.ie.mizdooni.model.TableModel;
import org.ie.mizdooni.model.UserModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@RestController
//@RequestMapping("/reservations")
//public class ReservationController {
//    @GetMapping("/current_user")
//    public ResponseEntity<String> getReservationsOfCurrentUser() {
//        try {
//            UserModel user = UserModel.getLoginnedUser();
//            if (user == null) {
//                return ResponseEntity.badRequest().build();
//            }
//            var reservations = ReserveTableModel.findByUsername(user.getUsername());
//            reservations.sort(Comparator.comparing(ReserveTableModel::getDatetime).reversed());
//            var mapper = new ObjectMapper();
//
//            ArrayList<Map<String, Object>> aggregatedResult = new ArrayList<>();
//            for (var reservationIter : reservations) {
//                var tableModel = TableModel.findByRestaurantNameAndNumber(reservationIter.getRestaurantName(),
//                        reservationIter.getTableNumber());
//                var newMap = mapper.convertValue(reservationIter, Map.class);
//                newMap.put("seatsNumber", tableModel.getSeatsNumber());
//                aggregatedResult.add(newMap);
//            }
//            String json = new ObjectMapper().writeValueAsString(aggregatedResult);
//
//            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.internalServerError().body(null);
//    }

    // @GetMapping("/")
    // public String getMethodName(@RequestParam String param) {
    // return new String();
    // }

// }