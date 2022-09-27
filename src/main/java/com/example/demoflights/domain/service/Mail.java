package com.example.demoflights.domain.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mail {
    private String body;
    private String to;
    private String subject;
}
