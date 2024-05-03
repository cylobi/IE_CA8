package org.ie.mizdooni.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ie.mizdooni.model.UserModel;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {
    @GetMapping("/users")
    String getAll() {
        try {
            String json = new ObjectMapper().writeValueAsString(UserModel.getAllObjects());
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Error!";
    }

    @RequestMapping(path = "/users/{name}", method = RequestMethod.GET)
    String getDetails(@PathVariable String name) {
        try {
            String json = new ObjectMapper().writeValueAsString(UserModel.findByUsername(name));
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Error!";
    }

    @RequestMapping(path = "/users/current_user", method = RequestMethod.GET)
    String getLoginnedUser() {
        try {
            String json = new ObjectMapper().writeValueAsString(UserModel.getLoginnedUser());
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Error!";
    }

    @RequestMapping(path = "/users/current_user/logout", method = RequestMethod.PUT)
    String logout() {
        UserModel.setLoginnedUser(null);
        return "";
    }

}
