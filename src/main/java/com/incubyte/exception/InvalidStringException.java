package com.incubyte.exception;

/**
 * @author AMAN AHUJA
 * Custom Exception class used in case of invalid string
 */
public class InvalidStringException extends RuntimeException {
    public InvalidStringException(String message) {
        super(message);
    }
}
