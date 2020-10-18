package service;

import exception.RepositoryInternalException;
import model.Account;
import repository.AccountRepository;
import repository.AccountRepositoryFactory;

public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean createAccount(String name, String secondName) {
        return repository.createAccount(name, secondName) != 0;
    }

    @Override
    public Account findAccountByName(String name) throws RepositoryInternalException {
        return repository.findAccountByName(name);
    }

    @Override
    public boolean updateAccountSecondName(String name, String secondName) {
        return repository.updateAccountSecondName(name, secondName) != 0;
    }
}
