package com.staleylabs.resteasy.beans;

import javax.mail.Multipart;
import java.util.List;

/**
 * Bean that represents a typical email message.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/7/13)
 */

public class EmailMessage {

    private String sender;

    private List<String> recipients;

    private List<String> ccRecipients;

    private String subject;

    private String messageBody;

    private Multipart contentType;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public List<String> getCcRecipients() {
        return ccRecipients;
    }

    public void setCcRecipients(List<String> ccRecipients) {
        this.ccRecipients = ccRecipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Multipart getContentType() {
        return contentType;
    }

    public void setContentType(Multipart contentType) {
        this.contentType = contentType;
    }
}
