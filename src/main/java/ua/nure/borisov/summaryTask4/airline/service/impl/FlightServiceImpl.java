package ua.nure.borisov.summaryTask4.airline.service.impl;

import ua.nure.borisov.summaryTask4.airline.dao.api.FlightDAO;
import ua.nure.borisov.summaryTask4.airline.dao.impl.FlightDAOImpl;
import ua.nure.borisov.summaryTask4.airline.dto.CrewDTO;
import ua.nure.borisov.summaryTask4.airline.dto.EmployeeDTO;
import ua.nure.borisov.summaryTask4.airline.dto.FlightDTO;
import ua.nure.borisov.summaryTask4.airline.entity.Crew;
import ua.nure.borisov.summaryTask4.airline.entity.Flight;
import ua.nure.borisov.summaryTask4.airline.service.api.CrewService;
import ua.nure.borisov.summaryTask4.airline.service.api.EmployeeService;
import ua.nure.borisov.summaryTask4.airline.service.api.FlightService;
import ua.nure.borisov.summaryTask4.airline.service.api.ServiceDataException;
import ua.nure.borisov.summaryTask4.airline.transaction.Transaction;
import ua.nure.borisov.summaryTask4.airline.transformer.Transformer;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 07.08.2016.
 */
public class FlightServiceImpl implements FlightService {
    private static final Logger LOGGER = Logger.getLogger(FlightServiceImpl.class.getName());

    @Override
    public void deleteFlight(int id) {
        FlightDAO flightDAO = new FlightDAOImpl();
        CrewService crewService = new CrewServiceImpl();
        Connection connection = Transaction.startTransaction();
        try {
            FlightDTO flightDTO = getFlightByNumber(id);
            crewService.deleteCrewTeam(flightDTO.getCrewTeam().getCrewTeamID(), connection);
            flightDAO.deleteFlight(id, connection);
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method deleteFlight: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }
    }

    @Override
    public FlightDTO getFlightByNumberWithConnection(int flightNumber, Connection connection) throws SQLException {
        FlightDAO flightDAO = new FlightDAOImpl();
        CrewService crewService = new CrewServiceImpl();
        Flight flight;

        flight = flightDAO.getFlightByNumber(flightNumber, connection);
        int crewTeamID = flightDAO.getCrewTeamIdByFlightNumber(flightNumber, connection);
        CrewDTO crewDTO = crewService.getCrewTeamByID(crewTeamID, connection);
        Crew crew = Transformer.crewDTOToCrew(crewDTO);
        flight.setCrewTeam(crew);

        return Transformer.flightToFlightDTO(flight);

    }

    @Override
    public FlightDTO getFlightByNumber(int flightNumber) {
        Connection connection = Transaction.startTransaction();
        FlightDAO flightDAO = new FlightDAOImpl();
        CrewService crewService = new CrewServiceImpl();
        Flight flight = null;
        try {
            flight = flightDAO.getFlightByNumber(flightNumber, connection);
            int crewTeamID = flightDAO.getCrewTeamIdByFlightNumber(flightNumber, connection);
            CrewDTO crewDTO = crewService.getCrewTeamByID(crewTeamID, connection);
            Crew crew = Transformer.crewDTOToCrew(crewDTO);
            flight.setCrewTeam(crew);
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method getFlightByNumber: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }
        return Transformer.flightToFlightDTO(flight);

    }

    @Override
    public List<FlightDTO> getFlightByMainParameters(String departure, String destination, String date) {
        FlightDAO flightDAO = new FlightDAOImpl();
        List<FlightDTO> allFlightsDTO = new ArrayList<FlightDTO>();
        List<Flight> allFlights = null;
        try {
            allFlights = flightDAO.getFlightByMainParameters(departure, destination, date);
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "Transform date exception: ", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method getFlightsByMainParameters: ", e);
        }
        if (allFlights != null) {
            for (Flight flight : allFlights) {
                FlightDTO flightDTO;
                flightDTO = Transformer.flightToFlightDTO(flight);
                allFlightsDTO.add(flightDTO);
            }
        }
        return allFlightsDTO;
    }

    @Override
    public List<FlightDTO> getFlightsByDepartureDate(String date) {
        FlightDAO flightDAO = new FlightDAOImpl();
        List<FlightDTO> allFlightsDTO = new ArrayList<FlightDTO>();
        List<Flight> allFlights = null;
        try {
            allFlights = flightDAO.getFlightsByDepartureDate(date);
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "Transform date exception: ", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method getFlightsByDepartureDate: ", e);
        }
        if (allFlights != null) {
            for (Flight flight : allFlights) {
                FlightDTO flightDTO;
                flightDTO = Transformer.flightToFlightDTO(flight);
                allFlightsDTO.add(flightDTO);
            }
        }
        return allFlightsDTO;
    }

