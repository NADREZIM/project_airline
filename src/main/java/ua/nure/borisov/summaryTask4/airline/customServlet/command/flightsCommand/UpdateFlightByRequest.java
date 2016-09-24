package ua.nure.borisov.summaryTask4.airline.customServlet.command.flightsCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.dto.FlightDTO;
import ua.nure.borisov.summaryTask4.airline.service.api.FlightService;
import ua.nure.borisov.summaryTask4.airline.service.api.RequestService;
import ua.nure.borisov.summaryTask4.airline.service.impl.FlightServiceImpl;
import ua.nure.borisov.summaryTask4.airline.service.impl.RequestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateFlightByRequest implements Command {
    private static final Logger LOGGER = Logger.getLogger(UdpateFlight.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flightNumber = request.getParameter("flightNumber");
        String flightName = request.getParameter("flightName");
        String flightDeparture = request.getParameter("flightDeparture");
        String flightDestination = request.getParameter("flightDestination");
        String flightDateDeparture = request.getParameter("flightDateOfDeparture");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date departureDate = null;
        try {
            departureDate = format.parse(flightDateDeparture);
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "ERROR OF STRING_TO_DATE TRANSFORMING ", e);
        }

        String flightCrewTeamID = request.getParameter("flightCrewTeamID");
        int digitFlightCrewTeamID = Integer.parseInt(flightCrewTeamID);

        String[] newPilots = request.getParameterValues("firstPilot");
        String[] oldPilots = request.getParameterValues("pilotName");
        String[] newStewardess = request.getParameterValues("firstStewardess");
        String[] oldStewardess = request.getParameterValues("stewardessName");
        String newNavigator = request.getParameter("navigator");
        String oldNavigator = request.getParameter("navigatorName");
        String newRadiomen = request.getParameter("radiomen");
        String oldRadiomen = request.getParameter("radiomenName");

        List<String> allCrewTeamNames = new ArrayList<String>();


        if (newPilots!=null){
            Collections.addAll(allCrewTeamNames, newPilots);
        }
        if (oldPilots!=null){
            Collections.addAll(allCrewTeamNames, oldPilots);
        }
        if (newStewardess!=null){
            Collections.addAll(allCrewTeamNames,newStewardess);
        }
        if (oldStewardess!=null){
            Collections.addAll(allCrewTeamNames, oldStewardess);
        }
        if (newNavigator==null){
            allCrewTeamNames.add(oldNavigator);
        } else {
            allCrewTeamNames.add(newNavigator);
        }
        if (newRadiomen==null){
            allCrewTeamNames.add(oldRadiomen);
        } else {
            allCrewTeamNames.add(newRadiomen);
        }

        String stringStatus = request.getParameter("flightStatus");
        boolean status = false;
        if (stringStatus.equals("ready")){
            status = true;
            RequestService requestService = new RequestServiceImpl();
            requestService.updateRequestStatusByFlightID(Integer.parseInt(flightNumber));

        }

        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightNumber(Integer.parseInt(flightNumber));
        flightDTO.setFlightName(flightName);
        flightDTO.setPointOfDeparture(flightDeparture);
        flightDTO.setPointOfDestination(flightDestination);
        flightDTO.setDepartureDate(departureDate);
        flightDTO.setFlightStatus(status);

        FlightService flightService = new FlightServiceImpl();
        flightService.updateFlightByRequest(flightDTO.getFlightNumber(), digitFlightCrewTeamID, flightDTO, allCrewTeamNames);

        return "/AdminPage/Requests?result=Success";
    }
}
