package org.ie.mizdooni.serializer;

import org.ie.mizdooni.model.UserModel;

public class RegisterRequestBody {
    public String username, password, email, country, city, role;

    public UserModel toModel() {
        var user = new UserModel();
        user.setUsername(username);
        user.setEmail(email);
        user.setCity(city);
        user.setCountry(country);
        user.setPassword(password);
        return user;
    }
}
