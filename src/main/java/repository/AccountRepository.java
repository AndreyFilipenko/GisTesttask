package repository;

import exception.RepositoryInternalException;
import model.Account;

public interface AccountRepository {

    int createAccount(String name, String secondName);

    Account findAccountByName(String name) throws RepositoryInternalException;

    int updateAccountSecondName(String name, String secondName);
}
