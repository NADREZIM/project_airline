package ua.nure.borisov.summaryTask4.airline.dao.api;

import ua.nure.borisov.summaryTask4.airline.entity.Flight;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by User on 04.08.2016.
 */
public interface FlightDAO {
    public Flight getFlightByNumber(int flightNumber, Connection connection) throws SQLException;
    public List<Flight> getFlightByMainParameters(String departure,String destination,String date) throws ParseException, SQLException;
    public List<Flight> getFlightsByDepartureDate(String date) throws ParseException, SQLException;
    public List<Flight> getFlightsByDestinationPoint(String destination) throws SQLException;
    public List<Flight> getFlightsByDeparturePoint(String departure) throws SQLException;
    public List<Flight> getAllFlights(Connection connection) throws SQLException;
    public List<Flight> sortFlightsByNumberAndName(Connection connection) throws SQLException;
    public List<Flight> sortFlightsByDeparturePoint(Connection connection) throws SQLException;
    public List<Flight> sortFlightsByName(Connection connection) throws SQLException;
    public List<Flight> sortFlightsByDestinationAndDeparture(Connection connection) throws SQLException;
    public int createFlight(Flight flight,Connection connection) throws SQLException;
    public void deleteFlight(int id,Connection connection) throws SQLException;
    public void updateFlight(int id, Flight flight,Connection connection)throws SQLException;
    public int getCrewTeamIdByFlightNumber(int id, Connection connection)throws SQLException;
    public int getCrewTeamIdByFlightNumber(int id) throws SQLException;

}
