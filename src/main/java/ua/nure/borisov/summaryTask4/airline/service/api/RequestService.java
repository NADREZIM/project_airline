package ua.nure.borisov.summaryTask4.airline.service.api;

import ua.nure.borisov.summaryTask4.airline.dto.FlightDTO;
import ua.nure.borisov.summaryTask4.airline.dto.RequestDTO;

import java.util.List;

/**
 * Created by User on 21.08.2016.
 */
public interface RequestService  {
    public void createRequest(RequestDTO requestDTO);
    public void updateRequestStatus(int requestID);
    public List<RequestDTO> getAllRequests();
    public FlightDTO getFlightByRequestID(int id);
    public void updateRequestStatusByFlightID(int flightID);
}
