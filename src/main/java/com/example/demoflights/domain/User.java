package com.example.demoflights.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String phone;
    private String surname;
    private String name;
    private String patronymic;
    private String email;
    private String orgInn;
}
