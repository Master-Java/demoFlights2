package com.example.demoflights.dao;


import com.example.demoflights.domain.UserToken;

public interface UserTokenDao {

    UserToken getByPhone(String phone);

    void updateTokenByPhone(String phone, String token);

    void addNewUserToken(UserToken userToken);
}
