package com.example.demoflights.service;

import com.example.demoflights.domain.service.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MailService {
    void sendEmail(String email, String body, String subject) throws MessagingException;

    void sendEmail(Mail mail);
}
