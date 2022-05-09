package eu.caffeineabusers.signaturdigitale.database;

import eu.caffeineabusers.signaturdigitale.certificate.Certificate;
import eu.caffeineabusers.signaturdigitale.database.impl.MySQL;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * This manager is responsible for creating and managing the database connection. It is also responsible for
 * executing queries. The manager is thread-safe.
 *
 * @author Tomáš Plánský, Marek Charvát
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
    public void executeAsync(@NotNull String query) {
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
     * Execute a query asynchronously. The result of the query is passed to the given consumer.
     *
     * @param query The query to execute.
     * @param callback The callback to execute when the query is finished.
     */
    public void executeQueryAsync(@NotNull String query, @NotNull Consumer<ResultSet> callback) {
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
        executeAsync(
                "CREATE TABLE IF NOT EXISTS `certificates` (" +
                        "`id` VARCHAR(36) NOT NULL," +
                        "`subject_id` VARCHAR(36) NOT NULL," +
                        "`key` VARCHAR(255) NOT NULL," +
                        "`expiry` LONG NOT NULL," +
                        "PRIMARY KEY (`id`));"
        );
    }

    /**
     * Loads all certificates from the database and executes the given Callback.
     *
     * @param callback The action to execute when the ResultSet is ready.
     */
    public void loadCertificates(@NotNull Consumer<ResultSet> callback) {
        executeQueryAsync("SELECT * FROM `certificates`", callback);
    }

    /**
     * Save the given {@link Certificate} into the database. If a certificate with the same
     * id is already present in the database, its values will be updated.
     *
     * @param certificate The certificate.
     */
    public void saveCertificate(@NotNull Certificate certificate) {
        CompletableFuture.runAsync(() -> {
            try (Connection connection = getMySQL().getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO `certificates`(`id`, `subject_id`, `key`, `expiry`) VALUES (?,?,?,?)" +
                                 "ON DUPLICATE KEY UPDATE `id`=?, `subject_id`=?, `key`=?, `expiry`=?"
                 )
            ) {
                statement.setString(1, certificate.name());
                statement.setString(2, certificate.subjectName());
                statement.setString(3, certificate.key());
                statement.setLong(4, certificate.expiry());
                statement.setString(5, certificate.name());
                statement.setString(6, certificate.subjectName());
                statement.setString(7, certificate.key());
                statement.setLong(8, certificate.expiry());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }




}
