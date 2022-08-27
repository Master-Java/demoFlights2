package com.example.demoflights.sevice.impl;

import com.example.demoflights.dao.UserTokenDao;
import com.example.demoflights.domain.UserToken;
import com.example.demoflights.sevice.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTokenServiceImpl implements UserTokenService {

    private final UserTokenDao userTokenDao;

    @Override
    public UserToken getByPhone(String phone) {
        return userTokenDao.getByPhone(phone);
    }

    @Override
    public void updateTokenByPhone(String phone, String token) {
        userTokenDao.updateTokenByPhone(phone, token);
    }

    @Override
    public void addNewUserToken(UserToken userToken) {
        userTokenDao.addNewUserToken(userToken);
    }
}
