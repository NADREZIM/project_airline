package ua.nure.borisov.summaryTask4.airline.service.impl;

import ua.nure.borisov.summaryTask4.airline.dao.api.EmployeeDAO;
import ua.nure.borisov.summaryTask4.airline.dao.impl.EmployeeDAOImpl;
import ua.nure.borisov.summaryTask4.airline.dto.CrewDTO;
import ua.nure.borisov.summaryTask4.airline.dto.EmployeeDTO;
import ua.nure.borisov.summaryTask4.airline.entity.Crew;
import ua.nure.borisov.summaryTask4.airline.entity.Employee;
import ua.nure.borisov.summaryTask4.airline.service.api.EmployeeService;
import ua.nure.borisov.summaryTask4.airline.transaction.Transaction;
import ua.nure.borisov.summaryTask4.airline.transformer.Transformer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 11.08.2016.
 */
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class.getName());

    @Override
    public List<EmployeeDTO> getEgorEgorov() {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
      List<Employee> employee = employeeDAO.getEgorEgorov();
        return Transformer.listEmployeeToEmployeeListDTO(employee);
    }

    @Override
    public void updateCrewTeamTable1(List<Integer> insideEmployeeID, List<EmployeeDTO> outsideEmployee, Connection connection) throws SQLException {
        List<Employee>employeeList = Transformer.listEmployeeDTOToEmployeeList(outsideEmployee);
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        employeeDAO.updateCrewTeamTable1(insideEmployeeID,employeeList,connection);
    }

    @Override
    public List<EmployeeDTO> getEmployeeByName(List<String> names,Connection connection) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> allEmployees = new ArrayList<Employee>();
        try {
            allEmployees = employeeDAO.getEmployeeByName(names, connection);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method getEmployeeByName: ", e);
        }
        return Transformer.listEmployeeToEmployeeListDTO(allEmployees);
    }

    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        Connection connection = Transaction.startTransaction();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee = Transformer.employeeDTOToEmployee(employeeDTO);

        try {
           // boolean checkAgainstDuplicates = employeeDAO.checkBeforeAddingEmployee(employee,connection);

           // if (!checkAgainstDuplicates){
                employeeDAO.addEmployee(employee,connection);
           // }
           // else {
             //   LOGGER.log(Level.SEVERE, "Exception in method addEmployee: ", e);
                // как лучше подать сообщение что чувак с таким именем уже есть?
           // }

            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method addEmployee: ", e);
        }
        finally {
            Transaction.endTransaction(connection);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        try {
            employeeDAO.deleteEmployee(id);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method deleteEmployee: ", e);
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> tempListEmployee = null;
        try {
            tempListEmployee = employeeDAO.getAllEmployees();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method getAllEmployees: ", e);
        }
        return Transformer.listEmployeeToEmployeeListDTO(tempListEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllPilots() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> tempListPilots = null;
        try {
            tempListPilots = employeeDAO.getAllPilots();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method getAllPilots: ", e);
        }
        return Transformer.listEmployeeToEmployeeListDTO(tempListPilots);
    }

    @Override
    public List<EmployeeDTO> getAllNavigators() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> tempListNavigators = null;
        try {
            tempListNavigators = employeeDAO.getAllNavigators();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method getAllNavigators: ", e);
        }
        return Transformer.listEmployeeToEmployeeListDTO(tempListNavigators);
    }

    @Override
    public List<EmployeeDTO> getAllRadiomen() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> tempListRadiomen = null;
        try {
            tempListRadiomen = employeeDAO.getAllRadiomen();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method getAllRadiomen: ", e);
        }
        return Transformer.listEmployeeToEmployeeListDTO(tempListRadiomen);
    }

    @Override
    public List<EmployeeDTO> getAllStewardess() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> tempListStewardess = null;
        try {
            tempListStewardess = employeeDAO.getAllStewardess();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method getAllStewardess: ", e);
        }
        return Transformer.listEmployeeToEmployeeListDTO(tempListStewardess);
    }

    @Override
    public void updateCrewTeamTable(int id,CrewDTO crewDTO,Connection connection) throws SQLException {
     EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Crew crew = Transformer.crewDTOToCrew(crewDTO);
        employeeDAO.updateCrewTeamTable(id,crew,connection);
    }

    @Override
    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee tempEmployee = Transformer.employeeDTOToEmployee(employeeDTO);
        Employee employee = null;
        try {
            employee = employeeDAO.updateEmployee(id, tempEmployee);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method updateEmployee: ", e);
        }
        return Transformer.employeeToEmployeeDTO(employee);

    }

    @Override
    public void updateSelectedEmployeesStatusToFree(List<Integer> crewTeamID, Connection connection) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        employeeDAO.updateSelectedEmployeesStatusToFree(crewTeamID, connection);
    }

    @Override
    public List<EmployeeDTO> getCrewTeamEmployeesByID(List<Integer> crewTeamMembersID, Connection connection) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> employeeList = employeeDAO.getCrewTeamEmployeesByID(crewTeamMembersID, connection);
        return Transformer.listEmployeeToEmployeeListDTO(employeeList);
    }

    @Override
    public void updateSelectedEmployeesStatusToBlock(List<EmployeeDTO> crewTeamDTO, Connection connection) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> crewTeam = new ArrayList<Employee>();
        for (EmployeeDTO employeeDTO : crewTeamDTO) {
            Employee employee = Transformer.employeeDTOToEmployee(employeeDTO);
            crewTeam.add(employee);
        }
        employeeDAO.updateSelectedEmployeesStatusToBlock(crewTeam, connection);
    }

    @Override
    public void linkCrewNumberEmployees(List<EmployeeDTO> crewTeamDTO, int autoID, Connection connection) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> crewTeam = new ArrayList<Employee>();
        for (EmployeeDTO employeeDTO : crewTeamDTO) {
            Employee employee = Transformer.employeeDTOToEmployee(employeeDTO);
            crewTeam.add(employee);
        }
        employeeDAO.linkCrewNumberEmployees(crewTeam, autoID, connection);
    }


}
