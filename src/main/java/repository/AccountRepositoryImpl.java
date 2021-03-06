package repository;

import exception.RepositoryInternalException;
import model.Account;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nullable;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AccountRepositoryImpl implements AccountRepository {
    private static final Logger logger = LogManager.getLogger(AccountRepositoryImpl.class);
    private final DataSource dataSource;

    public static final String SQL_INSERT_ACCOUNT = "insert into accounts(name, second_name) values (?, ?)";

    public static final String SQL_SELECT_ACCOUNT_BY_NAME = "select name, second_name from accounts where name = ?";

    public static final String SQL_UPDATE_ACCOUNT_SECOND_NAME = "update accounts set second_name = ? where name = ?";

    public AccountRepositoryImpl(DataSource dataSource) {
        this.dataSource = Objects.requireNonNull(dataSource, "Data source can't be null");
    }

    @Override
    public int createAccount(String name, String secondName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ACCOUNT)) {
            statement.setString(1, name);
            statement.setString(2, secondName);
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.warn("Invoke createAccount({}, {}). Account create failed, no rows added", name, secondName);
            }
            return result;
        } catch (SQLException ex) {
            logger.error("Invoke createAccount({}, {}) with exception.)", name, secondName, ex);
        }
        return 0;
    }

    @Nullable
    @Override
    public Account findAccountByName(String name) throws RepositoryInternalException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ACCOUNT_BY_NAME)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Account(resultSet.getString("name"), resultSet.getString("second_name"));
                } else {
                    logger.warn("Invoke findAccountByName({}). Account not found.", name);
                }
            }
        } catch (SQLException ex) {
            logger.error("Invoke findAccountByName({}) with exception.)", name, ex);
            throw new RepositoryInternalException("SQL query execution threw an SQLException");
        }
        return null;
    }

    @Override
    public int updateAccountSecondName(String name, String secondName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ACCOUNT_SECOND_NAME)) {
            statement.setString(2, name);
            statement.setString(1, secondName);
            int result = statement.executeUpdate();
            if (result == 0) {
                logger.warn("Invoke updateAccountSecondName({}, {}). Account update failed, no rows changed", name, secondName);
            }
            return result;
        } catch (SQLException ex) {
            logger.error("Invoke updateAccountSecondName({}, {}) with exception.)", name, secondName, ex);
        }
        return 0;
    }
}
