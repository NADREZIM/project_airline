package ua.nure.borisov.summaryTask4.airline.service.api;

/**
 * Created by User on 25.08.2016.
 */
public class ServiceDataException extends Exception {
    public ServiceDataException(String message) {
        super(message);
    }

    public ServiceDataException() {
    }

    public ServiceDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
