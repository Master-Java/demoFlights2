package com.example.demoflights.dao;


import com.example.demoflights.domain.User;

import java.util.List;

public interface UserDao {
    User getUserByPhone(String phone);

    List<String> getUsersPhoneById(int id);

    Boolean checkExistUserByPhone(String phone);

    void createUser(User user);

    void updateUser(User user, String phone);
}
