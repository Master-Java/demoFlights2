package com.example.demoflights.service;

import com.example.demoflights.domain.service.Mail;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;

public interface MailService {

    void sendEmail(Mail mail) throws MessagingException;

    void sendEmail(Mail mail, MultipartFile[] files) throws MessagingException;
}
