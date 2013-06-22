package com.staleylabs.resteasy.exception;

/**
 * Exception that can occur if not enough information is given in a method that requires certain parameters.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/17/13)
 */

public class InsufficientInformationException extends Exception {

    public InsufficientInformationException(String message) {
        super(message);
    }
}
