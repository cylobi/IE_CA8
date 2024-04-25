package org.ie.mizdooni.utils.exception;

public class ManagerNotFoundException extends BadRequestException{
    public ManagerNotFoundException() {
        super("Manager with given username not found!");
    }
}
