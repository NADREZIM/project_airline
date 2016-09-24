package ua.nure.borisov.summaryTask4.airline.service.impl;

import ua.nure.borisov.summaryTask4.airline.dao.api.RequestDAO;
import ua.nure.borisov.summaryTask4.airline.dao.impl.RequestDAOImpl;
import ua.nure.borisov.summaryTask4.airline.dto.FlightDTO;
import ua.nure.borisov.summaryTask4.airline.dto.RequestDTO;
import ua.nure.borisov.summaryTask4.airline.entity.Request;
import ua.nure.borisov.summaryTask4.airline.service.api.FlightService;
import ua.nure.borisov.summaryTask4.airline.service.api.RequestService;
import ua.nure.borisov.summaryTask4.airline.transaction.Transaction;
import ua.nure.borisov.summaryTask4.airline.transformer.Transformer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 21.08.2016.
 */
public class RequestServiceImpl implements RequestService {
    private static final Logger LOGGER = Logger.getLogger(RequestServiceImpl.class.getName());

    @Override
    public void updateRequestStatusByFlightID(int flightID) {
        Connection connection = Transaction.startTransaction();
        RequestDAO requestDAO = new RequestDAOImpl();
        try {
            Request request = requestDAO.getRequestByFlightID(flightID,connection);
            requestDAO.updateRequestStatus(request.getRequestID());
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method updateRequestStatusByFlightID : ", e);
        } finally {
            Transaction.endTransaction(connection);
        }
    }

    @Override
    public FlightDTO getFlightByRequestID(int id) {
        Connection connection = Transaction.startTransaction();
        RequestDAO requestDAO = new RequestDAOImpl();
        FlightService flightService = new FlightServiceImpl();
        FlightDTO flightDTO = new FlightDTO();
        try {
            Request request = requestDAO.getRequestByID(id,connection);
            flightDTO = flightService.getFlightByNumberWithConnection(request.getFlightID(),connection);
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method getFlightByRequestID : ", e);
        } finally {
            Transaction.endTransaction(connection);
        }
        return flightDTO;
    }

    @Override
    public void createRequest(RequestDTO requestDTO) {
        RequestDAO requestDAO = new RequestDAOImpl();
        Request request = Transformer.requestDTOToRequest(requestDTO);
        try {
            requestDAO.createRequest(request);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method createRequest : ", e);
        }
    }



    @Override
    public List<RequestDTO> getAllRequests() {
        RequestDAO requestDAO = new RequestDAOImpl();
        List<RequestDTO>requestDTOList = new ArrayList<RequestDTO>();
        try {
            List<Request>requestList = requestDAO.getAllRequests();
            if (requestList != null) {
                for (Request request : requestList) {
                    RequestDTO requestDTO;
                    requestDTO = Transformer.requestToRequestDTO(request);
                    requestDTOList.add(requestDTO);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method getAllRequests : ", e);
        }
        return requestDTOList;
    }

    @Override
    public void updateRequestStatus(int requestID) {
        RequestDAO requestDAO = new RequestDAOImpl();
        try {
            requestDAO.updateRequestStatus(requestID);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method updateRequestStatus : ", e);
        }
    }
}
