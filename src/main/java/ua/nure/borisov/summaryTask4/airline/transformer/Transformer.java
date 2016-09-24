package ua.nure.borisov.summaryTask4.airline.transformer;

import ua.nure.borisov.summaryTask4.airline.dto.CrewDTO;
import ua.nure.borisov.summaryTask4.airline.dto.EmployeeDTO;
import ua.nure.borisov.summaryTask4.airline.dto.FlightDTO;
import ua.nure.borisov.summaryTask4.airline.dto.RequestDTO;
import ua.nure.borisov.summaryTask4.airline.entity.Crew;
import ua.nure.borisov.summaryTask4.airline.entity.Employee;
import ua.nure.borisov.summaryTask4.airline.entity.Flight;
import ua.nure.borisov.summaryTask4.airline.entity.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07.08.2016.
 */
public class Transformer {

    public static RequestDTO requestToRequestDTO(Request request){
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRequestID(request.getRequestID());
        requestDTO.setFlightID(request.getFlightID());
        requestDTO.setRequestBody(request.getRequestBody());
        requestDTO.setRequestSendDate(request.getRequestSendDate());
        requestDTO.setRequestStatus(request.getRequestStatus());
        return requestDTO;
    }

    public static Request requestDTOToRequest(RequestDTO requestDTO){
        Request request = new Request();
        request.setRequestID(requestDTO.getRequestID());
        request.setFlightID(requestDTO.getFlightID());
        request.setRequestBody(requestDTO.getRequestBody());
        request.setRequestSendDate(requestDTO.getRequestSendDate());
        request.setRequestStatus(requestDTO.getRequestStatus());
        return request;
    }

    public static FlightDTO flightToFlightDTO(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightNumber(flight.getFlightNumber());
        flightDTO.setFlightName(flight.getFlightName());
        flightDTO.setDepartureDate(flight.getDepartureDate());
        flightDTO.setPointOfDeparture(flight.getPointOfDeparture());
        flightDTO.setPointOfDestination(flight.getPointOfDestination());
        flightDTO.setFlightStatus(flight.getFlightStatus());
        if(flight.getCrewTeam() != null){
        Crew crew = flight.getCrewTeam();
        CrewDTO crewDTO = Transformer.crewToCrewDTO(crew);
        flightDTO.setCrewTeam(crewDTO);
        }
        return flightDTO;
    }

    public static Flight flightDTOToFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setFlightName(flightDTO.getFlightName());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setPointOfDeparture(flightDTO.getPointOfDeparture());
        flight.setPointOfDestination(flightDTO.getPointOfDestination());
        flight.setFlightStatus(flightDTO.getFlightStatus());
        if (flightDTO.getCrewTeam() != null) {
            CrewDTO crewDTO = flightDTO.getCrewTeam();
            Crew crew = crewDTOToCrew(crewDTO);
            flight.setCrewTeam(crew);
        }
        return flight;
    }

    public static CrewDTO crewToCrewDTO(Crew crew) {
        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setCrewTeamID(crew.getCrewTeamID());
        List<Employee> employeeList = crew.getCrewTeam();
        List<EmployeeDTO> employeeDTOList = listEmployeeToEmployeeListDTO(employeeList);
        crewDTO.setCrewTeam(employeeDTOList);
        return crewDTO;
    }

    public static Crew crewDTOToCrew(CrewDTO crewDTO) {
        Crew crew = new Crew();
        crew.setCrewTeamID(crewDTO.getCrewTeamID());
        List<EmployeeDTO> employeeDTOList = crewDTO.getCrewTeam();
        List<Employee> employeeList = listEmployeeDTOToEmployeeList(employeeDTOList);
        crew.setCrewTeam(employeeList);
        return crew;

    }

    public static EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeID(employee.getEmployeeID());
        employeeDTO.setSpecialty(employee.getSpecialty());
        employeeDTO.setName(employee.getName());
        employeeDTO.setOrdinalNumber(employee.getOrdinalNumber());
        employeeDTO.setStatus(employee.getStatus());
        return employeeDTO;
    }

    public static Employee employeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeID(employeeDTO.getEmployeeID());
        employee.setSpecialty(employeeDTO.getSpecialty());
        employee.setName(employeeDTO.getName());
        employee.setOrdinalNumber(employeeDTO.getOrdinalNumber());
        employee.setStatus(employeeDTO.getStatus());
        return employee;
    }

    public static List<Employee> listEmployeeDTOToEmployeeList(List<EmployeeDTO> crewTeamDTO) {
        List<Employee> crewTeam = new ArrayList<Employee>();
        for (EmployeeDTO employeeDTO : crewTeamDTO) {
            Employee employee = Transformer.employeeDTOToEmployee(employeeDTO);
            crewTeam.add(employee);
        }
        return crewTeam;
    }

    public static List<EmployeeDTO> listEmployeeToEmployeeListDTO(List<Employee> crewTeam) {
        List<EmployeeDTO> crewTeamDTO = new ArrayList<EmployeeDTO>();
        for (Employee employee : crewTeam) {
            EmployeeDTO employeeDTO = Transformer.employeeToEmployeeDTO(employee);
            crewTeamDTO.add(employeeDTO);
        }
        return crewTeamDTO;
    }
}