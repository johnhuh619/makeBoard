package controller;

import model.Post;
import service.PostService;
import view.ConsoleView;

import java.util.List;
import java.util.Scanner;

public class CommandController {
    private final Scanner scanner;
    private final ConsoleView view;
    private final PostService postService;

    public CommandController(Scanner scanner, ConsoleView view, PostService postService) {
        this.scanner = scanner;
        this.view = view;
        this.postService = postService;
    }


    public void Start() {
        boolean run = true;
        view.displayMessageln("Get Started...");
        while (run) {
            view.displayMessage("command > ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                view.displayMessageln("Shutting down...");
                run = false;
                continue;
            }
            switch (command) {
                case "목록":
                    viewPosts();
                    break;
                case "작성":
                    createPost();
                    break;
                case "조회":
                    viewPost();
                    break;
                case "삭제":
                    deletePost();
                    break;
                case "수정":
                    updatePost();
                    break;
                default:
                    view.displayMessageln("존재하지 않는 명령어 입니다.");
                    break;
            }
        }
        scanner.close();
    }

    private void viewPosts() {
        List<Post> posts = postService.getPosts();
        for(Post post : posts) {
            view.displayMessageln(post.getId()+"번 게시글");
            view.displayMessageln("제목 : "+post.getTitle());
            view.displayMessageln("내용 : "+post.getTitle());
        }
    }

    private void createPost() {
        view.displayMessage("제목: ");
        String title = scanner.nextLine();
        view.displayMessage("내용: ");
        String content = scanner.nextLine();

        postService.createPost(title, content);
        view.displayMessageln("게시글이 작성되었습니다");
    }

    private void viewPost() {

        Post post = postService.getPost();
        if(post == null) {
            view.displayMessageln("게시글이 없습니다.");
            return;
        }
        view.displayMessageln("제목: "+post.getTitle());
        view.displayMessageln("내용: "+post.getContent());
    }

    private void deletePost() {
//        view.displayMessage("어떤 게시물을 삭제할까요?: ");
//        String cmd = scanner.nextLine();
        try {
            // int id = Integer.parseInt(cmd);
            int id = postService.getPost().getId();
            postService.deletePost(id);
            view.displayMessageln("게시글 " + id + " 삭제되었습니다");
        } catch (NumberFormatException e) {
            //Todo
        }
    }

    private void updatePost() {
        // view.displayMessage("어떤 게시물을 수정할까요?: ");
        // String cmd = scanner.nextLine();
        try {
//            int id = Integer.parseInt(cmd);
//            view.displayMessageln("게시글 " + id + "를 수정합니다");
            int id = postService.getPost().getId();
            view.displayMessage("제목: ");
            String title = scanner.nextLine();
            view.displayMessage("내용: ");
            String content = scanner.nextLine();

            postService.updatePost(id, title, content);
            view.displayMessageln("게시글이 수정되었습니다");
        } catch (NumberFormatException e) {
            // Todo
        }
    }
}
