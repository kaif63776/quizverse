package com.kaif.quizbackend.servlet;

import com.google.gson.Gson;
import com.kaif.quizbackend.dao.UserDAO;
import com.kaif.quizbackend.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/users/*")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private Gson gson = new Gson();

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        setCORSHeaders(resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setCORSHeaders(resp);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String pathInfo = req.getPathInfo();
        if ("/leaderboard".equals(pathInfo)) {
            List<User> leaderboard = userDAO.getLeaderboard();
            resp.getWriter().write(gson.toJson(leaderboard));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setCORSHeaders(resp);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String pathInfo = req.getPathInfo();
        BufferedReader reader = req.getReader();

        if ("/register".equals(pathInfo)) {
            User user = gson.fromJson(reader, User.class);
            boolean success = userDAO.registerUser(user);
            resp.getWriter().write(gson.toJson(success));
        } else if ("/login".equals(pathInfo)) {
            User credentials = gson.fromJson(reader, User.class);
            User user = userDAO.loginUser(credentials.getUsername(), credentials.getPassword());
            if (user != null) {
                resp.getWriter().write(gson.toJson(user));
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().write("{\"error\": \"Invalid credentials\"}");
            }
        } else if ("/updatescore".equals(pathInfo)) {
            User user = gson.fromJson(reader, User.class);
            userDAO.updateScore(user.getId(), user.getScore());
            resp.getWriter().write("{\"status\": \"Score updated successfully\"}");
        }
    }

    private void setCORSHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}