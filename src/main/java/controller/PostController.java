package controller;

import model.Board;
import model.Post;
import service.BoardService;
import service.PostService;
import sys.Request;
import view.ConsoleView;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PostController implements Controller {
    private final Scanner sc;
    private final ConsoleView view;
    private final PostService postService;
    private final BoardService boardService;

    public PostController(Scanner sc, ConsoleView view, PostService postService, BoardService boardService) {
        this.sc = sc;
        this.view = view;
        this.postService = postService;
        this.boardService = boardService;
    }

    @Override
    public void handleRequest(Request request) {
        try {
            String[] pathParts = request.getPathParts();
            Map<String, String> params = request.getParams();
            String act = pathParts[2];

            switch (act) {
                case "add":
                    handleAddPost(params);
                    break;
                case "edit":
                    handleEditPost(params);
                    break;
                case "remove":
                    handleRemovePost(params);
                    break;
                case "view":
                    handleViewPost(params);
                default:
                    view.displayMessageln("[404] 잘못된 요청입니다.");
            }
        } catch (Exception e) {
            view.displayMessageln("[500] 서버 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private void handleViewPost(Map<String, String> params) {
        view.displayMessage("어떤 게시물을 조회할까요? ");
        String cmd = sc.nextLine();
        int id;
        try {
            id = Integer.parseInt(cmd);
        } catch (NumberFormatException e) {
            view.displayMessageln("올바른 번호를 입력해주세요");
            return;
        }

        // postService: READ
        Post post = postService.getPost(id);
        if (post == null) {
            view.displayMessageln("[404] " + id + "번 게시글이 없습니다.");
            return;
        }
        view.displayMessageln(id + "번 게시물");
        view.displayMessageln("제목: " + post.getTitle());
        view.displayMessageln("내용: " + post.getContent());
    }

    private void handleAddPost(Map<String, String> params) {
        if (!params.containsKey("boardId")) {
            throw new IllegalArgumentException("boardId가 필요합니다.");
        }

        Integer boardId = Integer.parseInt(params.get("boardId"));
//        Board board = boardService.getBoardById(boardId);
//
//        if (board == null) {
//            throw new IllegalArgumentException(boardId + "번 게시판이 존재하지 않습니다.");
//        }

        // TODO: userId를 현재 로그인된 사용자로부터 가져오도록 수정
        Integer userId = 1; // 임시 값

        view.displayMessage("제목: ");
        String title = sc.nextLine();
        view.displayMessage("내용: ");
        String content = sc.nextLine();

        postService.createPost(title, content, boardId, userId);
        view.displayMessageln("게시글이 작성되었습니다");
    }

    private void handleEditPost(Map<String, String> params) {
        if (!params.containsKey("postId")) {
            throw new IllegalArgumentException("[404] postId가 필요합니다.");
        }

        Integer postId = Integer.parseInt(params.get("postId"));
        Post post = postService.getPost(postId);

        if (post == null) {
            throw new IllegalArgumentException("[404]" + postId + "번 게시글이 존재하지 않습니다.");
        }

        view.displayMessage("제목: ");
        String title = sc.nextLine();
        view.displayMessage("내용: ");
        String content = sc.nextLine();

        postService.updatePost(postId, title, content);
        view.displayMessageln("[200]" + postId + "번 게시물이 성공적으로 수정되었습니다.");
    }

    private void handleRemovePost(Map<String, String> params) {
        if (!params.containsKey("postId")) {
            throw new IllegalArgumentException("postId가 필요합니다.");
        }

        Integer postId = Integer.parseInt(params.get("postId"));
        Post post = postService.getPost(postId);

        if (post == null) {
            throw new IllegalArgumentException(postId + "번 게시글이 존재하지 않습니다.");
        }

        postService.deletePost(postId);
        view.displayMessageln("[200] 게시글 " + postId + " 삭제되었습니다");
    }

    private void viewPosts() {
        List<Post> posts = postService.getPosts();
        for (Post post : posts) {
            view.displayMessageln(post.getId() + "번 게시글");
            view.displayMessageln("제목 : " + post.getTitle());
            view.displayMessageln("내용 : " + post.getTitle());
        }
    }
}