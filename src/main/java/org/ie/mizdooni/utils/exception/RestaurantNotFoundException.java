package org.ie.mizdooni.utils.exception;

public class RestaurantNotFoundException extends BadRequestException {
    public RestaurantNotFoundException() {
        super("Restaurant not found!");
    }
}
