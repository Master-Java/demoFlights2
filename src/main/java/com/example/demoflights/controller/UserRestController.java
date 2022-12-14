package com.example.demoflights.controller;

import com.example.demoflights.domain.User;
import com.example.demoflights.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/rest/v1/user")
@Api(value = "User Controller", description = "Операции с пользователями")
public class UserRestController {

    private final UserService userService;

    @PostMapping()
    @ApiOperation("Создание нового пользователя")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{phone}")
    @ApiOperation("Изменение пользователя")
    public ResponseEntity<?> updateUser(@RequestBody User user, String phone) {
        try {
            userService.updateUser(user, phone);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by_phone/{phone}")
    @ApiOperation("Получение пользователя по номеру его телефона")
    public ResponseEntity<User> getUserByPhone(@PathVariable String phone) {
        try {
            return new ResponseEntity<>(userService.getUserByPhone(phone), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}