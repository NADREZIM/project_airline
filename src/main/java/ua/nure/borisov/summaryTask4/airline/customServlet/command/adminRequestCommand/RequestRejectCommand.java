package ua.nure.borisov.summaryTask4.airline.customServlet.command.adminRequestCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.service.api.FlightService;
import ua.nure.borisov.summaryTask4.airline.service.api.RequestService;
import ua.nure.borisov.summaryTask4.airline.service.impl.FlightServiceImpl;
import ua.nure.borisov.summaryTask4.airline.service.impl.RequestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestRejectCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestID = request.getParameter("requestID");

        String flightID = request.getParameter("flightID");
        int digitFlightID = Integer.parseInt(flightID);
        int digitRequestID = Integer.parseInt(requestID);

        RequestService requestService = new RequestServiceImpl();
        FlightService flightService = new FlightServiceImpl();


        flightService.deleteFlight(digitFlightID);
        requestService.updateRequestStatus(digitRequestID);

        return "/AdminPage/Requests?result=Success";
    }
}
