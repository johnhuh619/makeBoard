package controller;

import request.Request;
import service.PostService;
import util.UrlParser;
import view.ConsoleView;

import java.util.Scanner;

public class UrlCommandController {
    private final Scanner scanner;
    private final ConsoleView view;
    private final PostService postService;

    public UrlCommandController(Scanner scanner, ConsoleView view, PostService postService) {
        this.scanner = scanner;
        this.view = view;
        this.postService = postService;
    }

    public void Start() {
        boolean run = true;
        view.displayMessageln("Get Started...");
        while (run) {
            view.displayMessage("a ");
            String cmd = scanner.nextLine();
            if(cmd.equalsIgnoreCase("exit")) {
                view.displayMessageln("Shutting down...");
                run = false;
                continue;
            }
            Request request = UrlParser.parseUrl(cmd);
            String path = request.getPath();
            String[] pathParts = path.split("/");
            if(pathParts.length <2) {
                view.displayMessageln("잘못된 url 형식");
                return;
            }
            String category = pathParts[1];
            String act = pathParts[2];

            switch (category) {
                case "boards":
                    // Todo
                    break;
                case "posts":
                    // Todo
                    break;
                case "accounts":
                    // Todo
                    break;
                default:
                    view.displayMessageln("알 수 없는 기능 구분입니다: " + category);
                    break;
            }
        }
    }
}
