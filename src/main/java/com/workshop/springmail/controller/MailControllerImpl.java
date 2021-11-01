package com.workshop.springmail.controller;

import com.workshop.springmail.model.MailRequest;
import com.workshop.springmail.model.MailResponse;
import com.workshop.springmail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailControllerImpl implements MailController {

    private final MailService service;

    @Override
    public MailResponse sendMail(MailRequest request) {
        return service.senMail(request);
    }
}
