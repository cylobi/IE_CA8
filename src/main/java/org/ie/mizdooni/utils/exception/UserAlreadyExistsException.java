package org.ie.mizdooni.utils.exception;

public class UserAlreadyExistsException extends BadRequestException {
    public UserAlreadyExistsException() { super("USER_ALREADY_EXISTS"); }
}
