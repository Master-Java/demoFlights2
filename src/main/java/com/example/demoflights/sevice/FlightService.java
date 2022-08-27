package com.example.demoflights.sevice;


import com.example.demoflights.domain.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getAll();

    Flight getByFlightNumber(long flightNumber);

    void updateFlight(Flight flight, int id);

    void createFlight(Flight flight);

    List<Flight> getFlightsByUserPhone(String phone);

    void deleteFlight(int id);

    void updateStatus();

}