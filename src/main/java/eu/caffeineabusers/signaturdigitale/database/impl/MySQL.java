package eu.caffeineabusers.signaturdigitale.database.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is a wrapper for the HikariCP library. It provides a connection pool for the database.
 *
 * @author Tomáš Plánský, Marek Charvát
 */
public class MySQL {

    private final HikariDataSource dataSource;

    /**
     * Creates a new instance of {@link MySQL}. This constructor also
     * initializes the connection pool.
     *
     * @param hostname The hostname of the database.
     * @param port The port of the database.
     * @param database The database name.
     * @param username The username.
     * @param password The password.
     */
    public MySQL(@NotNull String hostname,
                 @NotNull String port,
                 @NotNull String database,
                 @NotNull String username,
                 @NotNull String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + hostname + ":" + port + "/" + database);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(1);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);
        config.setMaxLifetime(30000);
        dataSource = new HikariDataSource(config);
    }

    /**
     * Shutdown the connection pool.
     */
    public void shutdown() {
        dataSource.close();
    }

    /**
     * Get the data source.
     *
     * @return The data source.
     */
    public HikariDataSource getDataSource() {
        return dataSource;
    }

    /**
     * Get connection from the pool.
     *
     * @return The connection.
     */
    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
