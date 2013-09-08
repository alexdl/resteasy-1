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

    private List<String> sender;

    private List<String> recipients;

    private List<String> ccRecipients;

    private String messageBody;

    private Multipart contentType;

}
