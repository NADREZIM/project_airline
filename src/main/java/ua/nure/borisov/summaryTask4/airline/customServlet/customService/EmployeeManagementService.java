package ua.nure.borisov.summaryTask4.airline.customServlet.customService;

import ua.nure.borisov.summaryTask4.airline.dto.CrewDTO;
import ua.nure.borisov.summaryTask4.airline.dto.EmployeeDTO;
import ua.nure.borisov.summaryTask4.airline.service.api.CrewService;
import ua.nure.borisov.summaryTask4.airline.service.api.EmployeeService;
import ua.nure.borisov.summaryTask4.airline.service.impl.CrewServiceImpl;
import ua.nure.borisov.summaryTask4.airline.service.impl.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeManagementService {

    public static Map<String, Object> getAllAvailableEmployees(String sCrewID) {
        List<String> allPilots = new ArrayList<String>();
        List<String> allStewardess = new ArrayList<String>();
        List<String> allNavigators = new ArrayList<String>();
        List<String> allRadioman = new ArrayList<String>();
        Map<String, Object> resultMap = new HashMap<String,Object>();


        EmployeeService employeeService = new EmployeeServiceImpl();
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        if (sCrewID != null) {
            if (!sCrewID.equals("")) {

                int crewID = Integer.parseInt(sCrewID);
                CrewService crewService = new CrewServiceImpl();


                CrewDTO crewDTO = crewService.getCrewTeamByIDWithOutConnection(crewID);
                List<EmployeeDTO> employeeDTOList = crewDTO.getCrewTeam();
                for (EmployeeDTO employeeDTO : employeeDTOList) {
                    if (employeeDTO.getSpecialty().equals("pilot")) {
                        allPilots.add(employeeDTO.getName());
                    }
                    if (employeeDTO.getSpecialty().equals("stewardess")) {
                        allStewardess.add(employeeDTO.getName());
                    }
                    if (employeeDTO.getSpecialty().equals("navigator")) {
                        allNavigators.add(employeeDTO.getName());
                    }
                    if (employeeDTO.getSpecialty().equals("radioman")) {
                        allRadioman.add(employeeDTO.getName());
                    }
                }
            }
        }

        for (EmployeeDTO employeeDTO : allEmployees) {
            if (employeeDTO.getSpecialty().equals("pilot")) {
                if (!employeeDTO.getStatus()) {
                    allPilots.add(employeeDTO.getName());
                }
            }
            if (employeeDTO.getSpecialty().equals("stewardess")) {
                if (!employeeDTO.getStatus()) {
                    allStewardess.add(employeeDTO.getName());
                }
            }
            if (employeeDTO.getSpecialty().equals("navigator")) {
                if (!employeeDTO.getStatus()) {
                    allNavigators.add(employeeDTO.getName());
                }
            }
            if (employeeDTO.getSpecialty().equals("radioman")) {
                if (!employeeDTO.getStatus()) {
                    allRadioman.add(employeeDTO.getName());
                }
            }
        }
        resultMap.put("allPilots", allPilots);
        resultMap.put("allStewardess", allStewardess);
        resultMap.put("allNavigators", allNavigators);
        resultMap.put("allRadioman", allRadioman);
        return resultMap;
    }
}
