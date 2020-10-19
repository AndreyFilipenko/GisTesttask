package repository;

import exception.RepositoryInternalException;
import model.Account;
import org.junit.Test;

import javax.sql.DataSource;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountRepositoryImplTest {

    @Test
    public void createAccount() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(AccountRepositoryImpl.SQL_INSERT_ACCOUNT)).thenReturn(statement);

        when(statement.executeUpdate()).thenReturn(1);
        AccountRepositoryImpl accountRepository = new AccountRepositoryImpl(dataSource);
        assertEquals(1, accountRepository.createAccount("name", "secondName"));

        verify(statement, times(1)).setString(1, "name");
        verify(statement, times(1)).setString(2, "secondName");

        when(statement.executeUpdate()).thenReturn(0);
        accountRepository = new AccountRepositoryImpl(dataSource);
        assertEquals(0, accountRepository.createAccount("name", "secondName"));
    }

    @Test
    public void findAccountByName() throws SQLException, RepositoryInternalException {
        DataSource dataSource = mock(DataSource.class);
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(AccountRepositoryImpl.SQL_SELECT_ACCOUNT_BY_NAME)).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("name")).thenReturn("name");
        when(resultSet.getString("second_name")).thenReturn("secondName");
        Account account = new Account("name", "secondName");


        AccountRepositoryImpl accountRepository = new AccountRepositoryImpl(dataSource);
        assertEquals(account, accountRepository.findAccountByName("name"));

        verify(statement, times(1)).setString(1, "name");

        when(resultSet.next()).thenReturn(false);
        accountRepository = new AccountRepositoryImpl(dataSource);
        assertNull(accountRepository.findAccountByName("name"));
    }

    @Test
    public void updateAccountSecondName() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(AccountRepositoryImpl.SQL_UPDATE_ACCOUNT_SECOND_NAME)).thenReturn(statement);

        when(statement.executeUpdate()).thenReturn(1);
        AccountRepositoryImpl accountRepository = new AccountRepositoryImpl(dataSource);
        assertEquals(1, accountRepository.updateAccountSecondName("name", "secondName"));

        verify(statement, times(1)).setString(2, "name");
        verify(statement, times(1)).setString(1, "secondName");

        when(statement.executeUpdate()).thenReturn(0);
        accountRepository = new AccountRepositoryImpl(dataSource);
        assertEquals(0, accountRepository.updateAccountSecondName("name", "secondName"));
    }
}