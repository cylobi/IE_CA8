package org.ie.mizdooni.utils.exception;

public class UserNotFoundException extends BadRequestException{
    public UserNotFoundException() { super("USER_NOT_FOUND"); }
}
