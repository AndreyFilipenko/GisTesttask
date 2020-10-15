package repository;

public class AccountRepositoryFactory {
    private static AccountRepository repository;

    public static AccountRepository getAccountRepository() {
        if (repository == null) {
            repository = new AccountRepositoryImpl();
        }
        return repository;
    }

}
