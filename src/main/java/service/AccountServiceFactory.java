package service;

import repository.AccountRepositoryFactory;

public class AccountServiceFactory {
    private static AccountService service;

    public static AccountService getAccountService() {
        if (service == null) {
            service = new AccountServiceImpl(AccountRepositoryFactory.getAccountRepository());
        }
        return service;
    }
}
