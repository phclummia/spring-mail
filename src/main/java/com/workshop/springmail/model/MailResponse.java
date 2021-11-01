package com.workshop.springmail.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailResponse {

    private boolean result;
    private String message;
}
