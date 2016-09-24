package ua.nure.borisov.summaryTask4.airline.customServlet.command.adminEmployeeCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.service.api.EmployeeService;
import ua.nure.borisov.summaryTask4.airline.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        String stringEmployeeID = request.getParameter("employeeID");
        int digitEmployeeID = Integer.parseInt(stringEmployeeID);
        employeeService.deleteEmployee(digitEmployeeID);
        return "/AdminPage/Employee?result=Success";
    }
}
