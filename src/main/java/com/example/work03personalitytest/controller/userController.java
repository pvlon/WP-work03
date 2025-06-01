package com.example.work03personalitytest.controller;

import com.example.work03personalitytest.service.DBConnectionInfo;
import com.example.work03personalitytest.service.UserDAO;
import com.example.work03personalitytest.service.UserDAOImpl;
import com.example.work03personalitytest.service.UserDO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.Context;
import java.io.IOException;
import java.util.List;

@WebServlet("/userController")
public class userController extends HttpServlet {
    public userController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDO inputUser = new UserDO(email, username, password);

        DBConnectionInfo connInfo = (DBConnectionInfo) getServletContext().getAttribute("db_info");
        UserDAO dao = new UserDAOImpl(connInfo);
        HttpSession session = request.getSession();

        session.setAttribute("email", email);
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("score", 0);
        session.setAttribute("page", 0);


        try {
            UserDO readUser = dao.readUser(inputUser);

            if (readUser == null) {
                request.getRequestDispatcher("questionController").forward(request, response);
            } else if (readUser.equals(inputUser)) {
                // 기존 유저 → 결과 바로 출력
                session.setAttribute("user", readUser);
                request.getRequestDispatcher("resultController").forward(request, response);
            } else {
                request.setAttribute("error", "Username or password is incorrect.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
