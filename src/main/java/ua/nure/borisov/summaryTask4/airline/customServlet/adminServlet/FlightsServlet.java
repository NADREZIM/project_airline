package ua.nure.borisov.summaryTask4.airline.customServlet.adminServlet;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.customServlet.command.flightsCommand.*;
import ua.nure.borisov.summaryTask4.airline.dto.FlightDTO;
import ua.nure.borisov.summaryTask4.airline.service.api.FlightService;
import ua.nure.borisov.summaryTask4.airline.service.impl.FlightServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AdminPage/Flights")
public class FlightsServlet extends HttpServlet {
    Map<String,Command> commandMap = new HashMap<String,Command>(){{
        put("sortByName", new FlightsSortByNameCommand());
        put("sortByDpt", new FlightsSortByDepartureCommand());
        put("sortByNameAndNumber", new FlightsSortByNameAndNumberCommand());
        put("sortByDestinationAndDeparture", new FlightsSortByDestinationAndDeparture());
        put("deleteFlight", new DeleteFlight());
        put("updateFlight", new UdpateFlight());
        put("flightsByDeparture",new FlightsByDepartureCommand());
        put("flightsByDestination",new FlightsByDestinationCommand());
        put("flightsByDepartureDate",new FlightsByDepartureDate());
        put("flightsByMainParameters", new FlightsByMainParameters());
    }};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if(command!=null && !command.isEmpty()){
            execute(request,response);
        } else {
            FlightService flightService = new FlightServiceImpl();
            List<FlightDTO> allFlights = flightService.getAllFlights();
            request.setAttribute("allFlights",allFlights);
            request.getRequestDispatcher("/CustomView/FlightsPage.jsp").forward(request,response);
        }
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String command = request.getParameter("command");
        String pathToRedirect = null;
        if(command != null && !command.isEmpty()){
            Command commandToExecute = commandMap.get(command);
            if(commandToExecute == null){
                response.sendRedirect("/error?error='command wasn't found");
            } else {
                pathToRedirect = commandToExecute.execute(request,response);
            }
        }
        if(pathToRedirect == null){
            request.getRequestDispatcher("/CustomView/FlightsPage.jsp").forward(request,response);
            return;
        }
        response.sendRedirect(pathToRedirect);
    }
}
