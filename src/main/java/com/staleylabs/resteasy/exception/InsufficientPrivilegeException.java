package com.staleylabs.resteasy.exception;

/**
 * Exception used to show that a given user does not have permission to perform the requested action.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/6/13)
 */

public class InsufficientPrivilegeException extends Exception {

    public InsufficientPrivilegeException() {
        super("Not permitted to perform action.");
    }

    public InsufficientPrivilegeException(String message) {
        super(message);
    }
}
