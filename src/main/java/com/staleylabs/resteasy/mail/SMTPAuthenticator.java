package com.staleylabs.resteasy.mail;

import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * E-mail authenticator so that the application can send emails.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/7/13)
 */

@Component
public class SMTPAuthenticator extends Authenticator {

    private static final String SMTP_AUTH_USER = System.getenv("SENDGRID_USERNAME");

    private static final String SMTP_AUTH_PWD = System.getenv("SENDGRID_PASSWORD");

    /**
     * Provides the password authentication for a given user.
     *
     * @return New instance of {@link PasswordAuthentication} with our credentials in line.
     */
    public PasswordAuthentication getPasswordAuthentication() {
        String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PWD;

        return new PasswordAuthentication(username, password);
    }
}
