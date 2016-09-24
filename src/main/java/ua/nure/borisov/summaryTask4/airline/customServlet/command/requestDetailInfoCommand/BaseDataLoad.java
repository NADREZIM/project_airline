package ua.nure.borisov.summaryTask4.airline.customServlet.command.requestDetailInfoCommand;

import ua.nure.borisov.summaryTask4.airline.customServlet.command.Command;
import ua.nure.borisov.summaryTask4.airline.customServlet.customService.EmployeeManagementService;
import ua.nure.borisov.summaryTask4.airline.dto.CrewDTO;
import ua.nure.borisov.summaryTask4.airline.dto.EmployeeDTO;
import ua.nure.borisov.summaryTask4.airline.dto.FlightDTO;
import ua.nure.borisov.summaryTask4.airline.service.api.RequestService;
import ua.nure.borisov.summaryTask4.airline.service.impl.RequestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDataLoad implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringRequestID = request.getParameter("requestID");
        int requestID = Integer.parseInt(stringRequestID);
        RequestService requestService = new RequestServiceImpl();
        FlightDTO flightDTO = requestService.getFlightByRequestID(requestID);
        Map<String, List<EmployeeDTO>> specialityToEmployee = new HashMap<String, List<EmployeeDTO>>();
        CrewDTO crewDTO = flightDTO.getCrewTeam();

        List<EmployeeDTO> pilots = new ArrayList<EmployeeDTO>();
        List<EmployeeDTO> stewardess = new ArrayList<EmployeeDTO>();
        List<EmployeeDTO> radiomen = new ArrayList<EmployeeDTO>();
        List<EmployeeDTO> navigator = new ArrayList<EmployeeDTO>();

        List<EmployeeDTO> employeeDTOList = crewDTO.getCrewTeam();
        for (EmployeeDTO employeeDTO : employeeDTOList) {
            if (employeeDTO.getSpecialty().equals("pilot")) {
                pilots.add(employeeDTO);
            }
            if (employeeDTO.getSpecialty().equals("stewardess")) {
                stewardess.add(employeeDTO);
            }
            if (employeeDTO.getSpecialty().equals("navigator")) {
                navigator.add(employeeDTO);
            }
            if (employeeDTO.getSpecialty().equals("radioman")) {
                radiomen.add(employeeDTO);
            }
        }
        while (pilots.size() != 2){
            pilots.add(null);
        }
        while (stewardess.size() != 2){
            stewardess.add(null);
        }
        while (navigator.size() != 1){
            navigator.add(null);
        }
        while (radiomen.size() != 1){
            radiomen.add(null);
        }
        specialityToEmployee.put("pilot", pilots);
        specialityToEmployee.put("stewardess",stewardess);
        specialityToEmployee.put("navigator",navigator);
        specialityToEmployee.put("radiomen",radiomen);

        Map<String, Object> allAvailableEmployee = EmployeeManagementService.getAllAvailableEmployees("");

        request.setAttribute("allAvailableEmployee",allAvailableEmployee);
        request.setAttribute("flight",flightDTO);
        request.setAttribute("specialityToEmployee",specialityToEmployee);

        return null;
    }
}
