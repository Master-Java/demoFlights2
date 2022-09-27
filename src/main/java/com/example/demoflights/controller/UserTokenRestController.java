package com.example.demoflights.controller;

import com.example.demoflights.domain.UserToken;
import com.example.demoflights.service.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/rest/v1/")
@Api(value = "UserToken Controller", description = "Операции с токенами пользователей")
public class UserTokenRestController {

    private final UserTokenService userTokenService;

    @PostMapping("/user_token")
    @ApiOperation("Создание нового токена пользователя")
    public ResponseEntity<?> createUserToken(@RequestBody UserToken userToken) {
        try {
            userTokenService.addNewUserToken(userToken);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user_token/{phone}")
    @ApiOperation("Получение объект - токен пользователя по телефону")
    public ResponseEntity<UserToken> getUserTokenByPhone(@PathVariable String phone) {
        try {
            return new ResponseEntity<>(userTokenService.getByPhone(phone), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/user_token/{phone}/{token}")
    @ApiOperation("Изменение токена пользователя по телефону")
    public ResponseEntity<?> updateUserTokenByPhone(@PathVariable String phone, @PathVariable String token) {
        try {
            userTokenService.updateTokenByPhone(phone, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}