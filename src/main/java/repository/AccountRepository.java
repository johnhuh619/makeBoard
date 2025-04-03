package repository;


import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private List<Account> accounts = new ArrayList<>();

    public int save(Account account) {
        int id = accounts.size() + 1;
        account.setId(id);
        accounts.add(account);
        return id;
    }

    public List<Account> update(Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId() == account.getId()) {
                accounts.set(i, account);
                return accounts;
            }
        }
        return null;
    }

    public boolean delete(int accountId) {
        return accounts.removeIf(account -> account.getId() == accountId);
    }

    public Account findById(int accountId) {
        for(Account account : accounts) {
            if(account.getId() == accountId) {
                return account;
            }
        }
        return null;
    }

    public Account findByUsername(String username) {
        for(Account account : accounts) {
            if(account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> findAll() {
        return accounts;
    }

}
