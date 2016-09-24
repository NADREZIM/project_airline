package ua.nure.borisov.summaryTask4.airline.customServlet.command.adminEmployeeCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DrawForm implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeID = request.getParameter("employeeID");
        request.setAttribute("employeeID",employeeID);
        request.getRequestDispatcher("/CustomView/UpdateEmployeePage.jsp").forward(request,response);
        return "draw";
    }
}
