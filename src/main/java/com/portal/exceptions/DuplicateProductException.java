package com.portal.exceptions;

//It is custom exception for duplicates found
public class DuplicateProductException extends RuntimeException {
    public DuplicateProductException(String msg) {
        super(msg);
    }
}
