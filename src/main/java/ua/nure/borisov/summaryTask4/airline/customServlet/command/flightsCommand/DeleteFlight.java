package ua.nure.borisov.summaryTask4.airline.customServlet.command.flightsCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.service.api.FlightService;
import ua.nure.borisov.summaryTask4.airline.service.impl.FlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteFlight implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FlightService flightService = new FlightServiceImpl();
        String stringID = request.getParameter("flightID");

        int flightNumber = Integer.parseInt(stringID);

        flightService.deleteFlight(flightNumber);
        return "/AdminPage/Flights?result=Success";
    }
}
