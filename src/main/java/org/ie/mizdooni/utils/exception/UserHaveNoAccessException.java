package org.ie.mizdooni.utils.exception;

public class UserHaveNoAccessException extends BaseWebappException{

    public UserHaveNoAccessException() {
        super("Current user have no access to this page!", 403);
    }
}
