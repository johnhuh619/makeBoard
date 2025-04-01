package service;

import model.Board;
import repository.BoardRepository;

public class BoardService {

    BoardRepository repo;

    public BoardService(BoardRepository repo) {
        this.repo = repo;
    }

    public int createBoard() {
        Board board = new Board(0,0,0,null,null);
        return repo.save(board);
    }

    public Board updateBoard(int boardId, String newName, String newDesc) {
        Board board = repo.findById(boardId);
        if(board != null) {
            return repo.update(board);
        }
        return null;
    }

    public boolean removeBoard(int boardId) {
        return repo.delete(boardId);
    }


    public Board getBoardById(int boardId) {
        return repo.findById(boardId);
    }
}
