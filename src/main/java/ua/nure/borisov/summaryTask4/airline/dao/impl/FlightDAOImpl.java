package ua.nure.borisov.summaryTask4.airline.dao.impl;

import ua.nure.borisov.summaryTask4.airline.connection.ConnectionDB;
import ua.nure.borisov.summaryTask4.airline.dao.api.CrewDAO;
import ua.nure.borisov.summaryTask4.airline.dao.api.EmployeeDAO;
import ua.nure.borisov.summaryTask4.airline.dao.api.FlightDAO;
import ua.nure.borisov.summaryTask4.airline.entity.Crew;
import ua.nure.borisov.summaryTask4.airline.entity.Employee;
import ua.nure.borisov.summaryTask4.airline.entity.Flight;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 04.08.2016.
 */
public class FlightDAOImpl implements FlightDAO {
    private static final Logger LOGGER = Logger.getLogger(FlightDAOImpl.class.getName());
    private static final String GET_FLIGHT_NUMBER = "select*from flights where flight_number = ?";
    private static final String GET_FLIGHT_BY_MAIN_PARAMETERS = "select*from flights where departure = ? and destination = ? and departure_date = ?";
    private static final String GET_FLIGHTS_BY_DEPARTURE_DATE = "select*from flights where departure_date = ?";
    private static final String GET_FLIGHTS_BY_DESTINATION_POINT = "select*from flights where destination = ?";
    private static final String GET_FLIGHTS_BY_DEPARTURE_POINT = "select*from flights where departure = ?";
    private static final String GET_ALL_FLIGHTS = "select*from flights where status = 1";
    private static final String CREATE_FLIGHT = "insert into flights(flight_name,departure,destination,departure_date,crew,status)values (?,?,?,?,?,?)";
    private static final String DELETE_FLIGHT = "delete from flights where flight_number = ?";
    private static final String UPDATE_FLIGHT = "update flights set flight_name=IF(?='',flight_name,?), departure=IF(?='',departure,?), destination=IF(?='',destination,?), departure_date=IF(?='0001-01-01',departure_date,?), status=IF(?='',status,?) where flight_number=?";
    private static final String GET_CREW_TEAM_ID_BY_FLIGHT_NUMBER = "select crew from flights where flight_number = ?";

