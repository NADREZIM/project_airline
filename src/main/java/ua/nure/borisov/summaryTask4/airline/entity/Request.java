package ua.nure.borisov.summaryTask4.airline.entity;

import java.util.Date;

/**
 * Created by User on 20.08.2016.
 */
public class Request {
    private int requestID;
    private int flightID;
    private String requestBody;
    private boolean requestStatus;
    private Date requestSendDate;

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public boolean getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Date getRequestSendDate() {
        return requestSendDate;
    }

    public void setRequestSendDate(Date requestSendDate) {
        this.requestSendDate = requestSendDate;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestID=" + requestID +
                ", flightID=" + flightID +
                ", requestBody='" + requestBody + '\'' +
                ", requestStatus=" + requestStatus +
                ", requestSendDate=" + requestSendDate +
                '}';
    }
}
