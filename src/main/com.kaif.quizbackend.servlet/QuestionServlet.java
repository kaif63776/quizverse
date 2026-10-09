package com.kaif.quizbackend.servlet;

import com.google.gson.Gson;
import com.kaif.quizbackend.dao.QuestionDAO;
import com.kaif.quizbackend.model.Question;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/questions/*")
public class QuestionServlet extends HttpServlet {
    private QuestionDAO questionDAO = new QuestionDAO();
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

        String pathInfo = req.getPathInfo(); // Expecting something like /category/Java
        if (pathInfo != null && pathInfo.startsWith("/category/")) {
            String category = pathInfo.replace("/category/", "").trim();
            List<Question> questions = questionDAO.getQuestionsByCategory(category);
            resp.getWriter().write(gson.toJson(questions));
        } else {
            // Fallback to fetch all questions
            List<Question> allQuestions = questionDAO.getAllQuestions();
            resp.getWriter().write(gson.toJson(allQuestions));
        }
    }

    private void setCORSHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}