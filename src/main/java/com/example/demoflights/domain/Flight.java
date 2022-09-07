package com.example.demoflights.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @ApiModelProperty(notes = "Id полёта, не нужно указывать при создании", example = "1", required = true)
    private int id;
    private long flightNumber;
    private String fromPoint;
    private String toPoint;
    @ApiModelProperty(notes = "Время прилёта самолёта", example = "2022-09-07 08:40:00.000")
    private String toPointDate;
    @ApiModelProperty(notes = "Время отправления самолёта", example = "2022-09-07 07:25:00.000")
    private String fromPointDate;
    private String airCompany;
    private String flightStatus;
    private String comment;
    private List<String> passengersPhones;
}
