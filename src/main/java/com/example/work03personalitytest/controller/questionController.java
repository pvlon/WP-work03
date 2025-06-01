package com.example.work03personalitytest.controller;

import com.example.work03personalitytest.service.DBConnectionInfo;
import com.example.work03personalitytest.service.QuestionDAO;
import com.example.work03personalitytest.service.QuestionDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.LinkedHashMap;

@WebServlet("/questionController")
public class questionController extends HttpServlet {
    public questionController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        int page = (int) session.getAttribute("page");

        DBConnectionInfo connInfo = (DBConnectionInfo) getServletContext().getAttribute("db_info");
        QuestionDAO daoQ = new QuestionDAOImpl(connInfo);

        if (session.isNew() || session.getAttribute("username") == null) {
            request.setAttribute("error", "Session is new. Please start the test again.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        } else {
            String savedUsername = (String) session.getAttribute("username");
            if (savedUsername == null) {
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "index.jsp");
                return;
            } else if (!savedUsername.equals(username)) {
                session.setAttribute("username", username);
            }
        }

        String q1 = request.getParameter("q1");
        String q2 = request.getParameter("q2");

        try {
            Integer score = (Integer) session.getAttribute("score");
            if (q1 != null) score += Integer.parseInt(q1);
            if (q2 != null) score += Integer.parseInt(q2);
            session.setAttribute("score", score);

            // page 증가
            page++;
            session.setAttribute("page", page);

            // 마지막 문제 이후 결과 페이지로 이동
            if (page > 5) {

                request.getRequestDispatcher("resultController").forward(request, response);
                return;
            }

            String questionTitle1 = daoQ.readQuestionTitle(page * 2 - 1);
            String questionTitle2 = daoQ.readQuestionTitle(page * 2);
            LinkedHashMap<String, Integer> answerMap1 = daoQ.readAnswers(page * 2 - 1);
            LinkedHashMap<String, Integer> answerMap2 = daoQ.readAnswers(page * 2);

            request.setAttribute("questionTitle1", questionTitle1);
            request.setAttribute("questionTitle2", questionTitle2);
            request.setAttribute("answerMap1", answerMap1);
            request.setAttribute("answerMap2", answerMap2);
            request.setAttribute("page", page);
            request.setAttribute("username", username);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/views/question.jsp").forward(request, response);
    }
}
