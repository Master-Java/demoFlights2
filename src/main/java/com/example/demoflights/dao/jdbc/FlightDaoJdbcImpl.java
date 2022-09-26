package com.example.demoflights.dao.jdbc;

import com.example.demoflights.dao.FlightDao;
import com.example.demoflights.dao.mapper.FlightMapper;
import com.example.demoflights.domain.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FlightDaoJdbcImpl implements FlightDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Flight> getAll() {
        String sql = "SELECT *" +
                " FROM flights;";
        return jdbcTemplate.query(sql, new MapSqlParameterSource(), new FlightMapper());
    }

    @Override
    public void updateFlight(Flight flight, int id) {
        String sql = "" +
                "UPDATE flights" +
                " SET" +
                "  flight_number = :flightNumber," +
                "  from_point = :fromPoint," +
                "  to_point = :toPoint," +
                "  to_point_date = :toPointDate," +
                "  from_point_date = :fromPointDate," +
                "  air_company = :airCompany," +
                "  flight_status = :flightStatus," +
                "  comment = :comment" +
                " WHERE id = :id;";
        MapSqlParameterSource param = getMapSqlParameterSource(flight);
        param.addValue("id", id);
        jdbcTemplate.update(sql, param);
    }

    @Override
    public void deleteLinkPassengersPhoneWithFlight(int id) {
        String sql = "DELETE FROM users_flights WHERE id = :id;";
        jdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    @Override
    public void createNewLinkPassengersPhoneWithFlight(int id, String passengerPhone) {
        String sql = "" +
                "INSERT INTO users_flights (user_phone, id)" +
                " VALUES (:userPhone, :id);";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        param.addValue("userPhone", passengerPhone);
        jdbcTemplate.update(sql, param);
    }

    @Override
    public int createFlight(Flight flight) {
        String sql = "" +
                "INSERT INTO flights (flight_number, from_point, to_point, to_point_date, from_point_date, air_company, flight_status, comment)" +
                " VALUES (:flightNumber, :fromPoint, :toPoint, :toPointDate, :fromPointDate, :airCompany, :flightStatus, :comment);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, getMapSqlParameterSource(flight), keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public List<Flight> getFlightsByUserPhone(String phone) {
        String sql = "" +
                "SELECT *" +
                " FROM flights f JOIN users_flights uf ON f.id = uf.id" +
                " WHERE uf.user_phone = :phone;";
        return jdbcTemplate.query(sql, new MapSqlParameterSource("phone", phone), new FlightMapper());
    }

    @Override
    public void deleteFlight(int id) {
        String sql = "DELETE FROM flights WHERE id = :id;";
        jdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    @Override
    public Flight getByFlightNumber(long flightNumber) {
        String sql = "" +
                "SELECT *" +
                " FROM flights" +
                " WHERE flight_number = :flightNumber;";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("flightNumber", flightNumber), new FlightMapper());
    }

    @Override
    public void deleteLinkPassengersPhoneWithFlightByFlightId(int id) {
        String sql = "DELETE FROM users_flights WHERE id = :id;";
        jdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    private MapSqlParameterSource getMapSqlParameterSource(Flight flight) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("fromPoint", flight.getFromPoint());
        param.addValue("fromPointDate", flight.getFromPointDate());
        param.addValue("toPoint", flight.getToPoint());
        param.addValue("toPointDate", flight.getToPointDate());
        param.addValue("airCompany", flight.getAirCompany());
        param.addValue("flightStatus", flight.getFlightStatus());
        param.addValue("comment", flight.getComment());
        param.addValue("flightNumber", flight.getFlightNumber());
        return param;
    }

    @Override
    public void updateStatusInAWay() {
        String sql = "" +
                "UPDATE flights" +
                " SET flight_status = 'inaway'" +
                " WHERE DATE_TRUNC('minute', TO_TIMESTAMP(from_point_date, 'YYYY-MM-DD HH24:MI:SS')) = DATE_TRUNC('minute', now())" +
                "   AND flight_status = 'timetotime'";
        jdbcTemplate.update(sql, new MapSqlParameterSource());
    }

    @Override
    public void updateStatusArchive() {
        String sql = "" +
                "UPDATE flights" +
                " SET flight_status = 'archive'" +
                " WHERE DATE_TRUNC('minute', TO_TIMESTAMP(to_point_date, 'YYYY-MM-DD HH24:MI:SS')) = DATE_TRUNC('minute', now())" +
                "   AND flight_status = 'inaway'";
        jdbcTemplate.update(sql, new MapSqlParameterSource());
    }

    @Override
    public void deleteAllFightsLinks() {
        String sql = "DELETE FROM users_flights;";
        jdbcTemplate.update(sql, new MapSqlParameterSource());
    }
}