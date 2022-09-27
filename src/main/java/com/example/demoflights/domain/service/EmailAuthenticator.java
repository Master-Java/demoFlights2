package com.example.demoflights.domain.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.mail.PasswordAuthentication;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailAuthenticator extends javax.mail.Authenticator {

    private String login;
    private String password;

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(login, password);
    }
}
