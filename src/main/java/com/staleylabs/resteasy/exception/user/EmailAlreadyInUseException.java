package com.staleylabs.resteasy.exception.user;

/**
 * Exception that can be caused by trying to register with a user that has already used the given email.
 *
 * @author Sean M. Staley
 * @version X.X (6/21/13)
 */

public class EmailAlreadyInUseException extends Exception {

    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
