package com.example.demoflights.dao.mapper;


import com.example.demoflights.domain.UserToken;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTokenMapper implements RowMapper<UserToken> {

    @Override
    public UserToken mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserToken.builder()
                .id(rs.getLong("id"))
                .userToken(rs.getString("user_token"))
                .userPhone(rs.getString("user_phone"))
                .build();
    }
}