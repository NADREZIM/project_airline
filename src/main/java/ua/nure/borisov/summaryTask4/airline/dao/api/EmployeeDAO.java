package ua.nure.borisov.summaryTask4.airline.dao.api;

import ua.nure.borisov.summaryTask4.airline.entity.Crew;
import ua.nure.borisov.summaryTask4.airline.entity.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 09.08.2016.
 */
public interface EmployeeDAO {

    public int  addEmployee(Employee employee,Connection connection) throws SQLException;
    public boolean checkBeforeAddingEmployee(Employee employee, Connection connection) throws SQLException;
    public void deleteEmployee(int id) throws SQLException;
    public List<Employee> getAllEmployees() throws SQLException;
    public List<Employee> getAllPilots() throws SQLException;
    public List<Employee> getAllNavigators() throws SQLException;
    public List<Employee> getAllRadiomen() throws SQLException;
    public List<Employee> getAllStewardess() throws SQLException;
    public Employee updateEmployee(int id,Employee employee) throws SQLException;
    public void updateSelectedEmployeesStatusToFree(List<Integer> crewTeamID, Connection connection) throws SQLException;
    public void updateSelectedEmployeesStatusToBlock(List<Employee> crewTeam, Connection connection) throws SQLException;
    public void linkCrewNumberEmployees(List<Employee> crewTeam, int autoID, Connection connection) throws SQLException;
    public List<Employee> getCrewTeamEmployeesByID(List<Integer>crewTeamMembersID, Connection connection) throws SQLException;
    public void updateCrewTeamTable(int id,Crew crew, Connection connection) throws SQLException;
    public List<Employee> getEmployeeByName(List<String>names,Connection connection)throws SQLException;
    public void updateCrewTeamTable1(List<Integer> insideEmployeeID, List<Employee> outsideEmployee, Connection connection) throws SQLException;



}
