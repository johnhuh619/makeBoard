package controller;


import model.Account;
import service.AccountService;
import sys.Request;
import view.ConsoleView;

import java.io.Console;
import java.util.Map;
import java.util.Scanner;

public class AccountController implements Controller {
    private final Scanner sc;
    private final ConsoleView view;
    private final AccountService service;

    public AccountController(Scanner sc, ConsoleView view, AccountService service) {
        this.sc = sc;
        this.view = view;
        this.service = service;
    }

    @Override
    public void handleRequest(Request request) {
        String[] pathParts = request.getPathParts();
        String act = pathParts[2];
        Map<String, String> params = request.getParams();
        //Session session = request.getSession();
        try {
            switch (act) {
                case "signUp":
                    handleSignUp();
                    break;
                case "signIn":
                    handleSignIn();
                    break;
                case "signOut":
                    handleSignOut();
                    break;
                case "details":
                    handleDetails(params);
                    break;
                case "edit":
                    handleEdit(params);
                    break;
                case "remove":
                    handleRemove(params);
                    break;
                default:
                    view.displayMessageln("[400] 잘못된 요청입니다.");
                    break;
            }
        } catch (Exception e) {
            view.displayMessageln("[500]" + e.getMessage());
        }
    }

    private void handleRemove(Map<String, String> params) {
        if(!params.containsKey("accountId")) {
            throw new IllegalArgumentException("accountId 파라미터가 필요합니다.");
        }
        int accountId = Integer.parseInt(params.get("accountId"));
        boolean result = service.deleteAccount(accountId);
        if(result) {
            view.displayMessageln("계정이 삭제되었습니다.");
        } else {
            view.displayMessageln("계정 삭제에 실패했습니다.");
        }
    }

    private void handleEdit(Map<String, String> params) {
        if(!params.containsKey("accountId")) {
            throw new IllegalArgumentException("accountId 파라미터가 필요합니다.");
        }
        int accountId = Integer.parseInt(params.get("accountId"));
        view.displayMessage("새로운 비밀번호: ");
        String newPassword = sc.nextLine();
        view.displayMessage("새로운 이메일: ");
        String newEmail = sc.nextLine();
        Account updated = service.updateAccount(accountId, newPassword, newEmail);
        if(updated != null) {
            view.displayMessageln("계정 정보가 수정되었습니다.");
        } else {
            view.displayMessageln("계정 수정에 실패했습니다.");
        }
    }

    private void handleDetails(Map<String, String> params) {
        if(!params.containsKey("accountId")) {
            throw new IllegalArgumentException("accuntId 파라미터가 필요합니다.");
        }
        int accountId = Integer.parseInt(params.get("accountId"));
        Account account = service.getAccount(accountId);
        if(account == null) {
            view.displayMessageln("존재하지 않는 계정입니다");
        } else {
            view.displayMessageln("[" + account.getId() + "]번 회원");
            view.displayMessageln("계정: " + account.getUsername());
            view.displayMessageln("이메일: " + account.getEmail());
            view.displayMessageln("가입일: " + account.getJoinDate());
        }
    }

    private void handleSignOut() {

    }

    private void handleSignIn() {
        view.displayMessage("계정: ");
        String username = sc.nextLine();
        view.displayMessage("비밀번호: ");
        String password = sc.nextLine();
        Account account = service.signIn(username, password);
        view.displayMessageln("로그인 성공. 환영합니다, " + account.getUsername());
    }

    private void handleSignUp() {
        view.displayMessage("계정: ");
        String name = sc.nextLine();
        view.displayMessage("비밀번호: ");
        String password = sc.nextLine();
        view.displayMessage("이메일: ");
        String email = sc.nextLine();
        int id = service.signUp(name, password, email);
        view.displayMessageln("회원가입 성공. 계정 ID: " + id);
    }
}
