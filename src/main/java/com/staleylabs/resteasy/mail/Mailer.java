package com.staleylabs.resteasy.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Utility class that is used to send mail from different parts of the application to the end user, customer, or group.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/7/13)
 */

@Component
public class Mailer {

    private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";

    @Autowired
    private SMTPAuthenticator smtpAuthenticator;

    public void test() throws Exception {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");

        Session mailSession = Session.getDefaultInstance(props, smtpAuthenticator);

        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);

        Multipart multipart = new MimeMultipart("alternative");

        BodyPart part1 = new MimeBodyPart();
        part1.setText("This is multipart mail and u read part1......");

        BodyPart part2 = new MimeBodyPart();
        part2.setContent("<b>This is multipart mail and u read part2......</b>", "text/html");

        multipart.addBodyPart(part1);
        multipart.addBodyPart(part2);

        message.setContent(multipart);
        message.setFrom(new InternetAddress("me@myhost.com"));
        message.setSubject("This is the subject");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("someone@somewhere.com"));

        transport.connect();
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }
}
