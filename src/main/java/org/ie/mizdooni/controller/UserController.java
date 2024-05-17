package org.ie.mizdooni.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.ie.mizdooni.dao.GlobalDataDao;
import org.ie.mizdooni.dao.UserDao;
import org.ie.mizdooni.model.ClientUserModel;
import org.ie.mizdooni.model.ManagerUserModel;
import org.ie.mizdooni.model.UserAddress;
import org.ie.mizdooni.model.UserModel;
//import org.ie.mizdooni.model.UserModel.UserRole;
import org.ie.mizdooni.serializer.LoginUserRequestBody;
import org.ie.mizdooni.serializer.RegisterRequestBody;
import org.ie.mizdooni.utils.exception.BaseWebappException;
import org.ie.mizdooni.utils.exception.UserAlreadyExistsException;
import org.ie.mizdooni.utils.exception.UserNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api")
public class UserController {
    // @GetMapping("/users")
    // @ResponseBody
    // ResponseEntity<String> getAllUsers() throws JsonProcessingException
    // {
    // String json = new
    // ObjectMapper().writeValueAsString(UserModel.getAllObjects());
    // return new ResponseEntity<>(json, HttpStatus.OK);
    // }

    @RequestMapping(path = "/users/current_user", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<String> getLoginnedUser() throws JsonProcessingException {
        UserModel user = GlobalDataDao.getInstance().getLoginnedUser();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        String json = new ObjectMapper().writeValueAsString(user);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @RequestMapping(path = "/auth/logout", method = RequestMethod.PUT)
    @ResponseBody
    ResponseEntity<String> logout() {
        GlobalDataDao.getInstance().setLogoutUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/auth/login", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody LoginUserRequestBody body)
            throws BaseWebappException, JsonProcessingException {
        var user = UserDao.getInstance().findUserByUserPass(body.getUsername(), body.getPassword());
        if (user == null) {
            throw new UserNotFoundException();
        }
        GlobalDataDao.getInstance().setLoginnedUserByUsername(user.getUsername());
        String json = new ObjectMapper().writeValueAsString(GlobalDataDao.getInstance().getLoginnedUser());
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @RequestMapping(path = "/auth/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestBody body) throws BaseWebappException {
        boolean isUsernameAllowed = UserDao.getInstance().findOneByUsername(body.username) == null;
        if (!isUsernameAllowed) {
            throw new UserAlreadyExistsException();
        }
        boolean isEmailAllowed = UserDao.getInstance().isEmailAllowed(body.email);
        if (!isEmailAllowed) {
            throw new UserAlreadyExistsException();
        }
        var newUser = createInstanceFromRequest(body);
        UserDao.getInstance().create(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(BaseWebappException.class)
    public ResponseEntity<String> handleException(BaseWebappException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(e.getCode()));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> handleException(JsonProcessingException e) {
        return new ResponseEntity<>("Error Processing JSON" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // private boolean doesUsernameEmailExist(String username, String email) {
    // var allData = UserModel.getAllObjects();
    // for (var user : allData) {
    // if (user.getEmail().compareTo(email) == 0 ||
    // user.getUsername().compareTo(username) == 0) {
    // return true;
    // }
    // }
    // return false;
    // }

     private UserModel createInstanceFromRequest(RegisterRequestBody body) {
        UserModel instance;
        if (body.role.equals("client")){
            instance = new ClientUserModel();
        } else {
            instance = new ManagerUserModel();
        }
         instance.setUsername(body.username);
         instance.setPassword(body.password);
         instance.setEmail(body.email);
         instance.setCountry(body.country);
         instance.setCity(body.city);
         return instance;
     }

}
