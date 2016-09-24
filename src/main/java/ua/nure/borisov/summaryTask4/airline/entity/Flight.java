package ua.nure.borisov.summaryTask4.airline.entity;

import java.util.Date;

/**
 * Created by User on 04.08.2016.
 */
public class Flight {
    private int flightNumber;
    private String pointOfDeparture;
    private String pointOfDestination;
    private Date departureDate;
    private String flightName;
    private Crew crewTeam;
    private boolean flightStatus;

    public Crew getCrewTeam() {
        return crewTeam;
    }

    public void setCrewTeam(Crew crewTeam) {
        this.crewTeam = crewTeam;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public void setPointOfDeparture(String pointOfDeparture) {
        this.pointOfDeparture = pointOfDeparture;
    }

    public String getPointOfDestination() {
        return pointOfDestination;
    }

    public void setPointOfDestination(String pointOfDestination) {
        this.pointOfDestination = pointOfDestination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public boolean getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(boolean flightStatus) {
        this.flightStatus = flightStatus;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber=" + flightNumber +
                ", pointOfDeparture='" + pointOfDeparture + '\'' +
                ", pointOfDestination='" + pointOfDestination + '\'' +
                ", departureDate=" + departureDate +
                ", flightName='" + flightName + '\'' +
                ", crewTeam=" + crewTeam +
                ", flightStatus=" + flightStatus +
                '}';
    }
}
