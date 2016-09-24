package ua.nure.borisov.summaryTask4.airline.entity;

/**
 * Created by User on 08.08.2016.
 */
public class Employee {

    private int employeeID;
    private String specialty;
    private String name;
    private int ordinalNumber;
    private boolean status;


    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", specialty='" + specialty + '\'' +
                ", name='" + name + '\'' +
                ", ordinalNumber=" + ordinalNumber +
                ", status=" + status +
                '}';
    }
}
