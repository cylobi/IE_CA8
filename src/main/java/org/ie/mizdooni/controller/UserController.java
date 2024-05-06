package org.ie.mizdooni.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.ie.mizdooni.model.UserAddress;
import org.ie.mizdooni.model.UserModel;
import org.ie.mizdooni.model.UserModel.UserRole;
import org.ie.mizdooni.serializer.LoginUserRequestBody;
import org.ie.mizdooni.serializer.RegisterRequestBody;
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

    @RequestMapping(path = "/users/current_user/login", method = RequestMethod.PUT)
    public String login(@RequestBody LoginUserRequestBody lrb) {
        var user = UserModel.findUserByUserPass(lrb.getUsername(), lrb.getPassword());
        if (user.isEmpty()) {
            return "ERORRRRRRRRRRRRRRRRRR";
        }
        UserModel.setLoginnedUser(user.get(0));
        try {
            String json = new ObjectMapper().writeValueAsString(UserModel.getLoginnedUser());
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "BAGHBAGHOOOOOOO";
    }

    private boolean doesUsernameEmailExist(String username, String email) {
        var allData = UserModel.getAllObjects();
        for (var user : allData) {
            if (user.getEmail().compareTo(email) == 0 || user.getUsername().compareTo(username) == 0) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(path = "/users/current_user/register", method = RequestMethod.POST)
    public String requestMethodName(@RequestBody RegisterRequestBody body) {
        boolean doesAlreadyExist = doesUsernameEmailExist(body.username, body.email);
        if (doesAlreadyExist) {
            // throw new ValidatorException("Username and email combination is not
            // unique!");
            return "Username and email combination is not unique!";
        }
        var newUser = createInstanceFromRequest(body);
        UserModel.addObject(newUser);
        return "User added";
    }

    private UserModel createInstanceFromRequest(RegisterRequestBody body) {
        var instance = new UserModel();
        instance.setUsername(body.username);
        instance.setPassword(body.password);
        instance.setEmail(body.email);
        instance.setRole(body.role.equals("client") ? UserModel.UserRole.CLIENT : UserModel.UserRole.MANAGER);
        var addr = new UserAddress(body.country, body.city);
        instance.setAddress(addr);

        return instance;
    }

}
