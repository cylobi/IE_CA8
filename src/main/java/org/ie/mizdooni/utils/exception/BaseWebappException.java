package org.ie.mizdooni.utils.exception;

public class BaseWebappException extends Exception {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int code;
    public BaseWebappException() {
        code = 500;
    }

    public BaseWebappException(String message) {
        super(message);

        code = 500;
    }
    public BaseWebappException(String message, int statusCode) {
        super(message);

        code = statusCode;
    }

}
