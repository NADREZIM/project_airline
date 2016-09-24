package ua.nure.borisov.summaryTask4.airline.customServlet.command.adminCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlightsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/AdminPage/Flights";
    }
}
