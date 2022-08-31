package com.example.demoflights.controller;

import com.example.demoflights.domain.Flight;
import com.example.demoflights.domain.User;
import com.example.demoflights.domain.UserToken;
import com.example.demoflights.sevice.FlightService;
import com.example.demoflights.sevice.UserService;
import com.example.demoflights.sevice.UserTokenService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/rest/v1/")
public class MainRestController {

    private final FlightService flightService;
    private final UserService userService;
    private final UserTokenService userTokenService;

    @GetMapping("/flights")
    @ApiOperation("Получение всех полётов")
    public ResponseEntity<List<Flight>> getAllTickets() {
        try {
            return new ResponseEntity<>(flightService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/flight")
    @ApiOperation("Создание нового полёта")
    public ResponseEntity<?> createFlight(@RequestBody Flight flight) {
        try {
            flightService.createFlight(flight);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/flight/{id}")
    @ApiOperation("Изменение полёта")
    public ResponseEntity<?> updateFlight(@RequestBody Flight flight, @PathVariable int id) {
        try {
            flightService.updateFlight(flight, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/flights/{phone}")
    @ApiOperation("Получение полётов для указанного номера телефона (пользователя)")
    public ResponseEntity<List<Flight>> updateUser(@PathVariable String phone) {
        try {
            return new ResponseEntity<>(flightService.getFlightsByUserPhone(phone), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/flights/delete/{idFlight}")
    @ApiOperation("Удаление полёта по его id")
    public ResponseEntity<?> deleteFlights(@PathVariable int idFlight) {
        try {
            flightService.deleteFlight(idFlight);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user")
    @ApiOperation("Создание нового пользователя")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/{phone}")
    @ApiOperation("Изменение пользователя")
    public ResponseEntity<?> updateUser(@RequestBody User user, String phone) {
        try {
            userService.updateUser(user, phone);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/by_phone/{phone}")
    @ApiOperation("Получение пользователя по номеру его телефона")
    public ResponseEntity<User> getUserByPhone(@PathVariable String phone) {
        try {
            return new ResponseEntity<>(userService.getUserByPhone(phone), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

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
