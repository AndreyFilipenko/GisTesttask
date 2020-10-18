package configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.ds.PGSimpleDataSource;

import javax.annotation.Nullable;
import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;

public final class AccountDataSourceFactory {
    private static final Logger logger = LogManager.getLogger(AccountDataSourceFactory.class);

    private static final String PROPS_FILE_NAME = "db.properties";

    private static PGSimpleDataSource pgDataSource;

    private AccountDataSourceFactory() {
    }

    @Nullable
    public static DataSource getPostgreSQLDataSource() {

        if (pgDataSource == null) {
            Properties props = new Properties();

            try (InputStream input = AccountDataSourceFactory.class.getClassLoader().getResourceAsStream(PROPS_FILE_NAME)) {
                if (input == null) {
                    throw new IOException("Unable to find " + PROPS_FILE_NAME);
                }
                props.load(input);
                pgDataSource = new PGSimpleDataSource();
                pgDataSource.setURL(props.getProperty("PGSQL_DB_URL"));
                pgDataSource.setUser(props.getProperty("PGSQL_DB_LOGIN"));
                pgDataSource.setPassword(props.getProperty("PGSQL_DB_PASSWORD"));
            } catch (IOException ex) {
                logger.error("Invoke getPostgreSQLDataSource() with exception.", ex);
            }
        }

        return pgDataSource;
    }
}
