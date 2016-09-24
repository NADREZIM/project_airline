package ua.nure.borisov.summaryTask4.airline.customServlet.command.flightsCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.dto.FlightDTO;
import ua.nure.borisov.summaryTask4.airline.service.api.FlightService;
import ua.nure.borisov.summaryTask4.airline.service.impl.FlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FlightsByMainParameters implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FlightService flightService = new FlightServiceImpl();
        String departure = request.getParameter("mainDeparture");
        String destination = request.getParameter("mainDestination");
        String departureDate = request.getParameter("mainDepartureDate");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date formatDate = null;
        try {
            formatDate = format.parse(departureDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<FlightDTO> result = new ArrayList<FlightDTO>();
        List<FlightDTO> flightsByMainParameters = flightService.getAllFlights();
        for (FlightDTO item: flightsByMainParameters){
            if (item.getDepartureDate().equals(formatDate)){
                if (item.getPointOfDeparture().equals(departure)){
                    if (item.getPointOfDestination().equals(destination)){
                        result.add(item);
                    }
                }
            }
        }
        request.setAttribute("allFlights",result);
        return null;
    }
}
