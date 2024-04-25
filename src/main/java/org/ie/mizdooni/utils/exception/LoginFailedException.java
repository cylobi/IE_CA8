package org.ie.mizdooni.utils.exception;

public class LoginFailedException extends BadRequestException{

    public LoginFailedException() {
        super("Login Failed!");
    }
}
