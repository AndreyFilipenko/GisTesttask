package repository;

import model.Account;

import java.sql.SQLException;

public interface AccountRepository {
    int createAccount(String name, String lastName);
    Account findAccountByName(String name) throws SQLException;
}
