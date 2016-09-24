package ua.nure.borisov.summaryTask4.airline.customServlet.command.flightsCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.dto.FlightDTO;
import ua.nure.borisov.summaryTask4.airline.service.api.FlightService;
import ua.nure.borisov.summaryTask4.airline.service.impl.FlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightsByDepartureCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FlightService flightService = new FlightServiceImpl();
        final String departure = request.getParameter("departure");
        List<FlightDTO> result = new ArrayList<FlightDTO>();
        List<FlightDTO>flightByDeparture = flightService.getAllFlights();
        for (FlightDTO item : flightByDeparture){
            if(item.getPointOfDeparture().equals(departure)){
                result.add(item);
            }
        }

        request.setAttribute("allFlights",result);
        return null;

    }
}
