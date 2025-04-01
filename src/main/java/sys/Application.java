package sys;

import controller.BoardController;
import controller.Controller;
import controller.PostController;
import repository.BoardRepository;
import repository.PostRepository;
import service.BoardService;
import service.PostService;
import util.UrlParser;
import view.ConsoleView;

import java.util.Scanner;

public class Application {
    // View
    private final ConsoleView view = new ConsoleView();

    // Scanner
    private final Scanner sc = new Scanner(System.in);

    //Repository
    private final BoardRepository boardRepository = new BoardRepository();
    private final PostRepository postRepository = new PostRepository();
    // Services
    private final PostService postService = new PostService(postRepository);
    private final BoardService boardService = new BoardService(boardRepository);

    private boolean run = true;
    private String domain;

    public Application( String domain) {
        this.domain = domain;
    }

    public void Start() {
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
            Request request = UrlParser.parseUrl(cmd);

            String[] pathParts = request.getPathParts();

            if (pathParts.length < 2) {
                view.displayMessageln("잘못된 url 형식");
                return;
            }

            // 게시판, 게시글, 계정 선택 하는 컨트롤러
            Controller controller = getController(request);
            if (controller != null) {
                controller.handleRequest(request);
            } else{
                view.displayMessageln("잘못된 요청입니다.");
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
                // Todo
                break;
            default:
                view.displayMessageln("알 수 없는 기능 구분입니다: " + category);
                break;
        }
        return null;
    }
}

