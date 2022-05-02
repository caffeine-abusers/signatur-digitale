package eu.caffeineabusers.signaturdigitale.database;

import eu.caffeineabusers.signaturdigitale.database.impl.MySQL;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * This manager is responsible for creating and managing the database connection. It is also responsible for
 * executing queries. The manager is thread-safe.
 *
 * @author Tomáš Plánský
 */
public class DatabaseManager {

    private final MySQL mySQL;

    /**
     * Creates a new instance of {@link DatabaseManager}. This constructor also
     * initializes the {@link MySQL} class.
     */
    public DatabaseManager() {
        mySQL = new MySQL(
                "localhost",
                "3306",
                "signaturdigitale",
                "root",
                "root"
        );
    }

    /**
     * Get the instance of the {@link MySQL} class.
     *
     * @return The instance of the {@link MySQL} class.
     */
    public MySQL getMySQL() {
        return mySQL;
    }

    /**
     * Shutdown the database connection.
     */
    public void shutdown() {
        mySQL.shutdown();
    }

    // -- Queries -- //

    /**
     * Execute a query asynchronously.
     *
     * @param query The query to execute.
     */
    public void executeQueryAsync(@NotNull String query) {
        CompletableFuture.runAsync(() -> {
            try (Connection connection = mySQL.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {
                preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Execute a select query asynchronously. The result of the query is passed to the given consumer.
     *
     * @param query The query to execute.
     * @param callback The callback to execute when the query is finished.
     */
    public void executeSelectAsync(@NotNull String query, @NotNull Consumer<ResultSet> callback) {
        CompletableFuture.runAsync(() -> {
            try (Connection connection = mySQL.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {
                ResultSet resultSet = preparedStatement.executeQuery();
                callback.accept(resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Prepare all the tables in the database.
     */
    public void prepareTablesAsync() {
        executeQueryAsync("CREATE TABLE IF NOT EXISTS `users` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`username` VARCHAR(255) NOT NULL," +
                "`password` VARCHAR(255) NOT NULL," +
                "`email` VARCHAR(255) NOT NULL," +
                "`first_name` VARCHAR(255) NOT NULL," +
                "`last_name` VARCHAR(255) NOT NULL," +
                "`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP," +
                "PRIMARY KEY (`id`)," +
                "UNIQUE INDEX `username_UNIQUE` (`username` ASC)," +
                "UNIQUE INDEX `email_UNIQUE` (`email` ASC)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;"
        );
    }





}
