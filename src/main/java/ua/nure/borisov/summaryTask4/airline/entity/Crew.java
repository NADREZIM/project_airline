package ua.nure.borisov.summaryTask4.airline.entity;

import java.util.List;

/**
 * Created by User on 04.08.2016.
 */
public class Crew {
    private int crewTeamID;
    List<Employee> crewTeam;

    public int getCrewTeamID() {
        return crewTeamID;
    }

    public void setCrewTeamID(int crewTeamID) {
        this.crewTeamID = crewTeamID;
    }

    public List<Employee> getCrewTeam() {
        return crewTeam;
    }

    public void setCrewTeam(List<Employee> crewTeam) {
        this.crewTeam = crewTeam;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "crewTeamID=" + crewTeamID +
                ", crewTeam=" + crewTeam +
                '}';
    }
}
