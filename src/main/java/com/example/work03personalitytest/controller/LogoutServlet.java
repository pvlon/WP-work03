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

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            // 세션에서 유저 정보 가져오기
            UserDO user = (UserDO) session.getAttribute("user");

            if (user != null) {
                // DB에서 유저 삭제
                DBConnectionInfo connInfo = (DBConnectionInfo) getServletContext().getAttribute("db_info");
                UserDAO dao = new UserDAOImpl(connInfo);
                try {
                    dao.deleteUser(user);
                } catch (Exception e) {
                    e.printStackTrace();  // 실 서비스에선 로깅 권장
                }
            }

            // 세션 종료
            session.invalidate();
        }

        // index.jsp로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
