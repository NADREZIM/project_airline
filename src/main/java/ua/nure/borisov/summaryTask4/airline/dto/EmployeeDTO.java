package ua.nure.borisov.summaryTask4.airline.dto;

/**
 * Created by User on 11.08.2016.
 */
public class EmployeeDTO {

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
        return "EmployeeDTO{" +
                "employeeID=" + employeeID +
                ", specialty='" + specialty + '\'' +
                ", name='" + name + '\'' +
                ", ordinalNumber=" + ordinalNumber +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeDTO that = (EmployeeDTO) o;

        if (employeeID != that.employeeID) return false;
        if (ordinalNumber != that.ordinalNumber) return false;
        if (status != that.status) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (specialty != null ? !specialty.equals(that.specialty) : that.specialty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeID;
        result = 31 * result + (specialty != null ? specialty.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + ordinalNumber;
        result = 31 * result + (status ? 1 : 0);
        return result;
    }


}
