package ua.nure.borisov.summaryTask4.airline.customServlet.command.flightsCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.customServlet.customService.EmployeeManagementService;
import ua.nure.borisov.summaryTask4.airline.service.api.FlightService;
import ua.nure.borisov.summaryTask4.airline.service.impl.FlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class LoadAvailableEmployeeWithPreviousEmployee implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flightID = request.getParameter("flightID");
        int digitFlightID = Integer.parseInt(flightID);
        FlightService flightService = new FlightServiceImpl();
        int crewID = 0;
        try {
            crewID = flightService.getCrewTeamIdByFlightNumber(digitFlightID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String stringCrewID = String.valueOf(crewID);
        Map<String, Object> allAvailableEmployee = EmployeeManagementService.getAllAvailableEmployees(stringCrewID);
        request.setAttribute("allAvailableEmployee",allAvailableEmployee);
        request.setAttribute("flightID",flightID);
        request.setAttribute("crewID",crewID);
        return null;
    }
}
