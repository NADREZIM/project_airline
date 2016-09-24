package ua.nure.borisov.summaryTask4.airline.dao.api;

import ua.nure.borisov.summaryTask4.airline.entity.User;
import ua.nure.borisov.summaryTask4.airline.exception.DAOException;

import java.sql.Connection;

/**
 * Created by User on 17.08.2016.
 */
public interface UserDAO {
    /*
    public boolean userEntranceChecking(String logIn, String loginPassword);
    public String takeRole(String login, String password);
    */
    public boolean userEntranceChecking(String logIn, String loginPassword);
    public String takeRole(String login, String password);
    public User getUser(String login, String password, Connection connection) throws DAOException;
    public User getUser(String login, String password) throws DAOException;
}
