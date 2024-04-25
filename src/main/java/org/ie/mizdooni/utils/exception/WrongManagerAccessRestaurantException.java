package org.ie.mizdooni.utils.exception;

public class WrongManagerAccessRestaurantException extends BadRequestException{
    public WrongManagerAccessRestaurantException() {
        super("The manager has no any access to this restaurant!");
    }
}
