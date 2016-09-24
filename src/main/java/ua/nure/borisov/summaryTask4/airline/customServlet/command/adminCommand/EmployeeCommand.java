package ua.nure.borisov.summaryTask4.airline.customServlet.command.adminCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeCommand implements ua.nure.borisov.summaryTask4.airline.customServlet.command.Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/AdminPage/Employee";
    }
}
