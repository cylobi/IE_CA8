package org.ie.mizdooni.serializer;

import org.ie.mizdooni.model.UserModel;
import org.ie.mizdooni.model.UserAddress;

public class CurrentUserResponseBody {
    public UserAddress address;
    public String username;
    public String email;
    public String role;

    public CurrentUserResponseBody(UserModel user) {
        username = user.getUsername();
        email = user.getEmail();
        address = new UserAddress(user.getCountry(), user.getCity());
        role = user.getRole();
    }
}
