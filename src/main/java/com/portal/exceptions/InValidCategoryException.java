package com.portal.exceptions;

//It is custom exception occurs at run time when given parameter is doesn't exist
public class InValidCategoryException extends RuntimeException {
    public InValidCategoryException(String msg) {
        super(msg);
    }
}
