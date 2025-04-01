package model;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private int boardId;
    private int userId;

    public Post(int id, String title, String content, LocalDateTime createdDate, int boardId, int userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.updatedDate = createdDate;
        this.boardId = boardId;
        this.userId = userId;
    }

    // Getter
    public String getContent() {
        return content;
    }

    public int getBoardId() {
        return boardId;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    // Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
