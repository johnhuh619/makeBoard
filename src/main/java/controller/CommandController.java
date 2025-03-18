package controller;

import view.ConsoleView;

import java.util.Scanner;

public class CommandController {
    private final Scanner scanner;
    private final ConsoleView view;

    public CommandController(Scanner scanner, ConsoleView view) {
        this.scanner = scanner;
        this.view = view;
    }


    public void Start() {
        boolean run = true;
        view.displayCommand("Get Started...");
        while (run) {
            view.displayDefault("command > ");
            String command = scanner.nextLine();
            if(command.equalsIgnoreCase("exit")) {
                view.displayCommand("Shutting down...");
                run = false;
                continue;
            }
            switch (command) {
                case "작성":
                    // TODO
                    System.out.println("작성");
                    break;
                case "조회":
                    // TODO
                    System.out.println("조회");
                    break;
                case "삭제":
                    //TODO
                    System.out.println("삭제");
                    break;
                case "수정":
                    System.out.println("수정");
                    // TODO
                    break;
                default:
                    view.displayCommand("존재하지 않는 명령어 입니다.");
                    break;
            }
        }
        scanner.close();
    }
}
