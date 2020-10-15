package service;

public class AccountServiceFactory {
    private static AccountService service;

    public static AccountService getAccountService() {
        if (service == null) {
            service = new AccountServiceImpl();
        }
        return service;
    }
}
