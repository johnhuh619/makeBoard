package model;

public class Board {
    private int id;
    private int userId;
    private int boardId;
    private String boardName;
    private String description;
    private String createdDate;
    private String updatedDate;

    public Board(int id, int userId, int boardId, String boardName, String description) {
        this.id = id;
        this.userId = userId;
        this.boardId = boardId;
        this.boardName = null;
        this.description = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
}
