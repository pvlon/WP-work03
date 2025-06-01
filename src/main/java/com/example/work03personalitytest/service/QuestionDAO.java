package com.example.work03personalitytest.service;

import java.util.LinkedHashMap;

public interface QuestionDAO {
    String readQuestionTitle(int id) throws Exception;
    LinkedHashMap<String, Integer> readAnswers(int id) throws Exception;
    String evaluationResult(int index) throws Exception;
}
