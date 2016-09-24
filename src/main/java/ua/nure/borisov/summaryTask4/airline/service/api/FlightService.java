package ua.nure.borisov.summaryTask4.airline.service.api;

import ua.nure.borisov.summaryTask4.airline.dto.FlightDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 07.08.2016.
 */
public interface FlightService {
    public FlightDTO getFlightByNumber(int flightNumber);
    public List<FlightDTO> getFlightByMainParameters(String departure,String destination,String date);
    public List<FlightDTO> getFlightsByDepartureDate(String date);
    public List<FlightDTO> getFlightsByDestinationPoint(String destination);
    public List<FlightDTO> getFlightsByDeparturePoint(String departure);
    public List<FlightDTO> getAllFlights();
    public List<FlightDTO> sortFlightsByNumberAndName();
    public List<FlightDTO> sortFlightsByDeparturePoint();
    public List<FlightDTO> sortFlightsByName();
    public List<FlightDTO> sortFlightsByDestinationAndDeparture();
    public int createFlight(FlightDTO flightDTO,List<String> allEmployeesNames) throws ServiceDataException;
    public void deleteFlight(int id);
    public void updateFlight(int idFlight,int idCrew, FlightDTO flightDTO,List<String>allEmployeeNames);
    public void deleteCrewByFlightID(int id);
    public FlightDTO getFlightByNumberWithConnection(int flightNumber, Connection connection) throws SQLException;
    public void updateFlightByRequest(int idFlight, int idCrew, FlightDTO flightDTO, List<String>allEmployeeNames);
    public int getCrewTeamIdByFlightNumber(int id) throws SQLException;
}
