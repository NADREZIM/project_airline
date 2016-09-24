package ua.nure.borisov.summaryTask4.airline.dao.impl;

import ua.nure.borisov.summaryTask4.airline.dao.api.CrewDAO;
import ua.nure.borisov.summaryTask4.airline.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 05.08.2016.
 */
public class CrewDAOImpl implements CrewDAO {
    private static final Logger LOGGER = Logger.getLogger(CrewDAOImpl.class.getName());
    private static final String CREATE_CREW_TEAM_NUMBER = "INSERT INTO crew_team_number(ordinal_number)value(?)";
    private static final String SELECT_ALL_CREW_TEAM_ID = "select employee_id from crew_team where crew_team_id = ?";
    private static final String DELETE_CREW_TEAM = "delete crew_team, crew_team_number from crew_team,crew_team_number " +
            "WHERE crew_team.crew_team_id=? and crew_team_number.number=?";
    private static final String SELECT_ALL_FROM_CREW_TEAM = "select*from crew_team";


    @Override
    public HashMap<Integer, List<Integer>> selectAllFromCrewTeam(Connection connection) throws SQLException {
        Statement statement = null;
        ResultSet rs = null;
        int temp = 0;
        List<Integer> employeesID = new ArrayList<Integer>();
        HashMap<Integer, List<Integer>> crewTeamAndEmployeesID = new HashMap<Integer, List<Integer>>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(SELECT_ALL_FROM_CREW_TEAM);
            while (rs.next()) {
                int crewID = rs.getInt("crew_team_id");
                if (temp != 0) {
                    if (temp == crewID) {
                        employeesID.add(rs.getInt("employee_id"));
                        temp = crewID;
                    } else {
                        ArrayList<Integer> tempList = new ArrayList<Integer>();
                        tempList.addAll(employeesID);
                        crewTeamAndEmployeesID.put(temp, tempList);
                        employeesID.clear();
                        temp = crewID;
                        employeesID.add(rs.getInt("employee_id"));
                    }
                } else {
                    temp = crewID;
                    employeesID.add(rs.getInt("employee_id"));
                }
            }
            crewTeamAndEmployeesID.put(temp, employeesID);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }

        return crewTeamAndEmployeesID;
    }

    @Override
    public List<Integer> getEmployeeIDByCrewTeamID(int id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Integer> idList = new ArrayList<Integer>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_CREW_TEAM_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int employeeID = rs.getInt("employee_id");
                idList.add(employeeID);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
        }
        return idList;
    }

    @Override
    public int createCrewTeam(List<Employee> crewTeam, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Random random = new Random();
        int rnd = random.nextInt(9999);
        int autoID = 0;
        try {
            preparedStatement = connection.prepareStatement(CREATE_CREW_TEAM_NUMBER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, rnd);
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
    public List<Integer> deleteCrewTeam(int id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Integer> idList = new ArrayList<Integer>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_CREW_TEAM_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int employeeID = rs.getInt("employee_id");
                idList.add(employeeID);
            }
            closeResultSet(rs);
            closeStatement(preparedStatement);

            preparedStatement = connection.prepareStatement(DELETE_CREW_TEAM);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            closeStatement(preparedStatement);
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
        }
        return idList;
    }

    @Override
    public List<Integer> getAllCrewTeamID(int id, Connection connection) throws SQLException {
        List<Integer> idList = new ArrayList<Integer>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_CREW_TEAM_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int employeeID = rs.getInt("employee_id");
                idList.add(employeeID);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
        }
        return idList;
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
