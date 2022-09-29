package com.example.demoflights.controller;

import com.example.demoflights.domain.service.Mail;
import com.example.demoflights.service.MailService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/v1/mail")
@Api(value = "Mail Controller", description = "Операции с почтой")
public class MailController {

    private final MailService mailService;

    @PostMapping("/no_files")
    public ResponseEntity<?> sendMailWithoutFiles(@RequestBody Mail mail) {
        try {
            mailService.sendEmail(mail);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping()
    public ResponseEntity<?> sendMailWithFiles(@RequestParam() MultipartFile[] files, @RequestParam() String body,
                                               @RequestParam() String to, @RequestParam() String subject) {
        try {
            mailService.sendEmail(Mail.builder()
                    .body(body)
                    .to(to)
                    .subject(subject)
                    .build(), files);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}