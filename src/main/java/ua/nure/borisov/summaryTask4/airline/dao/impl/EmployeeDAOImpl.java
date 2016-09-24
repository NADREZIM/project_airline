package ua.nure.borisov.summaryTask4.airline.dao.impl;

import ua.nure.borisov.summaryTask4.airline.connection.ConnectionDB;
import ua.nure.borisov.summaryTask4.airline.dao.api.EmployeeDAO;
import ua.nure.borisov.summaryTask4.airline.entity.Crew;
import ua.nure.borisov.summaryTask4.airline.entity.Employee;
import ua.nure.borisov.summaryTask4.airline.transaction.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 09.08.2016.
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class.getName());
    private static final String ADD_EMPLOYEE = "insert into employee(specialty,name,ordinal_number)values(?,?,?)";
    private static final String DELETE_EMPLOYEE = "delete from employee where employee_id = ?";
    private static final String GET_ALL_EMPLOYEES = "select*from employee";
    private static final String UPDATE_EMPLOYEE = "update employee set specialty=IF(?='',specialty,?), name=IF(?='',name,?), ordinal_number=IF(?=0,ordinal_number,?), status=IF(?=0,status,?)where employee_id=?";
    private static final String UPDATE_CREW_TEAM_STATUS = "update employee set status = ? where employee_id = ?";
    private static final String LINK_CREW_NUMBER_EMPLOYEES = "insert into crew_team(crew_team_id,employee_id)value(?,?)";
    private static final String GET_CREW_TEAM_BY_ID = "select*from employee where employee_id = ?";
    private static final String UPDATE_CREW_TEAM_TABLE = "update crew_team set employee_id = ? where employee_id = ?";
    private static final String CHECK_BEFORE_ADDING_EMPLOYEE = "select*from employee where name = ?";
    private static final String GET_EMPLOYEE_BY_NAME = "select*from employee where name = ?";
    private static final String DELETE_EMPLOYEE_ID_FROM_CREW_TEAM_TABLE = "delete from crew_team where crew_team_id = ?";
    private static final String INSERT_EMPLOYEE_INTO_CREW_TEAM_BY_CREW_TEAM_ID = "insert into crew_team(crew_team_id,employee_id) values(?,?)";
    private static final String GET_ALL_EGOR_EGOROV_PILOT = "select*from employee  where name ='Egorov Egor'";
    /**
     * @author — Borisov Artem
     * @param names - List employees names
     * @param connection - connection with data base on the same transaction
     * @return - full employee objects list
     * @throws SQLException
     */

    @Override
    public List<Employee> getEmployeeByName(List<String> names, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Employee> allEmployees = new ArrayList<Employee>();
        try {
            for (String name : names) {
                preparedStatement = connection.prepareStatement(GET_EMPLOYEE_BY_NAME);
                preparedStatement.setString(1, name);
                rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeID(rs.getInt("employee_id"));
                    employee.setSpecialty(rs.getString("specialty"));
                    employee.setName(rs.getString("name"));
                    employee.setOrdinalNumber(rs.getInt("ordinal_number"));
                    int digitStatus = rs.getInt("status");
                    boolean status = false;
                    if (digitStatus > 1) {
                        status = true;
                    }
                    employee.setStatus(status);
                    allEmployees.add(employee);
                }
                closeResultSet(rs);
                closeStatement(preparedStatement);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
        }
        return allEmployees;
    }
    /**
     * check if there is any records in data base with a such employee name
     * @author — Borisov Artem
     * @param employee - - main employee parameters
     * @param connection - connection with data base on the same transaction
     * @return - false if there is no records with a such name or true if vise versa
     * @throws SQLException
     */
    @Override
    public boolean checkBeforeAddingEmployee(Employee employee, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        boolean b = false;
        try {
            preparedStatement = connection.prepareStatement(CHECK_BEFORE_ADDING_EMPLOYEE);
            preparedStatement.setString(1, employee.getName());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                b = true;
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
        }

        return b;
    }
    /**
     * delete all records from crew_team_table by id and then create record with a same crew_team_id
     * but with another records in employee_id
     * @author — Borisov Artem
     * @param id - ID in crew_team table
     * @param crew - crew object
     * @throws SQLException
     */
    @Override
    public void updateCrewTeamTable(int id, Crew crew, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_ID_FROM_CREW_TEAM_TABLE);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            closeStatement(preparedStatement);
            List<Employee> employeeList = crew.getCrewTeam();
            for (Employee employee : employeeList) {
                int employeeID = employee.getEmployeeID();
                preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_INTO_CREW_TEAM_BY_CREW_TEAM_ID);
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, employeeID);
                preparedStatement.execute();
                closeStatement(preparedStatement);
            }
        } finally {
            closeStatement(preparedStatement);
        }
    }
    /**
     * delete all records from crew_team_table by id and then create record with a same crew_team_id
     * but with another records in employee_id
     * @author — Borisov Artem
     * @param insideEmployeeID - ID in crew_team table
     * @param outsideEmployee - crew object
     * @throws SQLException
     */
    @Override
    public void updateCrewTeamTable1(List<Integer> insideEmployeeID, List<Employee> outsideEmployee, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        Iterator<Integer> itr1 = insideEmployeeID.iterator();
        Iterator<Employee> itr2 = outsideEmployee.iterator();
        try {
            while (itr1.hasNext() & itr2.hasNext()) {
                preparedStatement = connection.prepareStatement(UPDATE_CREW_TEAM_TABLE);
                Employee employee = itr2.next();
                int employeeID = employee.getEmployeeID();
                preparedStatement.setInt(1, employeeID);
                preparedStatement.setInt(2, itr1.next());
                preparedStatement.execute();
            }
        } finally {
            closeStatement(preparedStatement);
        }
    }
    /**
     * get Employee objects by ID
     * @author — Borisov Artem
     * @param crewTeamMembersID - all crew team members ID
     * @param connection - connection with data base on the same transaction
     * @throws SQLException
     */
    @Override
    public List<Employee> getCrewTeamEmployeesByID(List<Integer> crewTeamMembersID, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        List<Employee> employees = new ArrayList<Employee>();
        ResultSet rs = null;
        try {
            for (Integer employeeID : crewTeamMembersID) {
                preparedStatement = connection.prepareStatement(GET_CREW_TEAM_BY_ID);
                preparedStatement.setInt(1, employeeID);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeID(rs.getInt(1));
                    employee.setSpecialty(rs.getString(2));
                    employee.setName(rs.getString(3));
                    employee.setOrdinalNumber(rs.getInt(4));
                    int digitStatus = rs.getInt(5);
                    boolean status = false;
                    if (digitStatus > 1) {
                        status = true;
                    }
                    employee.setStatus(status);
                    employees.add(employee);
                }
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
        }
        return employees;
    }
    /**
     * update chosen employees status to free
     * @author — Borisov Artem
     * @param crewTeamID - all crew team members ID
     * @param connection - connection with data base on the same transaction
     * @throws SQLException
     */
    @Override
    public void updateSelectedEmployeesStatusToFree(List<Integer> crewTeamID, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            for (Integer employeeID : crewTeamID) {
                preparedStatement = connection.prepareStatement(UPDATE_CREW_TEAM_STATUS);
                preparedStatement.setInt(1, 1);
                preparedStatement.setInt(2, employeeID);
                preparedStatement.execute();
                closeStatement(preparedStatement);
            }
        } finally {
            closeStatement(preparedStatement);
        }
    }
    /**
     * update chosen employees status to block
     * @author — Borisov Artem
     * @param crewTeam - all crew team members
     * @param connection - connection with data base on the same transaction
     * @throws SQLException
     */
    @Override
    public void updateSelectedEmployeesStatusToBlock(List<Employee> crewTeam, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            for (Employee employee : crewTeam) {
                preparedStatement = connection.prepareStatement(UPDATE_CREW_TEAM_STATUS);
                preparedStatement.setInt(1, 2);
                preparedStatement.setInt(2, employee.getEmployeeID());
                preparedStatement.execute();
                closeStatement(preparedStatement);
            }
        } finally {
            closeStatement(preparedStatement);
        }

    }
    /**
     * link crew Number with Employee ID
     * @author — Borisov Artem
     * @param crewTeam - all crew team members
     * @param autoID - crew Number ID
     * @param connection - connection with data base on the same transaction
     * @throws SQLException
     */
    @Override
    public void linkCrewNumberEmployees(List<Employee> crewTeam, int autoID, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            for (Employee employee : crewTeam) {
                preparedStatement = connection.prepareStatement(LINK_CREW_NUMBER_EMPLOYEES);
                preparedStatement.setInt(1, autoID);
                preparedStatement.setInt(2, employee.getEmployeeID());
                preparedStatement.execute();
                closeStatement(preparedStatement);
            }
        } finally {
            closeStatement(preparedStatement);
        }

    }

    @Override
    public Employee updateEmployee(int id, Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE);
            preparedStatement.setString(1, employee.getSpecialty());
            preparedStatement.setString(2, employee.getSpecialty());
            preparedStatement.setString(3, employee.getName());
            preparedStatement.setString(4, employee.getName());
            preparedStatement.setInt(5, employee.getOrdinalNumber());
            preparedStatement.setInt(6, employee.getOrdinalNumber());
            boolean status = employee.getStatus();
            int digitStatus = 1;
            if (status) {
                digitStatus = 2;
            }
            preparedStatement.setInt(7, digitStatus);
            preparedStatement.setInt(8, digitStatus);
            preparedStatement.setInt(9, id);
            preparedStatement.execute();
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllPilots() throws SQLException {
        List<Employee> allEmployee = getAllEmployees();
        List<Employee> allPilots = new ArrayList<Employee>();
        for (Employee employee : allEmployee) {
            if (employee.getSpecialty().equals("pilot")) {
                allPilots.add(employee);
            }
        }
        return allPilots;
    }

    @Override
    public List<Employee> getAllNavigators() throws SQLException {
        List<Employee> allEmployee = getAllEmployees();
        List<Employee> allNavigators = new ArrayList<Employee>();
        for (Employee employee : allEmployee) {
            if (employee.getSpecialty().equals("navigator")) {
                allNavigators.add(employee);
            }
        }
        return allNavigators;
    }

    @Override
    public List<Employee> getAllRadiomen() throws SQLException {
        List<Employee> allEmployee = getAllEmployees();
        List<Employee> allRadiomen = new ArrayList<Employee>();
        for (Employee employee : allEmployee) {
            if (employee.getSpecialty().equals("radioman")) {
                allRadiomen.add(employee);
            }
        }
        return allRadiomen;
    }

    @Override
    public List<Employee> getAllStewardess() throws SQLException {
        List<Employee> allEmployee = getAllEmployees();
        List<Employee> allStewardess = new ArrayList<Employee>();
        for (Employee employee : allEmployee) {
            if (employee.getSpecialty().equals("stewardess")) {
                allStewardess.add(employee);
            }
        }
        return allStewardess;
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> allEmployees = new ArrayList<Employee>();
        java.sql.Statement statement = null;
        ResultSet rs = null;
        Connection connection;
        connection = Transaction.startTransaction();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(GET_ALL_EMPLOYEES);
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getInt("employee_id"));
                employee.setSpecialty(rs.getString("specialty"));
                employee.setName(rs.getString("name"));
                employee.setOrdinalNumber(rs.getInt("ordinal_number"));
                boolean status = false;
                int digitStatus = rs.getInt("status");
                if (digitStatus > 1) {
                    status = true;
                }
                employee.setStatus(status);
                allEmployees.add(employee);
            }
            connection.commit();
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            Transaction.endTransaction(connection);
        }
        return allEmployees;
    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
    }


    /**
     * @author - Borisov Artem
     * @param employee - main employee parameters
     * @param connection - connection on the same transaction
     * @return - generated key in data base
     * @throws SQLException
     */
    @Override
    public int addEmployee(Employee employee, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int autoID = 0;
        try {
            preparedStatement = connection.prepareStatement(ADD_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getSpecialty());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3, employee.getOrdinalNumber());
            preparedStatement.executeUpdate();
            rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                autoID = rs.getInt(1);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(preparedStatement);
        }
        return autoID;
    }

    public List<Employee> getEgorEgorov(){
        List<Employee> Egor_Egorov = new ArrayList<Employee>();
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(GET_ALL_EGOR_EGOROV_PILOT);
            while (rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getInt("employee_id"));
                employee.setSpecialty(rs.getString("specialty"));
                employee.setName(rs.getString("name"));
                employee.setOrdinalNumber(rs.getInt("ordinal_number"));
                boolean status = false;
                int digitStatus = rs.getInt("status");
                if (digitStatus > 1) {
                    status = true;
                }
                employee.setStatus(status);
                Egor_Egorov.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
        return Egor_Egorov;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Connection is not closed", e);
            }
        }
    }

    public void closeStatement(Statement statement) {
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "PreparedStatement is not closed", e);
            }
    }

    public void closeResultSet(ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "ResultSet is not closed", e);
            }
    }


}