package repository;

import model.Account;

import java.sql.SQLException;

public interface AccountRepository {
    int createAccount(String name, String secondName);
    Account findAccountByName(String name) throws SQLException;
    int updateAccountSecondName(String name, String secondName);
}
