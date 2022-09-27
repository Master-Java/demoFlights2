package com.example.demoflights.controller;

import com.example.demoflights.domain.service.Mail;
import com.example.demoflights.service.MailService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/v1/")
@Api(value = "Mail Controller", description = "Операции с почтой")
public class MailController {

    private final MailService mailService;

    @GetMapping("mail/{email}")
    public ResponseEntity<?> mail(@PathVariable String email) {
        try {
            mailService.sendEmail(email, "", "");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("mail/{email}")
    public ResponseEntity<?> sendMail(@RequestBody Mail mail) {
        try {
            mailService.sendEmail(mail);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}