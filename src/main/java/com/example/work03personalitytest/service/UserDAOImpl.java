package com.example.work03personalitytest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private DBConnectionInfo dbinfo = null;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private final String INSERT_USER_SQL = "INSERT INTO users (email, username, password, score) VALUES (?, ?, ?, ?)";
    private final String DELETE_USER_SQL = "DELETE FROM users WHERE email = ?";
    private final String READ_USER_SQL = "SELECT * FROM users WHERE email = ?";

    public UserDAOImpl(DBConnectionInfo connInfo) {
        this.dbinfo = connInfo;
    }

    private void connect() throws SQLException, ClassNotFoundException {
        Class.forName(dbinfo.getJdbcDriverName());
        connection = java.sql.DriverManager.getConnection(
                dbinfo.getUrl(),
                dbinfo.getUsername(),
                dbinfo.getPassword()
        );
    }

    private void disconnect() throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public int insertUser(UserDO user) throws SQLException, ClassNotFoundException {
        int result = 0;
        connect();
        if (connection != null) {
            preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getScore() != null ? user.getScore() : -1);
            result = preparedStatement.executeUpdate();
        }
        disconnect();
        return result;
    }

    @Override
    public int deleteUser(UserDO user) throws SQLException, ClassNotFoundException {
        int result = 0;
        connect();
        if (connection != null) {
            preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
            preparedStatement.setString(1, user.getEmail());
            result = preparedStatement.executeUpdate();
        }
        disconnect();
        return result;
    }

    @Override
    public UserDO readUser(UserDO user) throws SQLException, ClassNotFoundException {
        UserDO foundUser = null;
        connect();
        if (connection != null) {
            preparedStatement = connection.prepareStatement(READ_USER_SQL);
            preparedStatement.setString(1, user.getEmail());
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundUser = new UserDO();
                foundUser.setEmail(resultSet.getString("email"));
                foundUser.setUsername(resultSet.getString("username"));
                foundUser.setPassword(resultSet.getString("password"));
                foundUser.setScore(resultSet.getInt("score"));
            }
        }
        disconnect();
        return foundUser;
    }
}
