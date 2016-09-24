package ua.nure.borisov.summaryTask4.airline.customServlet.command.adminEmployeeCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.dto.EmployeeDTO;
import ua.nure.borisov.summaryTask4.airline.service.api.EmployeeService;
import ua.nure.borisov.summaryTask4.airline.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateEmployeeCommand implements Command {
    public boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        String employeeSpeciality = request.getParameter("speciality");
        String employeeName = request.getParameter("employeeName");
        String employeeOrdinalNumber = request.getParameter("ordinalNumber");
        boolean check = isNumber(employeeOrdinalNumber);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setSpecialty(employeeSpeciality);
        employeeDTO.setName(employeeName);
        if (check){
            employeeDTO.setOrdinalNumber(Integer.parseInt(employeeOrdinalNumber));
        }
        employeeService.addEmployee(employeeDTO);
        return "/AdminPage/Employee?result=Success";
    }
}
