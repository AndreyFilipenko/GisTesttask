package repository;

import org.apache.log4j.Logger;
import configuration.AccountDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountRepositoryImpl implements AccountRepository {
    private static final Logger logger = Logger.getLogger(AccountRepositoryImpl.class);

    private static final String SQL_INSERT_ACCOUNT = "insert into accounts(name, second_name) values ('%s', '%s');";

    @Override
    public int createAccount(String name, String lastName) {
        DataSource dataSource = AccountDataSourceFactory.getPostgreSQLDataSource();
        String completedQuery = String.format(SQL_INSERT_ACCOUNT, name, lastName);

        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate(completedQuery);
            if (result==0) {
                throw new SQLException("Account create failed, no rows added");
            } else {
                return result;
            }
        } catch (SQLException ex) {
            logger.error(String.format("Invoke createAccount(%s, %s with exception.)", name, lastName), ex);
        }
        return 0;
    }
}
