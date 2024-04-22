package org.ie.mizdooni.utils.exception;

public class TableAlreadyDefinedException extends BadRequestException{
    public TableAlreadyDefinedException() {
        super("The table is already defined in the same restaurant!");
    }
}
