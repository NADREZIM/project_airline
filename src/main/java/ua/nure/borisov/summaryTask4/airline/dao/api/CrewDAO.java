package ua.nure.borisov.summaryTask4.airline.dao.api;

import ua.nure.borisov.summaryTask4.airline.entity.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 05.08.2016.
 */
public interface CrewDAO {

    public int createCrewTeam(List<Employee> crewTeam, Connection connection) throws SQLException;
    public List<Integer> deleteCrewTeam(int id, Connection connection) throws SQLException;
    public List<Integer> getAllCrewTeamID(int id, Connection connection) throws SQLException;
    public HashMap<Integer,List<Integer>> selectAllFromCrewTeam(Connection connection) throws SQLException;
    public List<Integer> getEmployeeIDByCrewTeamID(int id, Connection connection) throws SQLException;

}
