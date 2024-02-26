package com.portal.exceptions;

public class DuplicateFoundException extends RuntimeException {
    public DuplicateFoundException(String msg) {
        super(msg);
    }
}
