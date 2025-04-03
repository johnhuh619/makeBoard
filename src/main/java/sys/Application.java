package sys;

import controller.AccountController;
import controller.BoardController;
import controller.Controller;
import controller.PostController;
import model.Session;
import repository.AccountRepository;
import repository.BoardRepository;
import repository.PostRepository;
import service.AccountService;
import service.BoardService;
import service.PostService;
import util.UrlParser;
import view.ConsoleView;

import java.util.Scanner;
import java.util.Set;

public class Application {
    // View
    private final ConsoleView view = new ConsoleView();

    // Scanner
    private final Scanner sc = new Scanner(System.in);

    private final Session session = new Session();

    //Repository
    private final BoardRepository boardRepository = new BoardRepository();
    private final PostRepository postRepository = new PostRepository();
    private final AccountRepository accountRepository = new AccountRepository();

    // Services
    private final PostService postService = new PostService(postRepository);
    private final BoardService boardService = new BoardService(boardRepository);
    private final AccountService accountService = new AccountService(accountRepository);

    private boolean run = true;
    private String domain;

    Set<String> publicCommands = Set.of("signUp", "signIn", "help");

    public Application(String domain) {
        this.domain = domain;
    }

    public void start() {
        view.displayMessageln("Get Started...");
        while (run) {
            String url = "https://" + domain;

            view.displayMessage(url);
            String cmd = sc.nextLine().trim();

            if (cmd.equalsIgnoreCase("exit")) {
                view.displayMessageln("Shutting down...");
                run = false;
                continue;
            }
            Request request;
            try {
                request = UrlParser.parseUrl(cmd, session);



                if (request == null) {
                    throw new IllegalArgumentException("요청이 비어있습니다.");
                }

                String[] pathParts = request.getPathParts();
                if (pathParts.length < 2) {
                    throw new IllegalArgumentException("패스 길이가 짧습니다.");
                }

                // 비로그인 시 접근 가능한 기능인지 검사
                if ( !publicCommands.contains(pathParts[2]) && session.getLoggedInAccount() == null) {
                    throw new IllegalArgumentException("권한이 없습니다.");
                }

                // 게시판, 게시글, 계정 선택 하는 컨트롤러
                Controller controller = getController(request);
                if (controller != null) {
                    controller.handleRequest(request);
                } else {
                    throw new IllegalArgumentException("컨트롤러에 이상이 있습니다.");
                }

            } catch (IllegalArgumentException e) {
                view.displayMessageln("[400] " + e.getMessage());
            }
        }
    }

    private Controller getController(Request request) {
        String category = request.getPathParts()[1];
        switch (category) {
            case "boards":
                BoardController boardController = new BoardController(sc, view, postService, boardService);
                return boardController;
            case "posts":
                PostController postController = new PostController(sc, view, postService, boardService);
                return postController;
            case "accounts":
                AccountController accountController = new AccountController(sc, view, accountService);
                return accountController;
            default:
                view.displayMessageln("알 수 없는 기능 구분입니다: " + category);
                break;
        }
        return null;
    }
}

