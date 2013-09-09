package com.staleylabs.resteasy.mail;

import com.staleylabs.resteasy.beans.EmailMessage;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Utility class that is used to send mail from different parts of the application to the end user, customer, or group.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/7/13)
 */

@Component
public class Mailer {

    private static final Logger log = Logger.getLogger(Mailer.class);

    private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";

    private final Properties properties = new Properties();

    @Autowired
    private SMTPAuthenticator smtpAuthenticator;

    @PostConstruct
    private void applyMailProperties() {
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", SMTP_HOST_NAME);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
    }

    // uncomment for debugging infos to stdout
    // mailSession.setDebug(true);

    /**
     * Provides the ability to send an email from the application given a {@link EmailMessage} object.
     *
     * @param message {@link EmailMessage} object that contains the basic information of the email to be sent.
     * @return {@code true} if the email was sent successfully.
     * @throws MessagingException
     */
    public boolean sendMessage(EmailMessage message) throws MessagingException {
        log.info("Request relieved to send a message.");

        Session mailSession = Session.getDefaultInstance(properties, smtpAuthenticator);
        Transport transport = mailSession.getTransport();

        MimeMessage mimeMessage = new MimeMessage(mailSession);
        Multipart multipart = new MimeMultipart("alternative");

        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(message.getMessageBody());

        multipart.addBodyPart(bodyPart);

        mimeMessage.setContent(multipart);
        mimeMessage.setFrom(new InternetAddress(message.getSender()));
        mimeMessage.addRecipients(Message.RecipientType.TO, generateAddressArray(message.getRecipients()));

        if (!CollectionUtils.isEmpty(message.getCcRecipients())) {
            mimeMessage.addRecipients(Message.RecipientType.CC, generateAddressArray(message.getCcRecipients()));
        }

        mimeMessage.setSubject(message.getSubject());

        log.info("Sending email to " + Arrays.toString(mimeMessage.getAllRecipients()));

        transport.connect();
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();

        log.info("Successfully sent out the requested email. Bye bye!");

        return true;
    }

    protected Address[] generateAddressArray(List<String> addresses) throws AddressException {
        log.debug("Translating addresses into an array of Address objects.");

        Address[] result = new Address[addresses.size()];
        int position = 0;

        for (String address : addresses) {
            result[position] = new InternetAddress(address);
            position++;
        }

        LogMF.debug(log, "Translated {0} addresses into Address objects.", position);

        return result;
    }
}
