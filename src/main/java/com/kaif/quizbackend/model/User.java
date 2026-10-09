package com.kaif.quizbackend.model;

public class User {
    private Long id;
    private String username;
    private String password;
    private int score;

    public User() {
    }

    public User(Long id, String username, String password, int score) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
