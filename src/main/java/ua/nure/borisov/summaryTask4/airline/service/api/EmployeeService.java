package ua.nure.borisov.summaryTask4.airline.service.api;

import ua.nure.borisov.summaryTask4.airline.dto.CrewDTO;
import ua.nure.borisov.summaryTask4.airline.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 11.08.2016.
 */
public interface EmployeeService {
    public void addEmployee(EmployeeDTO employeeDTO);
    public void deleteEmployee(int id);
    public List<EmployeeDTO> getAllEmployees();
    public List<EmployeeDTO> getAllPilots();
    public List<EmployeeDTO> getAllNavigators();
    public List<EmployeeDTO> getAllRadiomen();
    public List<EmployeeDTO> getAllStewardess();
    public EmployeeDTO updateEmployee(int id,EmployeeDTO employeeDTO);
    public void updateSelectedEmployeesStatusToFree(List<Integer> crewTeamID,Connection connection) throws SQLException;
    public void updateSelectedEmployeesStatusToBlock(List<EmployeeDTO> crewTeam, Connection connection) throws SQLException;
    public void linkCrewNumberEmployees(List<EmployeeDTO> crewTeam, int autoID,Connection connection) throws SQLException;
    public List<EmployeeDTO> getCrewTeamEmployeesByID(List<Integer>crewTeamMembersID, Connection connection) throws SQLException;
    public void updateCrewTeamTable(int id,CrewDTO crewDTO,Connection connection) throws SQLException;
    public List<EmployeeDTO> getEmployeeByName(List<String>names, Connection connection);
    public void updateCrewTeamTable1(List<Integer> insideEmployeeID, List<EmployeeDTO> outsideEmployee, Connection connection) throws SQLException;
    public List<EmployeeDTO> getEgorEgorov();

}
