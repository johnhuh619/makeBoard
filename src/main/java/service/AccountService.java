package service;

import model.Account;
import repository.AccountRepository;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public int signUp(String name, String pwd, String email) {
        if(accountRepository.findByUsername(name) != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다");
        }
        Account acc = new Account(0, name, pwd, email, java.time.LocalDateTime.now(), java.time.LocalDateTime.now());
        return accountRepository.save(acc);
    }

    public Account signIn(String name, String pwd) {
        Account acc = accountRepository.findByUsername(name);
        if(acc == null || !acc.getPassword().equals(pwd)) {
            throw new IllegalArgumentException("로그인 실패");
        }
        return acc;
    }

    public Account getAccount(int id) {
        return accountRepository.findById(id);
    }

    public Account updateAccount(int id, String newPassword, String newEmail){
        Account acc = accountRepository.findById(id);
        if(acc == null) {
            throw new IllegalArgumentException("계정이 존재하지 않습니다.");
        }
        acc.setPassword(newPassword);
        acc.setEmail(newEmail);
        acc.setUpdatedDate(java.time.LocalDateTime.now());
        return acc;
    }

    public boolean deleteAccount(int id){
        return accountRepository.delete(id);
    }
}
