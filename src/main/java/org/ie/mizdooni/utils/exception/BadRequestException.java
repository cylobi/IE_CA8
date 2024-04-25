package org.ie.mizdooni.utils.exception;

public class BadRequestException extends BaseWebappException {
    public BadRequestException(String msg){
        super(msg, 400);
    }
    public BadRequestException(){
        super();
    }

}
