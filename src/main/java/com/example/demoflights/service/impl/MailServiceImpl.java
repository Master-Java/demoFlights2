package com.example.demoflights.service.impl;

import com.example.demoflights.domain.service.Mail;
import com.example.demoflights.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.springframework.http.MediaType.*;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.noreply.mail}")
    private String currentMailFrom;
    @Value("${spring.mail.noreply.password}")
    private String password;

    private final static Map<String, String> nameFiles = new HashMap<>();

    private JavaMailSenderImpl mailSender;

    @PostConstruct
    public void init() {
        Properties emailProperties = new Properties();

        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");

        mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(emailProperties);
        mailSender.setPassword(password);
        mailSender.setUsername(currentMailFrom);
        mailSender.setPort(587);
        mailSender.setHost("smtp.gmail.com");

        nameFiles.put(IMAGE_JPEG_VALUE, "picture_");
        nameFiles.put(IMAGE_PNG_VALUE, "picture_");
        nameFiles.put(IMAGE_GIF_VALUE, "picture_");
        nameFiles.put(APPLICATION_PDF_VALUE, "document_");
    }

    @Override
    public void sendEmail(Mail mail) throws MessagingException {
        sendEmail(mail, new MultipartFile[0]);
    }

    @Override
    public void sendEmail(Mail mail, MultipartFile[] files) throws MessagingException {
        MimeMessage message = new MimeMessage(mailSender.getSession());

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody());
        helper.setTo(mail.getTo());
        helper.setFrom(currentMailFrom);

        addFilesToMessage(helper, files);

        mailSender.send(message);
    }

    private void addFilesToMessage(MimeMessageHelper helper, MultipartFile[] files) {
        for (int i = 0; i < files.length; i++) {
            try {
                ByteArrayDataSource attachment = new ByteArrayDataSource(files[i].getInputStream(), files[i].getContentType());
                helper.addAttachment(getStringOrDefault(files[i].getOriginalFilename(), getNameForFile(files[i].getContentType())), attachment);
            } catch (IOException | MessagingException exception) {
                System.out.println("Error for work with files\n Can't processing " + i + " file");
            }
        }
    }

    private static String getStringOrDefault(String string, String strDefault) {
        return (string == null || string.isEmpty()) ? strDefault : string;
    }

    private static String getNameForFile(String contentType) {
        return nameFiles.getOrDefault(contentType, "file_");
    }
}