    @Override
    public int getCrewTeamIdByFlightNumber(int id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int crewTeamID = 0;
        try {
            preparedStatement = connection.prepareStatement(GET_CREW_TEAM_ID_BY_FLIGHT_NUMBER);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                crewTeamID = resultSet.getInt("crew");
            }
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
        }
        return crewTeamID;
    }


    @Override
    public int getCrewTeamIdByFlightNumber(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionDB.getInstance().getConnection();
        int crewTeamID = 0;
        try {
            preparedStatement = connection.prepareStatement(GET_CREW_TEAM_ID_BY_FLIGHT_NUMBER);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                crewTeamID = resultSet.getInt("crew");
            }
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return crewTeamID;
    }

    @Override
    public void deleteFlight(int id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FLIGHT);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } finally {
            closeStatement(preparedStatement);
        }
    }

    @Override
    public void updateFlight(int id, Flight flight, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_FLIGHT);
            preparedStatement.setString(1, flight.getFlightName());
            preparedStatement.setString(2, flight.getFlightName());
            preparedStatement.setString(3, flight.getPointOfDeparture());
            preparedStatement.setString(4, flight.getPointOfDeparture());
            preparedStatement.setString(5, flight.getPointOfDestination());
            preparedStatement.setString(6, flight.getPointOfDestination());
            preparedStatement.setDate(7, new java.sql.Date(flight.getDepartureDate().getTime()));
            preparedStatement.setDate(8, new java.sql.Date(flight.getDepartureDate().getTime()));
            int digitStatus = 0;
            if (flight.getFlightStatus()) {
                digitStatus = 1;
            }
            preparedStatement.setInt(9, digitStatus);
            preparedStatement.setInt(10, digitStatus);
            preparedStatement.setInt(11, id);
            preparedStatement.execute();
        } finally {
            closeStatement(preparedStatement);
        }

    }

    @Override
    public int createFlight(Flight flight,Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int autoID = 0;
        try {
            preparedStatement = connection.prepareStatement(CREATE_FLIGHT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, flight.getFlightName());
            preparedStatement.setString(2, flight.getPointOfDeparture());
            preparedStatement.setString(3, flight.getPointOfDestination());
            preparedStatement.setDate(4, new java.sql.Date(flight.getDepartureDate().getTime()));
            preparedStatement.setInt(5, flight.getCrewTeam().getCrewTeamID());
            int numberStatus = 0;
            if (flight.getFlightStatus()) {
                numberStatus = 1;
            }
            preparedStatement.setInt(6, numberStatus);
            preparedStatement.executeUpdate();
            rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                autoID = rs.getInt(1);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
        }
        return autoID;
    }

    @Override
    public Flight getFlightByNumber(int flightNumber, Connection connection) throws SQLException {
        Flight flight = new Flight();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(GET_FLIGHT_NUMBER);
            preparedStatement.setString(1, String.valueOf(flightNumber));
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                boolean status = false;
                flight.setFlightNumber(rs.getInt("flight_number"));
                flight.setFlightName(rs.getString("flight_name"));
                flight.setPointOfDeparture(rs.getString("departure"));
                flight.setPointOfDestination(rs.getString("destination"));
                flight.setDepartureDate(rs.getDate("departure_date"));
                int numberStatus = rs.getInt("status");
                if (numberStatus == 1) {
                    status = true;
                }
                flight.setFlightStatus(status);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
        }
        return flight;
    }

    @Override
    public List<Flight> getFlightByMainParameters(String departure, String destination, String date) throws ParseException, SQLException {
        List<Flight> requiredFlights = new ArrayList<Flight>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection connection = null;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date formatDate = format.parse(date);
        try {
            connection = ConnectionDB.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_FLIGHT_BY_MAIN_PARAMETERS);
            preparedStatement.setString(1, departure);
            preparedStatement.setString(2, destination);
            if (formatDate != null) {
                preparedStatement.setDate(3, new java.sql.Date(formatDate.getTime()));
            }
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                boolean status = false;
                flight.setFlightNumber(rs.getInt("flight_number"));
                flight.setFlightName(rs.getString("flight_name"));
                flight.setPointOfDeparture(rs.getString("departure"));
                flight.setPointOfDestination(rs.getString("destination"));
                flight.setDepartureDate(rs.getDate("departure_date"));
                int numberStatus = rs.getInt("status");
                if (numberStatus == 1) {
                    status = true;
                }
                flight.setFlightStatus(status);
                requiredFlights.add(flight);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return requiredFlights;
    }

    @Override
    public List<Flight> getFlightsByDepartureDate(String date) throws ParseException, SQLException {
        List<Flight> requiredFlights = new ArrayList<Flight>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection connection = null;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date formatDate = format.parse(date);
        try {
            connection = ConnectionDB.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_FLIGHTS_BY_DEPARTURE_DATE);
            if (formatDate != null) {
                preparedStatement.setDate(1, new java.sql.Date(formatDate.getTime()));
            }
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                boolean status = false;
                flight.setFlightNumber(rs.getInt("flight_number"));
                flight.setFlightName(rs.getString("flight_name"));
                flight.setPointOfDeparture(rs.getString("departure"));
                flight.setPointOfDestination(rs.getString("destination"));
                flight.setDepartureDate(rs.getDate("departure_date"));
                int numberStatus = rs.getInt("status");
                if (numberStatus == 1) {
                    status = true;
                }
                flight.setFlightStatus(status);
                requiredFlights.add(flight);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return requiredFlights;
    }

    @Override
    public List<Flight> getFlightsByDestinationPoint(String destination) throws SQLException {
        List<Flight> requiredFlights = new ArrayList<Flight>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_FLIGHTS_BY_DESTINATION_POINT);
            preparedStatement.setString(1, destination);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                boolean status = false;
                flight.setFlightNumber(rs.getInt("flight_number"));
                flight.setFlightName(rs.getString("flight_name"));
                flight.setPointOfDeparture(rs.getString("departure"));
                flight.setPointOfDestination(rs.getString("destination"));
                flight.setDepartureDate(rs.getDate("departure_date"));
                int numberStatus = rs.getInt("status");
                if (numberStatus == 1) {
                    status = true;
                }
                flight.setFlightStatus(status);
                requiredFlights.add(flight);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return requiredFlights;
    }

    @Override
    public List<Flight> getFlightsByDeparturePoint(String departure) throws SQLException {
        List<Flight> requiredFlights = new ArrayList<Flight>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(GET_FLIGHTS_BY_DEPARTURE_POINT);
            preparedStatement.setString(1, departure);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                boolean status = false;
                flight.setFlightNumber(rs.getInt("flight_number"));
                flight.setFlightName(rs.getString("flight_name"));
                flight.setPointOfDeparture(rs.getString("departure"));
                flight.setPointOfDestination(rs.getString("destination"));
                flight.setDepartureDate(rs.getDate("departure_date"));
                int numberStatus = rs.getInt("status");
                if (numberStatus == 1) {
                    status = true;
                }
                flight.setFlightStatus(status);
                requiredFlights.add(flight);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return requiredFlights;
    }


    @Override
    public List<Flight> getAllFlights(Connection connection) throws SQLException {
        List<Flight> allFlights = new ArrayList<Flight>();
        java.sql.Statement statement = null;
        ResultSet rs = null;
        CrewDAO crewDAO = new CrewDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(GET_ALL_FLIGHTS);
            while (rs.next()) {
                List<Integer> employeeID;
                List<Employee> employeeList;
                Flight flight = new Flight();
                flight.setFlightNumber(rs.getInt("flight_number"));
                flight.setPointOfDeparture(rs.getString("departure"));
                flight.setPointOfDestination(rs.getString("destination"));
                int crewTeamID = rs.getInt("crew");
                Crew crew = new Crew();
                crew.setCrewTeamID(crewTeamID);
                employeeID = crewDAO.getEmployeeIDByCrewTeamID(crewTeamID, connection);
                employeeList = employeeDAO.getCrewTeamEmployeesByID(employeeID, connection);
                crew.setCrewTeam(employeeList);
                flight.setCrewTeam(crew);
                flight.setDepartureDate(rs.getDate("departure_date"));
                flight.setFlightName(rs.getString("flight_name"));
                int numberStatus = rs.getInt("status");
                boolean status = false;
                if (numberStatus > 0) {
                    status = true;
                }
                flight.setFlightStatus(status);
                allFlights.add(flight);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return allFlights;
    }

    @Override
    public List<Flight> sortFlightsByDestinationAndDeparture(Connection connection) throws SQLException {
        List<Flight> allFlights = getAllFlights(connection);
        Collections.sort(allFlights, new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.getPointOfDestination().compareTo(o2.getPointOfDestination());
            }
        }.thenComparing(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.getPointOfDeparture().compareTo(o2.getPointOfDeparture());
            }
        }));
        return allFlights;
    }

    @Override
    public List<Flight> sortFlightsByNumberAndName(Connection connection) throws SQLException {
        List<Flight> allFlights = getAllFlights(connection);
        Collections.sort(allFlights, new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.getFlightNumber() - o2.getFlightNumber();
            }
        }.thenComparing(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return o1.getFlightNumber() - o2.getFlightNumber();
            }
        }));
        return allFlights;
    }

    @Override
    public List<Flight> sortFlightsByDeparturePoint(Connection connection) throws SQLException {
        List<Flight> allFlights = getAllFlights(connection);
        Collections.sort(allFlights, new Comparator<Flight>() {
            public int compare(Flight o1, Flight o2) {
                return o1.getPointOfDeparture().compareTo(o2.getPointOfDeparture());
            }
        });
        return allFlights;
    }

    @Override
    public List<Flight> sortFlightsByName(Connection connection) throws SQLException {
        List<Flight> allFlights = getAllFlights(connection);
        Collections.sort(allFlights, new Comparator<Flight>() {
            public int compare(Flight o1, Flight o2) {
                return o1.getFlightName().compareTo(o2.getFlightName());
            }
        });

        return allFlights;
    }

    public void closeStatement(Statement statement) {
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "PreparedStatement is not closed", e);
            }
    }

    public void closeResultSet(ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "PreparedStatement is not closed", e);
            }
    }

    public void closeConnection(Connection connection) {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "PreparedStatement is not closed", e);
            }
    }

}
