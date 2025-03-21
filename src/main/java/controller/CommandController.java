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
            view.displayMessage("a ");
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

        //postService.createPost(title, content);
        view.displayMessageln("게시글이 작성되었습니다");
    }

    private void viewPost() {
        view.displayMessage("어떤 게시물을 조회할까요?");
        String cmd = scanner.nextLine();
        int id;
        try{
            id = Integer.parseInt(cmd);
        } catch(NumberFormatException e) {
            view.displayMessageln("올바른 번호를 입력해주세요");
            return;
        }
        Post post = postService.getPost(id);
        if(post == null) {
            view.displayMessageln(id+"번 게시글이 없습니다.");
            return;
        }
        view.displayMessageln(id+"번 게시물");
        view.displayMessageln("제목: "+post.getTitle());
        view.displayMessageln("내용: "+post.getContent());

    }

    private void deletePost() {
        view.displayMessage("어떤 게시물을 삭제할까요?: ");
        String cmd = scanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(cmd);
        } catch (NumberFormatException e) {
            view.displayMessageln("올바른 번호를 입력해주세요");
            return;
        }
        Post post = postService.getPost(id);
        if(post == null) {
            view.displayMessageln(id+"번 게시글이 없습니다.");
            return;
        }
        postService.deletePost(id);
        view.displayMessageln("게시글 " + id + " 삭제되었습니다");
    }

    private void updatePost() {
        view.displayMessage("어떤 게시물을 수정할까요?: ");
        String cmd = scanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(cmd);
        } catch (NumberFormatException e) {
            view.displayMessageln("올바른 번호를 입력해주세요.");
            return;
        }
        view.displayMessageln("게시글 " + id + "를 수정합니다");
        view.displayMessage("제목: ");
        String title = scanner.nextLine();
        view.displayMessage("내용: ");
        String content = scanner.nextLine();

        postService.updatePost(id, title, content);
        view.displayMessageln(id + "번 게시물이 성공적으로 수정되었습니다.");

    }
}
