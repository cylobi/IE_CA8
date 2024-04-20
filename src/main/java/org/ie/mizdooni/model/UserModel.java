package org.ie.mizdooni.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ie.mizdooni.utils.validator.EmailValidator;
import org.ie.mizdooni.utils.validator.UserNameVlidator;
import org.ie.mizdooni.utils.validator.ValidatorException;

public class UserModel extends BaseModel {

    static class UserAddress {
        public String country, city;
    }

    public enum UserRole{
                @JsonProperty("client") CLIENT,@JsonProperty("manager") MANAGER
        };

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    UserRole role;
    @FieldValidator(UserNameVlidator.class) String username;
    String password;
    @FieldValidator(EmailValidator.class) String email;
    UserAddress address;

    public UserModel() {

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserAddress getAddress() {
        return address;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }

    static private UserModel loginnedUser = null;

    public static UserModel getLoginnedUser() {
        return loginnedUser;
    }

    public static void setLoginnedUser(UserModel loginnedUser) {
        UserModel.loginnedUser = loginnedUser;
    }
}
