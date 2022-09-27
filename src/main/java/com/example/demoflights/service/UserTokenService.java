package com.example.demoflights.service;


import com.example.demoflights.domain.UserToken;

public interface UserTokenService {

    UserToken getByPhone(String phone);

    void updateTokenByPhone(String phone, String token);

    void addNewUserToken(UserToken userToken);
}
