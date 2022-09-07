package com.example.demoflights.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ApiModelProperty(notes = "Id пользователя - телефон", example = "+7-911-955-10-10", required = true)
    private String phone;
    private String surname;
    private String name;
    private String patronymic;
    private String email;
    private String orgInn;
}
