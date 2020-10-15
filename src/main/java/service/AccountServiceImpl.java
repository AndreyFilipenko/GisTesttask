package service;

import repository.AccountRepository;
import repository.AccountRepositoryFactory;

public class AccountServiceImpl implements AccountService {

    @Override
    public boolean createAccount(String name, String secondName) {
        AccountRepository repository = AccountRepositoryFactory.getAccountRepository();
        int result = repository.createAccount(name, secondName);
        return result != 0;
    }
}
