package configuration;

import org.apache.log4j.Logger;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;

public class AccountDataSourceFactory {
    private static final Logger logger = Logger.getLogger(AccountDataSourceFactory.class);

    private static final String PROPS_FILE_NAME = "db.properties";


    public static DataSource getPostgreSQLDataSource() {


        Properties props = new Properties();
        PGSimpleDataSource source = null;

        try (InputStream input = AccountDataSourceFactory.class.getClassLoader().getResourceAsStream(PROPS_FILE_NAME) ) {
            if (input == null) {
                throw new IOException("Unable to find "+ PROPS_FILE_NAME);
            }
            props.load(input);
            source = new PGSimpleDataSource();
            source.setURL(props.getProperty("PGSQL_DB_URL"));
            source.setUser(props.getProperty("PGSQL_DB_LOGIN"));
            source.setPassword(props.getProperty("PGSQL_DB_PASSWORD"));
        } catch (IOException ex) {
            logger.error("Invoke getPostgreSQLDataSource() with exception.", ex);
        }

        return source;
    }
}
