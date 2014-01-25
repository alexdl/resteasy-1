package com.staleylabs.resteasy.mail;

import com.github.sendgrid.SendGrid;
import com.staleylabs.resteasy.beans.mail.Letter;

/**
 * Class used to send emails out of the application using the SendGrid service.
 *
 * @author Sean M. Staley
 * @version 1.0 (1/25/14)
 */

public final class MailEngine {

    private static final String USERNAME = System.getenv("SENDGRID_USERNAME");

    private static final String PASSWORD = System.getenv("SENDGRID_PASSWORD");

    public static String sendMessage(Letter letter) {
        SendGrid envelope = new SendGrid(USERNAME, PASSWORD);
        envelope.addTo(letter.getReceiverEmail());
        envelope.addToName(letter.getReceiverName());
        envelope.setFromName(letter.getSenderName());
        envelope.setFrom(letter.getSenderEmail());

        envelope.setHtml(letter.getHtmlBody());
        envelope.setText(letter.getTextBody());

        envelope.setSubject(letter.getSubject());

        return envelope.send();
    }
}
