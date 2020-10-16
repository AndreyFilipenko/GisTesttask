package service;

import exception.RepositoryInternalException;
import model.Account;

public interface AccountService {
    boolean createAccount(String name, String secondName);
    Account findAccountByName(String name) throws RepositoryInternalException;
    boolean updateAccountSecondName(String name, String secondName);
}
