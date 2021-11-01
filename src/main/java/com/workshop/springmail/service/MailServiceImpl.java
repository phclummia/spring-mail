package com.workshop.springmail.service;

import com.workshop.springmail.data.model.MailEntity;
import com.workshop.springmail.data.repository.MailEntityRepository;
import com.workshop.springmail.model.MailRequest;
import com.workshop.springmail.model.MailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final MailEntityRepository repository;

    @Override
    public MailResponse senMail(MailRequest request) {
        log.info("Mail send start Combined");
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(request.getTo());
            email.setSubject((String.format("Subject : %s Time : %s",
                    request.getSubject(), LocalDateTime.now())));
            email.setText(request.getBody());
            mailSender.send(email);
            repository.saveAndFlush(MailEntity.builder()
                    .applicationName(request.getApplicationName())
                    .body(request.getBody())
                    .date(LocalDateTime.now())
                    .response("Success")
                    .subject(request.getSubject())
                    .to(request.getTo())
                    .build());

        } catch (Exception ex) {
            repository.saveAndFlush(MailEntity.builder()
                    .applicationName(request.getApplicationName())
                    .body(request.getBody())
                    .date(LocalDateTime.now())
                    .response(ex.toString())
                    .subject(request.getSubject())
                    .to(request.getTo())
                    .build());

            log.info("Mail send end with error");
            return MailResponse.builder()
                    .message("Exception Occured")
                    .result(false)
                    .build();
        }

        log.info("Mail send end with success");
        return MailResponse.builder()
                .message("Mail Successfully Sent")
                .result(true)
                .build();

    }
}
