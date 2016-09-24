package ua.nure.borisov.summaryTask4.airline.customServlet.command.dispCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.customServlet.customService.EmployeeManagementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoadAvailableEmployees implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> allAvailableEmployee = EmployeeManagementService.getAllAvailableEmployees("");
        request.setAttribute("allAvailableEmployee",allAvailableEmployee);
        return null;
    }
}
