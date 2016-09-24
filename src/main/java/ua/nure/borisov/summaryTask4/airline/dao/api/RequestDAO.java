package ua.nure.borisov.summaryTask4.airline.dao.api;

import ua.nure.borisov.summaryTask4.airline.entity.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 20.08.2016.
 */
public interface RequestDAO {
    public void createRequest(Request request) throws SQLException;
    public void updateRequestStatus(int requestID) throws SQLException;
    public List<Request> getAllRequests() throws SQLException;
    public Request getRequestByID(int id,Connection connection) throws SQLException;
    public Request getRequestByFlightID(int id, Connection connection) throws SQLException;
}
