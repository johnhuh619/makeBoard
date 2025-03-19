import controller.CommandController;
import service.PostService;
import view.ConsoleView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        ConsoleView view = new ConsoleView();
        Scanner scanner = new Scanner(System.in);
        PostService postService = new PostService();
        CommandController controller = new CommandController(scanner, view, postService);
        controller.Start();
    }
}
