package controller;

import model.Board;
import model.Post;
import service.PostService;
import view.ConsoleView;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BoardController {
    private final Scanner scanner;
    private final ConsoleView view;
    private final PostService postService;

    public BoardController(Scanner scanner, ConsoleView view, PostService postService) {
        this.scanner = scanner;
        this.view = view;
        this.postService = postService;
    }



    private void handleBoard(String act, Map<String, String> params) {
        switch(act) {
            case "add":
                AddBoard(params);
                break;
            case "edit":
                EditBoard(params);
                break;
            case "remove":
                RemoveBoard(params);
                break;
            case "view":
                ViewBoard(params);
                break;
            default:
                // Todo
                break;
        }
    }

    private void RemoveBoard(Map<String, String> params) {
        if (!params.containsKey("boardId")) {
            view.displayMessageln("Board Id가 필요합니다.");
        }
        int boardId;
        try {
            boardId = Integer.parseInt(params.get("boardId"));
        } catch (NumberFormatException e ) {
            view.displayMessageln("Board Id가 유효하지 않습니다.");
            return;
        }
        Board boardToRemove = boardService.getBoardById(boardId);
        if (boardToRemove == null) {
            view.displayMessageln(boardId + "번 게시판이 존재하지 않습니다.");
            return;
        }
        boardService.removeBoard(boardId);
    }

    private void ViewBoard(Map<String, String> params) {
        if (!params.containsKey("boardName")) {
            view.displayMessageln("Board Name이 필요합니다.");
        }
        String boardName = params.get("boardName");
        List<Board> boards = boardService.getBoardsByName(boardName);
        if (boards.isEmpty()) {
            view.displayMessageln(boardName+" 게시판이 존재하지 않습니다.");
            return;
        }
        for (Board b : boards) {
            view.displayMessageln("["+b.getId()+"]"+b.getName());
            List<Post> posts = postService.getPostsByBoardId(b.getId());
            view.displayMessageln("글 번호/ 글 제목/ 작성일");
            for (Post p : posts) {
                view.displayMessageln(p.getId()+"/"+p.getTitle()+"/"+p.getCreatedDate());
            }
            view.displayMessageln("---------------------------------");
        }
    }

    private void AddBoard(Map<String, String> params) {
        if (!params.containsKey("boardId")) {
            view.displayMessageln("Board Id가 필요합니다.");
            return;
        }
        int boardId;
        try {
            boardId = Integer.parseInt(params.get("boardId"));
        } catch (NumberFormatException e ) {
            view.displayMessageln("Board Id가 유효하지 않습니다.");
            return;
        }
        Board board = boardService.getBoardById(boardId);
        if (board == null) {
            view.displayMessageln(boardId + "번 게시판이 존재하지 않습니다.");
        }
    }

    private void EditBoard(Map<String, String> params) {
        if (!params.containsKey("boardId")) {
            view.displayMessageln("boardId 파라미터가 필요합니다.");
            return;
        }
        int boardId;
        try {
            boardId = Integer.parseInt(params.get("boardId"));
        } catch (NumberFormatException e) {
            view.displayMessageln("유효한 boardId를 입력하세요.");
            return;
        }
        Board boardToEdit = boardService.getBoardById(boardId);
        if (boardToEdit == null) {
            view.displayMessageln(boardId + "번 게시판이 존재하지 않습니다.");
            return;
        }
        view.displayMessage("새로운 게시판 이름: ");
        String newName = scanner.nextLine();
        view.displayMessage("새로운 게시판 설명: ");
        String newDesc = scanner.nextLine();
        boardService.editBoard(boardId, newName, newDesc);
        view.displayMessageln(boardId + "번 게시판이 수정되었습니다.");
    }


}