    @Override
    public List<FlightDTO> getFlightsByDestinationPoint(String destination) {
        FlightDAO flightDAO = new FlightDAOImpl();
        List<FlightDTO> allFlightsDTO = new ArrayList<FlightDTO>();
        List<Flight> allFlights = null;
        try {
            allFlights = flightDAO.getFlightsByDestinationPoint(destination);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method getFlightsByDestinationPoint: ", e);
        }
        if (allFlights != null) {
            for (Flight flight : allFlights) {
                FlightDTO flightDTO;
                flightDTO = Transformer.flightToFlightDTO(flight);
                allFlightsDTO.add(flightDTO);
            }
        }
        return allFlightsDTO;
    }

    @Override
    public List<FlightDTO> getFlightsByDeparturePoint(String departure) {
        FlightDAO flightDAO = new FlightDAOImpl();
        List<FlightDTO> allFlightsDTO = new ArrayList<FlightDTO>();
        List<Flight> allFlights = null;
        try {
            allFlights = flightDAO.getFlightsByDeparturePoint(departure);

        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Exception in method getFlightsByDeparturePoint: ", e);
        }
        if (allFlights != null) {
            for (Flight flight : allFlights) {
                FlightDTO flightDTO;
                flightDTO = Transformer.flightToFlightDTO(flight);
                System.out.println("Transformed flight " + flightDTO);
                allFlightsDTO.add(flightDTO);
            }
        }

        System.out.println(allFlightsDTO);
        return allFlightsDTO;
    }

    @Override
    public void deleteCrewByFlightID(int id) {
        Connection connection = Transaction.startTransaction();
        try {
            FlightDTO flightDTO = getFlightByNumber(id);
            CrewDTO crewDTO = flightDTO.getCrewTeam();
            List<EmployeeDTO> employeeDTOList = crewDTO.getCrewTeam();
            List<Integer> integerList = new ArrayList<Integer>();
            for (EmployeeDTO employeeDTO : employeeDTOList) {
                integerList.add(employeeDTO.getEmployeeID());
            }
            EmployeeService employeeService = new EmployeeServiceImpl();
            employeeService.updateSelectedEmployeesStatusToFree(integerList, connection);
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method deleteCrewByFlightID: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }
    }

    @Override
    public void updateFlight(int idFlight, int idCrew, FlightDTO flightDTO, List<String> allEmployeeNames) {
        Connection connection = Transaction.startTransaction();
        EmployeeService employeeService = new EmployeeServiceImpl();
        CrewDTO crewDTO = new CrewDTO();
        List<EmployeeDTO> crewTeam = employeeService.getEmployeeByName(allEmployeeNames, connection);

        crewDTO.setCrewTeamID(idCrew);
        crewDTO.setCrewTeam(crewTeam);
        flightDTO.setCrewTeam(crewDTO);

        Flight flight = Transformer.flightDTOToFlight(flightDTO);
        FlightDAO flightDAO = new FlightDAOImpl();

        CrewService crewService = new CrewServiceImpl();

        try {
            crewService.updateCrewTeam(idCrew, crewDTO);
            flightDAO.updateFlight(idFlight, flight, connection);
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method updateFlight: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }
    }

    @Override
    public void updateFlightByRequest(int idFlight, int idCrew, FlightDTO flightDTO, List<String> allEmployeeNames) {
        Connection connection = Transaction.startTransaction();
        EmployeeService employeeService = new EmployeeServiceImpl();
        CrewService crewService = new CrewServiceImpl();
        FlightDAO flightDAO = new FlightDAOImpl();
        CrewDTO crewDTO = new CrewDTO();
        try {
            List<EmployeeDTO> crewTeam = employeeService.getEmployeeByName(allEmployeeNames, connection);

            crewDTO.setCrewTeamID(idCrew);
            crewDTO.setCrewTeam(crewTeam);
            flightDTO.setCrewTeam(crewDTO);
            Flight flight = Transformer.flightDTOToFlight(flightDTO);
            flightDAO.updateFlight(idFlight, flight, connection);

            crewService.updateCrewTeamByRequest(flightDTO.getCrewTeam(), connection);
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method updateFlightByRequest: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }
    }

