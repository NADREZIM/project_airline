package ua.nure.borisov.summaryTask4.airline.service.impl;

import ua.nure.borisov.summaryTask4.airline.dao.api.CrewDAO;
import ua.nure.borisov.summaryTask4.airline.dao.impl.CrewDAOImpl;
import ua.nure.borisov.summaryTask4.airline.dto.CrewDTO;
import ua.nure.borisov.summaryTask4.airline.dto.EmployeeDTO;
import ua.nure.borisov.summaryTask4.airline.entity.Employee;
import ua.nure.borisov.summaryTask4.airline.service.api.CrewService;
import ua.nure.borisov.summaryTask4.airline.service.api.EmployeeService;
import ua.nure.borisov.summaryTask4.airline.transaction.Transaction;
import ua.nure.borisov.summaryTask4.airline.transformer.Transformer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 07.08.2016.
 */
public class CrewServiceImpl implements CrewService {
    private static final Logger LOGGER = Logger.getLogger(CrewServiceImpl.class.getName());

    @Override
    public List<CrewDTO> getAllAvailableCrewTeams() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Connection connection = Transaction.startTransaction();
        List<CrewDTO> allCrewDTO = new ArrayList<CrewDTO>();
        try {
            HashMap<Integer, List<Integer>> crewAndEmployeesID = new CrewDAOImpl().selectAllFromCrewTeam(connection);
            for (Map.Entry entry : crewAndEmployeesID.entrySet()) {
                CrewDTO crewDTO = new CrewDTO();
                Integer crewID = (Integer) entry.getKey();
                List<Integer> employeeID = (List<Integer>) entry.getValue();
                List<EmployeeDTO> employeeDTOList = employeeService.getCrewTeamEmployeesByID(employeeID, connection);
                crewDTO.setCrewTeamID(crewID);
                crewDTO.setCrewTeam(employeeDTOList);
                allCrewDTO.add(crewDTO);
            }
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }
        return allCrewDTO;
    }

    @Override
    public CrewDTO updateCrewTeam(int id, CrewDTO crewDTO) {
        CrewDAO crewDAO = new CrewDAOImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        Connection connection = Transaction.startTransaction();
        try {
            List<Integer> employeesID = crewDAO.getEmployeeIDByCrewTeamID(id, connection);

            List<EmployeeDTO> updatedEmployees = crewDTO.getCrewTeam();
            employeeService.updateCrewTeamTable1(employeesID, updatedEmployees, connection);
            List<EmployeeDTO> employeeCrewTeamDTO = employeeService.getCrewTeamEmployeesByID(employeesID, connection);
            Iterator<EmployeeDTO> insideIterator = employeeCrewTeamDTO.iterator();
            Iterator<EmployeeDTO> outsideIterator = updatedEmployees.iterator();

            List<Integer> insideEmployeeID = new ArrayList<Integer>();
            List<EmployeeDTO> outsideEmployeeList = new ArrayList<EmployeeDTO>();
            while (insideIterator.hasNext() & outsideIterator.hasNext()) {
                EmployeeDTO insideEmployee = insideIterator.next();
                EmployeeDTO outsideEmployee = outsideIterator.next();
                if (insideEmployee.getOrdinalNumber() != outsideEmployee.getOrdinalNumber()) {
                    outsideEmployeeList.add(outsideEmployee);
                    insideEmployeeID.add(insideEmployee.getEmployeeID());
                }
                employeeService.updateSelectedEmployeesStatusToFree(insideEmployeeID, connection);
                employeeService.updateSelectedEmployeesStatusToBlock(outsideEmployeeList, connection);
            }
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }
        return crewDTO;
    }

    @Override
    public void updateCrewTeamByRequest(CrewDTO crewDTO, Connection connection) throws SQLException {
        CrewDAO crewDAO = new CrewDAOImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();

        List<Integer> employeesID = crewDAO.getEmployeeIDByCrewTeamID(crewDTO.getCrewTeamID(), connection);
        employeeService.updateCrewTeamTable(crewDTO.getCrewTeamID(), crewDTO, connection);
        employeeService.updateSelectedEmployeesStatusToFree(employeesID, connection);

        List<EmployeeDTO> allNewCrewTeamEmployee = crewDTO.getCrewTeam();
        employeeService.updateSelectedEmployeesStatusToBlock(allNewCrewTeamEmployee, connection);


    }


    @Override
    public void deleteCrewTeam(int id, Connection connection) {
        CrewDAO crewDAO = new CrewDAOImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        try {
            List<Integer> idList = crewDAO.deleteCrewTeam(id, connection);
            employeeService.updateSelectedEmployeesStatusToFree(idList, connection);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in method deleteCrewTeam: ", e);
        }
    }

    @Override
    public CrewDTO getCrewTeamByIDWithOutConnection(int id) {
        Connection connection = Transaction.startTransaction();
        CrewDTO crewDTO = new CrewDTO();
        try {
            crewDTO = getCrewTeamByID(id, connection);
            connection.commit();
        } catch (SQLException e) {
            Transaction.rollBackConnection(connection);
            LOGGER.log(Level.SEVERE, "Exception in method getCrewTeamByIDWithOutConnection: ", e);
        } finally {
            Transaction.endTransaction(connection);
        }
        return crewDTO;
    }

    @Override
    public CrewDTO getCrewTeamByID(int id, Connection connection) {
        CrewDAO crewDAO = new CrewDAOImpl();
        CrewDTO crewDTO = new CrewDTO();
        EmployeeService employeeService = new EmployeeServiceImpl();
        try {
            List<Integer> idList = crewDAO.getAllCrewTeamID(id, connection);
            List<EmployeeDTO> employeeDTOList = employeeService.getCrewTeamEmployeesByID(idList, connection);
            crewDTO.setCrewTeamID(id);
            crewDTO.setCrewTeam(employeeDTOList);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        }
        return crewDTO;
    }

    @Override
    public CrewDTO createCrewTeam(List<EmployeeDTO> crewTeamDTO, Connection connection) {
        CrewDAO crewDAO = new CrewDAOImpl();
        CrewDTO crewDTO = new CrewDTO();
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> crewTeam = Transformer.listEmployeeDTOToEmployeeList(crewTeamDTO);
        int autoID;
        try {
            autoID = crewDAO.createCrewTeam(crewTeam, connection);
            employeeService.linkCrewNumberEmployees(crewTeamDTO, autoID, connection);
            employeeService.updateSelectedEmployeesStatusToBlock(crewTeamDTO, connection);

            crewDTO.setCrewTeamID(autoID);
            crewDTO.setCrewTeam(crewTeamDTO);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        }
        return crewDTO;
    }

}
