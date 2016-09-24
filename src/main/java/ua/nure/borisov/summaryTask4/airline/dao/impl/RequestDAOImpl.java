package ua.nure.borisov.summaryTask4.airline.dao.impl;

import ua.nure.borisov.summaryTask4.airline.connection.ConnectionDB;
import ua.nure.borisov.summaryTask4.airline.dao.api.RequestDAO;
import ua.nure.borisov.summaryTask4.airline.entity.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 20.08.2016.
 */
public class RequestDAOImpl implements RequestDAO {
    private static final Logger LOGGER = Logger.getLogger(RequestDAOImpl.class.getName());
    private static final String CREATE_REQUEST = "insert into request(flight_id,body,date,status)values (?,?,?,?)";
    private static final String UPDATE_REQUEST_STATUS = "update request set status=1 where request_id=?";
    private static final String GET_ALL_REQUESTS = "select*from request where status=0";
    private static final String GET_REQUEST_BY_ID = "select*from request where request_id=?";
    private static final String GET_REQUEST_BY_fLIGHT_ID = "select*from request where flight_id =?";

    @Override
    public Request getRequestByID(int id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Request request = new Request();
        try {
            preparedStatement = connection.prepareStatement(GET_REQUEST_BY_ID);
            preparedStatement.setInt(1,id);
           resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                request.setRequestID(resultSet.getInt("request_id"));
                request.setFlightID(resultSet.getInt("flight_id"));
                request.setRequestBody(resultSet.getString("body"));
                request.setRequestSendDate(resultSet.getDate("date"));
                int digitStatus = resultSet.getInt("status");
                boolean status = false;
                if (digitStatus>0){
                    status = true;
                }
                request.setRequestStatus(status);
            }
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
        }
        return request;
    }
    @Override
    public Request getRequestByFlightID(int id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Request request = new Request();
        try {
            preparedStatement = connection.prepareStatement(GET_REQUEST_BY_fLIGHT_ID);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                request.setRequestID(resultSet.getInt("request_id"));
                request.setFlightID(resultSet.getInt("flight_id"));
                request.setRequestBody(resultSet.getString("body"));
                request.setRequestSendDate(resultSet.getDate("date"));
                int digitStatus = resultSet.getInt("status");
                boolean status = false;
                if (digitStatus>0){
                    status = true;
                }
                request.setRequestStatus(status);
            }
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
        }
        return request;
    }

    @Override
    public List<Request> getAllRequests() throws SQLException {
        java.sql.Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        List<Request> requestList = new ArrayList<Request>();
        try {
            connection = ConnectionDB.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(GET_ALL_REQUESTS);
            while (rs.next()) {
                Request request = new Request();
                request.setRequestID(rs.getInt("request_id"));
                request.setFlightID(rs.getInt("flight_id"));
                request.setRequestBody(rs.getString("body"));
                request.setRequestSendDate(rs.getDate("date"));
                boolean status = false;
                int digitStatus = rs.getInt("status");
                if (digitStatus > 0) {
                    status = true;
                }
                request.setRequestStatus(status);
                requestList.add(request);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
        return requestList;
    }

    @Override
    public void updateRequestStatus(int requestID) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_REQUEST_STATUS);
            preparedStatement.setInt(1, requestID);
            preparedStatement.execute();
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }

    }


    @Override
    public void createRequest(Request request) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(CREATE_REQUEST);
            preparedStatement.setInt(1, request.getFlightID());
            preparedStatement.setString(2, request.getRequestBody());
            preparedStatement.setDate(3, new java.sql.Date(request.getRequestSendDate().getTime()));
            preparedStatement.setInt(4, 0);
            preparedStatement.execute();
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }

    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Connection is not closed", e);
            }
        }
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
                LOGGER.log(Level.WARNING, "ResultSet is not closed", e);
            }
    }
}
