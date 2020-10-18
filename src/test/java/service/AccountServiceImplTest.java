package service;

import exception.RepositoryInternalException;
import model.Account;
import org.junit.Test;
import org.mockito.Mockito;
import repository.AccountRepositoryImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AccountServiceImplTest {


    @Test
    public void createAccountReturnTrue() {
        AccountRepositoryImpl repository = mock(AccountRepositoryImpl.class);
        Mockito.when(repository.createAccount("nameTrue","secondNameTrue")).thenReturn(1);

        AccountServiceImpl accountService = new AccountServiceImpl(repository);
        assertTrue(accountService.createAccount("nameTrue", "secondNameTrue"));
    }

    @Test
    public void createAccountReturnFalse() {
        AccountRepositoryImpl repository = mock(AccountRepositoryImpl.class);
        Mockito.when(repository.createAccount("nameFalse","secondNameFalse")).thenReturn(0);

        AccountServiceImpl accountService = new AccountServiceImpl(repository);
        assertFalse(accountService.createAccount("nameFalse","secondNameFalse"));
    }

    @Test(expected = RepositoryInternalException.class)
    public void findAccountByNameReturnTrue() throws RepositoryInternalException {
        AccountRepositoryImpl repository = mock(AccountRepositoryImpl.class);
        Mockito.when(repository.findAccountByName("nameTrue")).thenReturn(new Account("nameTrue","secondNameTrue"));

        AccountServiceImpl accountService = new AccountServiceImpl(repository);
        assertEquals(new Account("nameTrue","secondNameTrue"), accountService.findAccountByName("nameTrue"));
    }

    @Test
    public void findAccountByNameReturnNull() throws RepositoryInternalException {
        AccountRepositoryImpl repository = mock(AccountRepositoryImpl.class);
        Mockito.when(repository.findAccountByName("nameNull")).thenReturn(null);

        AccountServiceImpl accountService = new AccountServiceImpl(repository);
        assertNull(accountService.findAccountByName("nameNull"));
    }

    @Test(expected = RepositoryInternalException.class)
    public void findAccountByNameThrowsException() throws RepositoryInternalException {
        AccountRepositoryImpl repository = mock(AccountRepositoryImpl.class);
        Mockito.when(repository.findAccountByName("nameException")).thenThrow(new RepositoryInternalException("message"));

        AccountServiceImpl accountService = new AccountServiceImpl(repository);
        accountService.findAccountByName("nameException");
    }

    @Test
    public void updateAccountSecondNameReturnTrue() {
        AccountRepositoryImpl repository = mock(AccountRepositoryImpl.class);
        Mockito.when(repository.updateAccountSecondName("nameTrue","secondNameTrue")).thenReturn(1);

        AccountServiceImpl accountService = new AccountServiceImpl(repository);
        assertTrue(accountService.updateAccountSecondName("nameTrue", "secondNameTrue"));
    }

    @Test
    public void updateAccountSecondNameReturnFalse() {
        AccountRepositoryImpl repository = mock(AccountRepositoryImpl.class);
        Mockito.when(repository.updateAccountSecondName("nameFalse","secondNameFalse")).thenReturn(0);

        AccountServiceImpl accountService = new AccountServiceImpl(repository);
        assertFalse(accountService.updateAccountSecondName("nameFalse","secondNameFalse"));
    }
}