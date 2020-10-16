package repository;

import model.Account;
import org.apache.log4j.Logger;
import configuration.AccountDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountRepositoryImpl implements AccountRepository {
    private static final Logger logger = Logger.getLogger(AccountRepositoryImpl.class);
    private final DataSource dataSource = AccountDataSourceFactory.getPostgreSQLDataSource();

    private static final String SQL_INSERT_ACCOUNT = "insert into accounts(name, second_name) values ('%s', '%s');";
    private static final String SQL_SELECT_ACCOUNT_BY_NAME = "select name, second_name from accounts where name = '%s';";

    @Override
    public int createAccount(String name, String lastName) {
        String query = String.format(SQL_INSERT_ACCOUNT, name, lastName);

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate(query);
            if (result==0) {
                throw new SQLException("Account create failed, no rows added");
            } else {
                return result;
            }
        } catch (SQLException ex) {
            logger.error(String.format("Invoke createAccount(%s, %s) with exception.)", name, lastName), ex);
        }
        return 0;
    }

    @Override
    public Account findAccountByName(String name) throws SQLException {
        String query = String.format(SQL_SELECT_ACCOUNT_BY_NAME, name);
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return new Account(resultSet.getString("name"), resultSet.getString("second_name"));
            }
        }
        catch (SQLException ex) {
            logger.error(String.format("Invoke findAccountByName(%s) with exception.)", name), ex);
            throw ex;
        }
        return null;
    }
}
