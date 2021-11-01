package com.workshop.springmail.data.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "MailEntity")
@Table(name = "mail")
@Data
@Builder
public class MailEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "application_name", nullable = false, length = 80)
    private String applicationName;
    @Column(name = "mail_to", nullable = false, length = 80)
    private String to;
    @Column(name = "subject", nullable = false, length = 1024)
    private String subject;
    @Column(name = "body", nullable = false, length = 4096)
    private String body;
    @Column(name = "response", nullable = false, length = 4096)
    private String response;
    @Column(name = "mail_date", nullable = false)
    private LocalDateTime date;

}
