package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Account {
    private int id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime joinDate;
    private LocalDateTime updatedDate;

    public Account(int id, String username, String password, String email, LocalDateTime joinDate, LocalDateTime updatedDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.joinDate = joinDate;
        this.updatedDate = updatedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
