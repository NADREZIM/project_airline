package ua.nure.borisov.summaryTask4.airline.customServlet.command.adminEmployeeCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.dto.EmployeeDTO;
import ua.nure.borisov.summaryTask4.airline.service.api.EmployeeService;
import ua.nure.borisov.summaryTask4.airline.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateEmployeeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeSpeciality = request.getParameter("speciality");
        System.out.println(employeeSpeciality);
        String employeeName = request.getParameter("employeeName");
        System.out.println(employeeName);
        String employeeOrdinalNumber = request.getParameter("ordinalNumber");
        int ordinalNumber = Integer.parseInt(employeeOrdinalNumber);
        System.out.println(employeeOrdinalNumber);
        String employeeID = request.getParameter("employeeID");
        int digitEmployeeID = Integer.parseInt(employeeID);
        System.out.println(employeeID);
        String employeeStatus = request.getParameter("status");
        System.out.println(employeeStatus);
        boolean status = false;
        if (employeeStatus.equals("ready")){
            status = true;
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setSpecialty(employeeSpeciality);
        employeeDTO.setName(employeeName);
        employeeDTO.setOrdinalNumber(ordinalNumber);
        employeeDTO.setStatus(status);

        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.updateEmployee(digitEmployeeID, employeeDTO);

       return "/AdminPage/Employee?result=Success";
    }
}
