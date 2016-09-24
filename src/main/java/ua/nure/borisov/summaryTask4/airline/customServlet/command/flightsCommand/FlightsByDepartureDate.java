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

public class FlightsByDepartureDate implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FlightService flightService = new FlightServiceImpl();
        final String stringDate = request.getParameter("departureDate");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date formatDate = null;
        try {
            formatDate = format.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<FlightDTO> result = new ArrayList<FlightDTO>();
        List<FlightDTO> flightByDepartureDate = flightService.getAllFlights();
        for (FlightDTO item : flightByDepartureDate) {
            if (item.getDepartureDate().equals(formatDate)) {
                result.add(item);
            }
        }
        request.setAttribute("allFlights", result);
        return null;
    }
}
