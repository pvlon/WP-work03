package com.example.work03personalitytest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class QuestionDAOImpl implements QuestionDAO {
    private DBConnectionInfo dbinfo = null;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private final String READ_QUESTION = "SELECT * FROM question WHERE id = ?";
    private final String READ_ANSWER = "SELECT * FROM answers WHERE id = ?";
    private final String EVALUATION_RESULT = "SELECT * FROM evaluationresults WHERE index = ?";

    public QuestionDAOImpl(DBConnectionInfo connInfo) {
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
    public String readQuestionTitle(int id) throws SQLException, ClassNotFoundException {
        String questionTitle = null;
        connect();
        if (connection != null) {
            preparedStatement = connection.prepareStatement(READ_QUESTION);
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                questionTitle = resultSet.getString("title");
            }
        }
        disconnect();
        return questionTitle;
    }

    @Override
    public LinkedHashMap<String, Integer> readAnswers(int id) throws SQLException, ClassNotFoundException {
        LinkedHashMap<String, Integer> answers = new LinkedHashMap<>();
        connect();
        if (connection != null) {
            preparedStatement = connection.prepareStatement(READ_ANSWER);
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String answerText = resultSet.getString("content");
                int score = resultSet.getInt("score");
                answers.put(answerText, score);
            }
        }
        disconnect();
        return answers;
    }

    @Override
    public String evaluationResult(int index) throws Exception {
        String result = null;
        connect();
        if (connection != null) {
            preparedStatement = connection.prepareStatement(EVALUATION_RESULT);
            preparedStatement.setInt(1, index);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("info");
            }
        }
        disconnect();
        return result;
    }
}
