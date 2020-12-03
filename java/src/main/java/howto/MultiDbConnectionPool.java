package howto;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class MultiDbConnectionPool {
    private final static Map<String, DataSource> DB_POOLMAP = new ConcurrentHashMap<>(2);
    private static Properties prop;

    public static synchronized Connection getConnection(String dbName) throws Exception {
        if (!DB_POOLMAP.containsKey(dbName)) {
            createDataSource(dbName);
        }
        DataSource dataSource = DB_POOLMAP.get(dbName);
        return dataSource.getConnection();
    }

    private static void createDataSource(String dbName) throws Exception {
        System.out.println("Creting the dataspurce for " + dbName);
        HikariConfig hikariConfig = getHikariConfig(dbName);
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        System.out.println("Adding the datasource to the global map");
        DB_POOLMAP.put(dbName, hikariDataSource);
    }

    private static HikariConfig getHikariConfig(String dbName) throws Exception {
        Properties properties = getProperties();
        if (properties == null || properties.get(dbName + ".url") == null) {
            throw new Exception("Database not defined");
        }

        HikariConfig hikaConfig = new HikariConfig();

        // to reduce the code duplication, using Function to get the value
        Function<String, String> getValue = (key) -> properties.get(dbName + "." + key).toString();

        //This is same as passing the Connection info to the DriverManager class.
        //your jdbc url. in my case it is mysql.
        hikaConfig.setJdbcUrl(getValue.apply("url"));
        //username
        hikaConfig.setUsername(getValue.apply("user"));
        //password
        hikaConfig.setPassword(getValue.apply("password"));
        //driver class name
        hikaConfig.setDriverClassName(getValue.apply("driver"));

        // Information about the pool
        //pool name. This is optional you don't have to do it.
        hikaConfig.setPoolName(dbName);

        //the maximum connection which can be created by or resides in the pool
        hikaConfig.setMaximumPoolSize(Integer.parseInt(getValue.apply("poolsize")));

        //how much time a user can wait to get a connection from the pool.
        //if it exceeds the time limit then a SQlException is thrown
        hikaConfig.setConnectionTimeout(Duration.ofSeconds(30).toMillis());

        //The maximum time a connection can sit idle in the pool.
        // If it exceeds the time limit it is removed form the pool.
        // If you don't want to retire the connections simply put 0.
        hikaConfig.setIdleTimeout(Duration.ofMinutes(2).toMillis());

        return hikaConfig;
    }

    // read the properties file to get the credentials of databases
    private static Properties getProperties() {
        //return the existing properties if loaded earlier
        if (prop != null) {
            return prop;
        }

        System.out.println("Loading the configuration File");
        String propertiesFileName = "application.properties";

        try (InputStream istream = MultiDbConnectionPool.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            Properties properties = new Properties();
            properties.load(istream);
            // save to global prop obkect
            prop = properties;
            return properties;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
