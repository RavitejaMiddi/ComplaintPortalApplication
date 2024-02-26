package com.portal.exceptions;

//It is custom exception occurs at run time when given  parameter is doesn't exist
public class InValidModelNumberException extends RuntimeException {
    public InValidModelNumberException(String msg) {
        super(msg);
    }
}
