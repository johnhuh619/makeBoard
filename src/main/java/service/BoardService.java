package service;

import model.Board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BoardService {

    private List<Board> boardList = new ArrayList<>();

    public int createBoard() {
        int id = boardList.size() + 1;
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Board newBoard = new Board(id, 1, id, now, now);
        boardList.add(newBoard);
        return id;
    }

    public Board updateBoard(int boardId, String newName, String newDesc) {
        for (Board board : boardList) {
            if (board.getBoardId() == boardId) {
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                board.setUpdatedDate(now);
                return board;
            }
        }
        return null;
    }

    public boolean removeBoard(int boardId) {
        return boardList.removeIf(board -> board.getId() == boardId);
    }


    public Board getBoardById(int boardId) {
        for (Board board : boardList) {
            if (board.getId() == boardId) {
                return board;
            }
        }
        return null;
    }
}
