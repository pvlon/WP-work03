package com.example.work03personalitytest.service;

public class UserDO {
    private String email;
    private String username;
    private String password;
    private Integer score;

    public UserDO() {}
    public UserDO(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.score = -1;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserDO other = (UserDO) obj;
        return email.equals(other.email) &&
                username.equals(other.username) &&
                password.equals(other.password);
    }
}
