package service;

import model.Account;
import repository.AccountRepository;
import repository.AccountRepositoryFactory;

import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository = AccountRepositoryFactory.getAccountRepository();

    @Override
    public boolean createAccount(String name, String secondName) {
        int result = repository.createAccount(name, secondName);
        return result != 0;
    }

    @Override
    public Account findAccountByName(String name) throws SQLException {
        return repository.findAccountByName(name);
    }
}
