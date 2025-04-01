package repository;

import model.Board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
    private final List<Board> boardList = new ArrayList<>();

    public int save(Board board) {
        int id = boardList.size() + 1;
        board.setId(id);
        board.setBoardId(id);
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        board.setUpdatedDate(now);
        board.setCreatedDate(now);
        boardList.add(board);
        return id;
    }

    public Board update(Board board) {
        for(int i = 0; i < boardList.size(); i++) {
            if(boardList.get(i).getId() == board.getId()) {
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                board.setUpdatedDate(now);
                boardList.set(i, board);
                return board;
            }
        }
        return null;
    }

    public Board findById(int boardId) {
        for (Board board : boardList) {
            if (board.getBoardId() == boardId) {
                return board;
            }
        }
        return null;
    }

    public boolean delete(int boardId) {
        return boardList.removeIf(board -> board.getBoardId() == boardId);
    }


}
