package com.example.demoflights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DemoFlightsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoFlightsApplication.class, args);
    }

}
