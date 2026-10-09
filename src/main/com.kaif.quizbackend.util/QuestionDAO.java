package com.kaif.quizbackend.dao;

import com.kaif.quizbackend.model.Question;
import com.kaif.quizbackend.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions ORDER BY id ASC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getLong("id"));
                q.setQuestionTitle(rs.getString("question_text"));
                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));
                q.setRightAnswer(String.valueOf(rs.getInt("correct_answer")));
                q.setCategory(rs.getString("category"));
                questions.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public boolean addQuestion(Question q) {
        String sql = "INSERT INTO questions (question_text, option1, option2, option3, option4, correct_answer, category) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, q.getQuestionTitle());
            ps.setString(2, q.getOption1());
            ps.setString(3, q.getOption2());
            ps.setString(4, q.getOption3());
            ps.setString(5, q.getOption4());
            ps.setInt(6, Integer.parseInt(q.getRightAnswer()));
            ps.setString(7, q.getCategory());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateQuestion(Question q) {
        String sql = "UPDATE questions SET question_text = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ?, correct_answer = ?, category = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, q.getQuestionTitle());
            ps.setString(2, q.getOption1());
            ps.setString(3, q.getOption2());
            ps.setString(4, q.getOption3());
            ps.setString(5, q.getOption4());
            ps.setInt(6, Integer.parseInt(q.getRightAnswer()));
            ps.setString(7, q.getCategory());
            ps.setLong(8, q.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteQuestion(long id) {
        String sql = "DELETE FROM questions WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}