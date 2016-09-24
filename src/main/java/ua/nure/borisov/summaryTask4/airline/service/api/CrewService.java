package ua.nure.borisov.summaryTask4.airline.service.api;

import ua.nure.borisov.summaryTask4.airline.dto.CrewDTO;
import ua.nure.borisov.summaryTask4.airline.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 07.08.2016.
 */
public interface CrewService {
    public CrewDTO createCrewTeam(List<EmployeeDTO> crewTeam,Connection connection);
    public void deleteCrewTeam(int id,Connection connection);
    public CrewDTO getCrewTeamByID(int id,Connection connection);
    public List<CrewDTO> getAllAvailableCrewTeams();
    public CrewDTO updateCrewTeam(int id,CrewDTO crewDTO);
    public CrewDTO getCrewTeamByIDWithOutConnection(int id);
    public void updateCrewTeamByRequest(CrewDTO crewDTO, Connection connection) throws SQLException;
}
