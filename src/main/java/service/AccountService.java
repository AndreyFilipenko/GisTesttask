package service;

import model.Account;

import java.sql.SQLException;

public interface AccountService {
    boolean createAccount(String name, String secondName);
    Account findAccountByName(String name) throws SQLException;
}
