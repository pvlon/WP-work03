package com.example.work03personalitytest.controller;

import com.example.work03personalitytest.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/resultController")
public class resultController extends HttpServlet {
    public resultController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Integer score = (Integer) session.getAttribute("score");

        DBConnectionInfo connInfo = (DBConnectionInfo) getServletContext().getAttribute("db_info");
        UserDAO dao = new UserDAOImpl(connInfo);
        QuestionDAO daoQ = new QuestionDAOImpl(connInfo);

        if (session.isNew() || session.getAttribute("username") == null) {
            request.setAttribute("error", "Session is new. Please start the test again.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
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

        UserDO user = (UserDO) session.getAttribute("user");


        try {
            if (user == null) {
                user = new UserDO();
                user.setUsername(username);
                user.setEmail((String) session.getAttribute("email"));
                user.setPassword((String) session.getAttribute("password"));
                user.setScore(score);
                dao.insertUser(user);
            } else {
                score = user.getScore();
            }

            int index = (score-11)/10;
            if (index < 0) index = 0;

            String evaluationResult = daoQ.evaluationResult(index);

            request.setAttribute("username", user.getUsername());
            request.setAttribute("score", user.getScore());
            request.setAttribute("evaluationResult", evaluationResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/views/result.jsp").forward(request, response);
    }
}
