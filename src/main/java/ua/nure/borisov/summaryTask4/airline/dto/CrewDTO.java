package ua.nure.borisov.summaryTask4.airline.dto;

import java.util.List;

/**
 * Created by User on 07.08.2016.
 */
public class CrewDTO {
    private int crewTeamID;
    List<EmployeeDTO> crewTeam;

    public int getCrewTeamID() {
        return crewTeamID;
    }

    public void setCrewTeamID(int crewTeamID) {
        this.crewTeamID = crewTeamID;
    }

    public List<EmployeeDTO> getCrewTeam() {
        return crewTeam;
    }

    public void setCrewTeam(List<EmployeeDTO> crewTeam) {
        this.crewTeam = crewTeam;
    }

    @Override
    public String toString() {
        return "CrewDTO{" +
                "crewTeamID=" + crewTeamID +
                ", crewTeam=" + crewTeam +
                '}';
    }
}
