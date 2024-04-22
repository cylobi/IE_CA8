package org.ie.mizdooni.utils.exception;

public class AccessDeniedException extends BaseWebappException{
    public AccessDeniedException() {
        super("Access denied!", 403);
    }

}
