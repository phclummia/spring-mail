package com.workshop.springmail.service;


import com.workshop.springmail.model.MailRequest;
import com.workshop.springmail.model.MailResponse;

public interface MailService {

    MailResponse senMail(MailRequest request);
}
