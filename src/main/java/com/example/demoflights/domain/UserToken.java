package com.example.demoflights.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserToken {
    long id;
    String userPhone;
    String userToken;
}
