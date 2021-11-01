package com.workshop.springmail.model;

import lombok.Data;

@Data
public class MailRequest {

    private String applicationName;
    private String subject;
    private String to;
    private String body;
}
