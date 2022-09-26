package com.example.demoflights.controller;

import com.example.demoflights.domain.Flight;
import com.example.demoflights.sevice.FlightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/rest/v1/")
@Api(value = "Flights Controller", description = "Операции с полётами")
public class FlightsRestController {

    @Value("${rest.api.password.confirm}")
    private String password;
    private final FlightService flightService;

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

    @DeleteMapping("/flights/delete/all/{password}")
    @ApiOperation("Удаление связей полёта и пользователей")
    public ResponseEntity<?> deleteAllFightsLinks(@PathVariable String password) {
        try {
            if (password.equals(this.password)) {
                flightService.deleteAllFightsLinks();
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
