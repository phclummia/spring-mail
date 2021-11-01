package com.workshop.springmail.controller;


import com.workshop.springmail.model.MailRequest;
import com.workshop.springmail.model.MailResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Validated
@RequestMapping(value = "/api/v1/mail")
public interface MailController {

    @RequestMapping(value = "/send",method = RequestMethod.POST)
    MailResponse sendMail(@RequestBody MailRequest request);
}
