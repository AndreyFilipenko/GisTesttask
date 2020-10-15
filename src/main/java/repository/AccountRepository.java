package repository;

import model.AccountModel;

public interface AccountRepository {
    int createAccount(String name, String lastName);
}
