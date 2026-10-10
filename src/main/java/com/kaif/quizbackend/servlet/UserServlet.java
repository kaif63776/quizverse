package com.kaif.quizbackend.servlet;

import com.google.gson.Gson;
import com.kaif.quizbackend.dao.UserDAO;
import com.kaif.quizbackend.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/users/*")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        if ("/leaderboard".equals(pathInfo)) {
            List<User> leaderboard = userDAO.getLeaderboard();
            out.print(gson.toJson(leaderboard));
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        User inputUser = gson.fromJson(sb.toString(), User.class);

        if ("/register".equals(pathInfo)) {
            boolean success = userDAO.registerUser(inputUser);
            if (success) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                out.print("{\"message\":\"User registered successfully\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"message\":\"Registration failed\"}");
            }
        } else if ("/login".equals(pathInfo)) {
            User loggedIn = userDAO.loginUser(inputUser.getUsername(), inputUser.getPassword());
            if (loggedIn != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                out.print(gson.toJson(loggedIn));
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.print("{\"message\":\"Invalid credentials\"}");
            }
        } else if ("/score".equals(pathInfo)) {
            if (inputUser != null && inputUser.getUsername() != null) {
                userDAO.updateScore(inputUser.getUsername(), inputUser.getScore());
                resp.setStatus(HttpServletResponse.SC_OK);
                out.print("{\"message\":\"Score updated successfully\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"message\":\"Invalid user data\"}");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
