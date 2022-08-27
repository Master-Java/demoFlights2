package com.example.demoflights.dao.jdbc;

import com.example.demoflights.dao.UserTokenDao;
import com.example.demoflights.dao.mapper.UserTokenMapper;
import com.example.demoflights.domain.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTokenDaoJdbcImpl implements UserTokenDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public UserToken getByPhone(String phone) {
        String sql = "" +
                "SELECT *" +
                " FROM user_token" +
                " WHERE user_phone = :phone";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("phone", phone), new UserTokenMapper());
    }

    @Override
    public void updateTokenByPhone(String phone, String token) {
        String sql = "" +
                " UPDATE user_token" +
                " SET" +
                "  user_token = :token," +
                " WHERE user_phone = :phone;";
        MapSqlParameterSource param = new MapSqlParameterSource("phone", phone);
        param.addValue("token", token);
        jdbcTemplate.update(sql, param);
    }

    @Override
    public void addNewUserToken(UserToken userToken) {
        String sql = "" +
                "INSERT INTO user_token (user_phone, user_token)" +
                " VALUES (:phone, :token);";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("phone", userToken.getUserPhone());
        param.addValue("token", userToken.getUserToken());
        jdbcTemplate.update(sql, param);
    }
}
