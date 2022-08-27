package com.example.demoflights.controller;

import com.example.demoflights.domain.Flight;
import com.example.demoflights.domain.User;
import com.example.demoflights.domain.UserToken;
import com.example.demoflights.sevice.FlightService;
import com.example.demoflights.sevice.UserService;
import com.example.demoflights.sevice.UserTokenService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/rest/v1/")
public class MainRestController {

    private final FlightService flightService;
    private final UserService userService;
    private final UserTokenService userTokenService;

    @GetMapping("/flights")
    @ApiOperation("Получение всех полётов")
    public List<Flight> getAllTickets() {
        return flightService.getAll();
    }

    @PostMapping("/flight")
    @ApiOperation("Создание нового полёта")
    public void createFlight(@RequestBody Flight flight) {
        flightService.createFlight(flight);
    }

    @PutMapping("/flight/{id}")
    @ApiOperation("Изменение полёта")
    public void updateFlight(@RequestBody Flight flight, @PathVariable int id) {
        flightService.updateFlight(flight, id);
    }

    @GetMapping("/flights/{phone}")
    @ApiOperation("Получение полётов для указанного номера телефона (пользователя)")
    public List<Flight> updateUser(@PathVariable String phone) {
        return flightService.getFlightsByUserPhone(phone);
    }

    @DeleteMapping("/flights/delete/{idFlight}")
    @ApiOperation("Удаление полёта по его id")
    public void deleteFlights(@PathVariable int idFlight) {
        flightService.deleteFlight(idFlight);
    }

    @PostMapping("/user")
    @ApiOperation("Создание нового пользователя")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping("/user/{phone}")
    @ApiOperation("Изменение пользователя")
    public void updateUser(@RequestBody User user, String phone) {
        userService.updateUser(user, phone);
    }

    @GetMapping("/user/by_phone/{phone}")
    @ApiOperation("Получение пользователя по номеру его телефона")
    public User getUserByPhone(@PathVariable String phone) {
        return userService.getUserByPhone(phone);
    }

    @PostMapping("/user_token")
    @ApiOperation("Создание нового токена пользователя")
    public void createUserToken(@RequestBody UserToken userToken) {
        userTokenService.addNewUserToken(userToken);
    }

    @GetMapping("/user_token/{phone}")
    @ApiOperation("Получение объект - токен пользователя по телефону")
    public UserToken getUserTokenByPhone(@PathVariable String phone) {
        return userTokenService.getByPhone(phone);
    }

    @PutMapping("/user_token/{phone}/{token}")
    @ApiOperation("Изменение токена пользователя по телефону")
    public void updateUserTokenByPhone(@PathVariable String phone, @PathVariable String token) {
        userTokenService.updateTokenByPhone(phone, token);
    }
}
