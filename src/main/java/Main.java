import controller.CommandController;
import service.PostService;
import sys.Application;
import view.ConsoleView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        Application app = new Application("github.com");
        app.Start();
    }
}
