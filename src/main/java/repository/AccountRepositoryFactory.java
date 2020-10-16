package repository;

import configuration.AccountDataSourceFactory;

public class AccountRepositoryFactory {
    private static AccountRepository repository;

    public static AccountRepository getAccountRepository() {
        if (repository == null) {
            repository = new AccountRepositoryImpl(AccountDataSourceFactory.getPostgreSQLDataSource());
        }
        return repository;
    }

}
