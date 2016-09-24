package ua.nure.borisov.summaryTask4.airline.dao.impl;

import ua.nure.borisov.summaryTask4.airline.connection.ConnectionDB;
import ua.nure.borisov.summaryTask4.airline.dao.api.UserDAO;
import ua.nure.borisov.summaryTask4.airline.entity.User;
import ua.nure.borisov.summaryTask4.airline.exception.DAOException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 17.08.2016.
 */
public class UserDAOImpl implements UserDAO {
    /*
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());
    private static final String TAKE_ROLE_FROM_REQUEST = "select role from users where login=? and password=?";

    @Override
    public boolean userEntranceChecking(String logIn, String loginPassword) {
        Statement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        final String LOGIN_CHECK = "select login, password from users where login = '" + logIn + "' AND password='" + loginPassword + "';";
        boolean login = false;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(LOGIN_CHECK);
            login = rs.first();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return login;

    }
    @Override
    public String takeRole(String login, String password) {
        String role = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(TAKE_ROLE_FROM_REQUEST);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 role = resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return role;
    }
    */
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());
    private static final String TAKE_ROLE_FROM_REQUEST = "select role from users where login=? and password=?";
    private static final String GET_USER_BY_LOGIN_AND_PASSWORD ="Select * From `users` Where `login`=? and `password`=?";

    @Override
    public boolean userEntranceChecking(String logIn, String loginPassword) {
        Statement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        final String LOGIN_CHECK = "select login, password from users where login = '" + logIn + "' AND password='" + loginPassword + "';";
        boolean login = false;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(LOGIN_CHECK);
            login = rs.first();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception: ", e);
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return login;

    }
    @Override
    public String takeRole(String login, String password) {
        String role = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(TAKE_ROLE_FROM_REQUEST);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return role;
    }

    @Override
    public User getUser(String login, String password, Connection connection) throws DAOException {
        User resultUser = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD);
            statement.setString(1,login);
            statement.setString(2,password);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                resultUser = new User();
                int k = 1;
                resultUser.setId(resultSet.getInt(k++));
                resultUser.setLogin(resultSet.getString(k++));
                resultUser.setPassword(resultSet.getString(k++));
                resultUser.setRole(resultSet.getString(k++));
            }

        } catch (SQLException e) {
            //log.error(e.getMessage());
            throw new DAOException("Error processing request", e);
        } finally {
            try {
                closeResultSet(resultSet);
                closeStatement(statement);
            } catch (SQLException e) {
                //log.error(e.getMessage());
                throw new DAOException("Error processing request", e);
            }
        }
        return resultUser;
    }

    @Override
    public User getUser(String login, String password) throws DAOException {
        try{
            Connection connection = ConnectionDB.getInstance().getConnection();
            User user = getUser(login, password, connection);
            connection.close();
            return user;
        } catch (SQLException ex){
            //TODO logger
            throw new DAOException("Error processing request",ex);
        }


    }

    private void closeStatement(Statement obj) throws SQLException {
        if (obj != null) {
            obj.close();
        }
    }
    private void closeResultSet(ResultSet obj) throws SQLException {
        if (obj != null) {
            obj.close();
        }
    }
}