    @Override
    public int getCrewTeamIdByFlightNumber(int id) throws SQLException {
        FlightDAO flightDAO = new FlightDAOImpl();
        return flightDAO.getCrewTeamIdByFlightNumber(id);

    }

    @Override
    public List<FlightDTO> getAllFlights() {
        Connection connection = Transaction.startTransaction();
        FlightDAO flightDAO = new FlightDAOImpl();
        List<FlightDTO> allFlightsDTO = new ArrayList<FlightDTO>();
        List<Flight> allFlights;
        try {
            allFlights = flightDAO.getAllFlights(connection);
            for (Flight flight : allFlights) {
                FlightDTO flightDTO;
                flightDTO = Transformer.flightToFlightDTO(flight);
                allFlightsDTO.add(flightDTO);
            }
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method getAllFlights: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }


        return allFlightsDTO;
    }

    @Override
    public List<FlightDTO> sortFlightsByNumberAndName() {
        FlightDAO flightDAO = new FlightDAOImpl();
        List<FlightDTO> allFlightsDTO = new ArrayList<FlightDTO>();
        List<Flight> allFlights;
        Connection connection = Transaction.startTransaction();
        try {
            allFlights = flightDAO.sortFlightsByNumberAndName(connection);
            if (allFlights != null) {
                for (Flight flight : allFlights) {
                    FlightDTO flightDTO;
                    flightDTO = Transformer.flightToFlightDTO(flight);
                    allFlightsDTO.add(flightDTO);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method sortFlightsByNumberAndName: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }

        return allFlightsDTO;
    }

    @Override
    public List<FlightDTO> sortFlightsByDeparturePoint() {
        FlightDAO flightDAO = new FlightDAOImpl();
        List<FlightDTO> allFlightsDTO = new ArrayList<FlightDTO>();
        List<Flight> allFlights;
        Connection connection = Transaction.startTransaction();
        try {
            allFlights = flightDAO.sortFlightsByDeparturePoint(connection);
            if (allFlights != null) {
                for (Flight flight : allFlights) {
                    FlightDTO flightDTO;
                    flightDTO = Transformer.flightToFlightDTO(flight);
                    allFlightsDTO.add(flightDTO);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method sortFlightsByDeparturePoint: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }

        return allFlightsDTO;
    }

    @Override
    public int createFlight(FlightDTO flightDTO, List<String> allEmployeesNames) throws ServiceDataException {
        Connection connection = Transaction.startTransaction();
        EmployeeService employeeService = new EmployeeServiceImpl();
        CrewService crewService = new CrewServiceImpl();

        List<EmployeeDTO> crewTeam = employeeService.getEmployeeByName(allEmployeesNames, connection);
        CrewDTO crewDTO = crewService.createCrewTeam(crewTeam, connection);
        flightDTO.setCrewTeam(crewDTO);
        Flight flight = Transformer.flightDTOToFlight(flightDTO);
        FlightDAO flightDAO = new FlightDAOImpl();
        int flightID = 0;
        try {
            flightID = flightDAO.createFlight(flight, connection);
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method createFlight: ", e);
            throw new ServiceDataException("No record inserted", e);
        } finally {
            Transaction.endTransaction(connection);
        }
        return flightID;
    }

    @Override
    public List<FlightDTO> sortFlightsByName() {
        FlightDAO flightDAO = new FlightDAOImpl();
        List<FlightDTO> allFlightsDTO = new ArrayList<FlightDTO>();
        List<Flight> allFlights;
        Connection connection = Transaction.startTransaction();
        try {
            allFlights = flightDAO.sortFlightsByName(connection);
            if (allFlights != null) {
                for (Flight flight : allFlights) {
                    FlightDTO flightDTO;
                    flightDTO = Transformer.flightToFlightDTO(flight);
                    allFlightsDTO.add(flightDTO);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method sortFlightsByName: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }

        return allFlightsDTO;
    }

    @Override
    public List<FlightDTO> sortFlightsByDestinationAndDeparture() {
        FlightDAO flightDAO = new FlightDAOImpl();
        List<FlightDTO> allFlightsDTO = new ArrayList<FlightDTO>();
        List<Flight> allFlights;
        Connection connection = Transaction.startTransaction();
        try {
            allFlights = flightDAO.sortFlightsByDestinationAndDeparture(connection);
            if (allFlights != null) {
                for (Flight flight : allFlights) {
                    FlightDTO flightDTO;
                    flightDTO = Transformer.flightToFlightDTO(flight);
                    allFlightsDTO.add(flightDTO);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method sortFlightsByDepartureAndDestination: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }

        return allFlightsDTO;
    }
}
