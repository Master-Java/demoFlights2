package com.example.demoflights.service.impl;

import com.example.demoflights.domain.service.Mail;
import com.example.demoflights.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.noreply.mail}")
    private String mail;
    @Value("${spring.mail.noreply.password}")
    private String password;

    private JavaMailSenderImpl mailSender;

    @PostConstruct
    public void init() {
        Properties emailProperties = new Properties();

        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");

        mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(emailProperties);
        mailSender.setPassword(password);
        mailSender.setUsername(mail);
        mailSender.setPort(587);
        mailSender.setHost("smtp.gmail.com");
    }

    @Override
    public void sendEmail(String email, String body, String subject) throws MessagingException {
        MimeMessage message = new MimeMessage(mailSender.getSession());
        message.setFrom(new InternetAddress(mail));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Полёт забронирован");

        String msg = "<div class=\"cl_384061\"><style>.cl_384061 {" +
                "}.cl_384061 body{" +
                "margin-top:0px;" +
                "margin-right:0px;" +
                "margin-bottom:0px;" +
                "margin-left:0px;" +
                "padding-top:0px;" +
                "padding-right:0px;" +
                "padding-bottom:0px;" +
                "padding-left:0px;" +
                "}.cl_384061 #MessageViewBody_mr_css_attr a{" +
                "color:inherit;" +
                "text-decoration:none;" +
                "}.cl_384061 p{" +
                "line-height:inherit;" +
                "}.cl_384061 .desktop_hide_mr_css_attr, .cl_384061 .desktop_hide_mr_css_attr table{" +
                "display:none;" +
                "max-height:0px;" +
                "overflow-x:hidden;" +
                "overflow-y:hidden;" +
                "}@media (max-width: 660px){" +
                ".cl_384061 .social_block_mr_css_attr.desktop_hide_mr_css_attr .social-table_mr_css_attr{" +
                "display:inline-block !important;" +
                "}.cl_384061 .image_block_mr_css_attr img.big_mr_css_attr, .cl_384061 .row-content_mr_css_attr{" +
                "width:100% !important;" +
                "}.cl_384061 .mobile_hide_mr_css_attr{" +
                "display:none;" +
                "}.cl_384061 .stack_mr_css_attr .column_mr_css_attr{" +
                "width:100%;" +
                "display:block;" +
                "}.cl_384061 .mobile_hide_mr_css_attr{" +
                "min-height:0px;" +
                "max-height:0px;" +
                "max-width:0px;" +
                "overflow-x:hidden;" +
                "overflow-y:hidden;" +
                "font-size:0px;" +
                "}.cl_384061 .desktop_hide_mr_css_attr, .cl_384061 .desktop_hide_mr_css_attr table{" +
                "display:table !important;" +
                "max-height:none !important;" +
                "}" +
                "}</style><table class=\"nl-container_mr_css_attr\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background-color:#f0f0f0\"><tbody><tr><td><table class=\"row_mr_css_attr row-1_mr_css_attr\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tbody><tr><td><table class=\"row-content_mr_css_attr stack_mr_css_attr\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background-color:#fff;color:#000;width:640px\" width=\"640\"><tbody><tr><td class=\"column_mr_css_attr column-1_mr_css_attr\" width=\"100%\" style=\"font-weight:400;text-align:left;vertical-align:top;padding-top:5px;padding-bottom:5px;border-top:0;border-right:0;border-bottom:0;border-left:0\"><div class=\"spacer_block_mr_css_attr\" style=\"height:20px;line-height:20px;font-size:1px\"></div></td></tr></tbody></table></td></tr></tbody></table><table class=\"row_mr_css_attr row-2_mr_css_attr\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tbody><tr><td><table class=\"row-content_mr_css_attr stack_mr_css_attr\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background-color:#fff;color:#000;width:640px\" width=\"640\"><tbody><tr><td class=\"column_mr_css_attr column-1_mr_css_attr\" width=\"100%\" style=\"font-weight:400;text-align:left;padding-left:30px;padding-right:30px;vertical-align:top;padding-top:5px;padding-bottom:5px;border-top:0;border-right:0;border-bottom:0;border-left:0\"><div class=\"spacer_block_mr_css_attr\" style=\"height:20px;line-height:20px;font-size:1px\"></div></td></tr></tbody></table></td></tr></tbody></table><table class=\"row_mr_css_attr row-3_mr_css_attr\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tbody><tr><td><table class=\"row-content_mr_css_attr stack_mr_css_attr\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background-color:#fff;color:#000;width:640px\" width=\"640\"><tbody><tr><td class=\"column_mr_css_attr column-1_mr_css_attr\" width=\"100%\" style=\"font-weight:400;text-align:left;padding-left:30px;padding-right:30px;vertical-align:top;padding-top:5px;padding-bottom:5px;border-top:0;border-right:0;border-bottom:0;border-left:0\"><table class=\"text_block_mr_css_attr block-1_mr_css_attr\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"word-break:break-word\"><tbody><tr><td class=\"pad_mr_css_attr\" style=\"padding-bottom:20px\"><div style=\"font-family:Arial,sans-serif\"><div style=\"font-size:14px;font-family:'Open Sans','Helvetica Neue',Helvetica,Arial,sans-serif;color:#000;line-height:1.5\" class=\"MsoNormal_mr_css_attr\"><p style=\"margin:0;font-size:30px;\" class=\"MsoNormal_mr_css_attr\"><strong>Добрый день (" + email + "), вы забронировали полёт в Мордер</strong></p></div></div></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody><table class=\"row_mr_css_attr row-5_mr_css_attr\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tbody><tr><td><table class=\"row-content_mr_css_attr stack_mr_css_attr\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background-color:#fff;color:#000;width:640px\" width=\"640\"><tbody><tr><td class=\"column_mr_css_attr column-1_mr_css_attr\" width=\"100%\" style=\"font-weight:400;text-align:left;padding-left:30px;padding-right:30px;vertical-align:top;padding-top:5px;padding-bottom:5px;border-top:0;border-right:0;border-bottom:0;border-left:0\"><table class=\"text_block_mr_css_attr block-2_mr_css_attr\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"word-break:break-word\"><tbody><tr><td class=\"pad_mr_css_attr\" style=\"padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:30px\"><div style=\"font-family:sans-serif\"><div style=\"font-size:14px;color:#000;line-height:1.5;font-family:Roboto,Tahoma,Verdana,Segoe,sans-serif\" class=\"MsoNormal_mr_css_attr\"><p style=\"margin:0;font-size:14px;\" class=\"MsoNormal_mr_css_attr\"><span style=\"font-size:18px;\">Я бы пожелал вам удачи, но увы не могу. Так как я ухожу на бир-понг. До скорой встречи&nbsp;&nbsp;</span></p></div></div></td></tr></tbody></table></td></tr>" +
                "</tbody></table></td></tr></tbody></table><table class=\"row_mr_css_attr row-6_mr_css_attr\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tbody><tr><td><table class=\"row-content_mr_css_attr stack_mr_css_attr\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background-color:#fff;color:#000;width:640px\" width=\"640\"><tbody><tr><td class=\"column_mr_css_attr column-1_mr_css_attr\" width=\"50%\" style=\"font-weight:400;text-align:left;padding-left:30px;padding-right:30px;vertical-align:top;border-top:0;border-right:0;border-bottom:0;border-left:0\"><table class=\"social_block_mr_css_attr block-2_mr_css_attr\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tbody><tr><td class=\"pad_mr_css_attr\" style=\"padding-bottom:15px;padding-left:10px;padding-right:10px;padding-top:30px;text-align:center\"><div class=\"alignment_mr_css_attr\" style=\"text-align:center\"><table class=\"social-table_mr_css_attr\" width=\"108px\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block\"><tbody><tr><td style=\"padding:0 2px 0 2px\"><a href=\"https://instagram.com/\" target=\"_blank\" rel=\" noopener noreferrer\"><img src=\"https://img.imgsmail.ru/mail/ru/images/dumb.gif\" class=\"ext_img\" realimg=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-only-logo-dark-gray/instagram@2x.png\" width=\"32\" height=\"32\" alt=\"Instagram\" title=\"Instagram\" style=\"display:block;height:auto;border:0\"></a></td><td style=\"padding:0 2px 0 2px\"><a href=\"https://www.facebook.com/\" target=\"_blank\" rel=\" noopener noreferrer\"><img src=\"https://img.imgsmail.ru/mail/ru/images/dumb.gif\" class=\"ext_img\" realimg=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-only-logo-dark-gray/facebook@2x.png\" width=\"32\" height=\"32\" alt=\"Facebook\" title=\"Facebook\" style=\"display:block;height:auto;border:0\"></a></td><td style=\"padding:0 2px 0 2px\"><a href=\"https://telegram.org\" target=\"_blank\" rel=\" noopener noreferrer\"><img src=\"https://img.imgsmail.ru/mail/ru/images/dumb.gif\" class=\"ext_img\" realimg=\"https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-only-logo-dark-gray/telegram@2x.png\" width=\"32\" height=\"32\" alt=\"Telegram\" title=\"Telegram\" style=\"display:block;height:auto;border:0\"></a></td></tr></tbody></table></div></td></tr></tbody></table></td><td class=\"column_mr_css_attr column-2_mr_css_attr\" width=\"50%\" style=\"font-weight:400;text-align:left;padding-left:30px;padding-right:30px;vertical-align:top;border-top:0;border-right:0;border-bottom:0;border-left:0\">" +
                "<table class=\"text_block_mr_css_attr block-2_mr_css_attr\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"word-break:break-word\"><tbody><tr><td class=\"pad_mr_css_attr\" style=\"padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:25px\"><div style=\"font-family:sans-serif\"><div style=\"font-size:14px;color:#000;line-height:1.2;font-family:Roboto,Tahoma,Verdana,Segoe,sans-serif\" class=\"MsoNormal_mr_css_attr\"><p style=\"margin:0;font-size:14px;text-align:center;\" class=\"MsoNormal_mr_css_attr\"><span class=\"js-phone-number\">8 800 555 35 35</span></p></div></div></td></tr></tbody></table><table class=\"text_block_mr_css_attr block-3_mr_css_attr\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"word-break:break-word\"><tbody><tr><td class=\"pad_mr_css_attr\" style=\"padding-bottom:15px;padding-left:5px;padding-right:5px;padding-top:10px\"><div style=\"font-family:Arial,sans-serif\"><div style=\"font-size:14px;font-family:Arial,'Helvetica Neue',Helvetica,sans-serif;color:#000;line-height:1.2\" class=\"MsoNormal_mr_css_attr\"><p style=\"margin:0;text-align:left;\" class=\"MsoNormal_mr_css_attr\">Лучше позвонить чем у кого-то занимать</p></div></div></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><style>.MsoNormal_mr_css_attr{margin:0;}</style></div>";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=UTF-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        mailSender.send(message);
    }

    @Override
    public void sendEmail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.mail);

        message.setTo("nio07212000@mail.ru");
        message.setText("(｡◕‿◕｡)  С уважением Mail-Handler BIA  (｡◕‿◕｡)");
        message.setSubject("Задача");

        mailSender.send(message);
    }
}
