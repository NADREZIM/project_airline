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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UdpateFlight implements Command {
    private static final Logger LOGGER = Logger.getLogger(UdpateFlight.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringFlightID = request.getParameter("flightID");
        int flightID = Integer.parseInt(stringFlightID);
        String stringCrewID = request.getParameter("crewID");
        int crewID = Integer.parseInt(stringCrewID);

        String flightName = request.getParameter("flightName");
        String departure = request.getParameter("departureCity");
        String destination = request.getParameter("destinationCity");
        String departureDateString = request.getParameter("departureDate");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Date departureDate = null;
        try {
            departureDate = format.parse(departureDateString);
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "ERROR OF STRING_TO_DATE TRANSFORMING ", e);
        }
        String stringStatus = request.getParameter("status");
        boolean status = false;
        if (stringStatus.equals("ready")){
            status = true;
        }
        String firstPilot = request.getParameter("firstPilot");
        String secondPilot = request.getParameter("secondPilot");
        String firstStewardess = request.getParameter("firstStewardess");
        String secondStewardess = request.getParameter("secondStewardess");
        String navigator = request.getParameter("navigator");
        String radiomen = request.getParameter("radiomen");

        List<String> allEmployeesNames = new ArrayList<String>();
        allEmployeesNames.add(firstPilot);
        allEmployeesNames.add(secondPilot);
        allEmployeesNames.add(firstStewardess);
        allEmployeesNames.add(secondStewardess);
        allEmployeesNames.add(navigator);
        allEmployeesNames.add(radiomen);


        FlightService flightService = new FlightServiceImpl();


        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setDepartureDate(departureDate);
        flightDTO.setFlightName(flightName);
        flightDTO.setPointOfDestination(destination);
        flightDTO.setPointOfDeparture(departure);
        flightDTO.setFlightStatus(status);


        flightService.updateFlight(flightID,crewID,flightDTO,allEmployeesNames);
        return "/AdminPage/Flights?result=Success";
    }
}
