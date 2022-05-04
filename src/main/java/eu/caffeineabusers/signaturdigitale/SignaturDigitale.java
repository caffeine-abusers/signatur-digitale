package eu.caffeineabusers.signaturdigitale;

import eu.caffeineabusers.signaturdigitale.database.DatabaseManager;
import eu.caffeineabusers.signaturdigitale.utils.file.FileUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * SignaturDigitale - A simple Java application that is able to sign a file
 * with a private key and verify the signature with the public key. The
 * application is able to sign and verify files of any size.
 *
 * <b>Signing:</b>
 * <ul>
 *     <li>Hash text file</li>
 *     <li>Encrypt hash with key</li>
 *     <li>Add the signature (certificateId, hash) into the file</li>
 * </ul>
 *
 * <b>Verifying:</b>
 * <ul>
 *     <li>Read signature (certificateId, hash) from the file</li>
 *     <li>Decrypt hash with key</li>
 *     <li>Compare hash with the hash of the file</li>
 *     <li>If the hashes are equal, the signature is valid</li>
 * </ul>
 *
 * @author Marek Charvát, Tomáš Plánský
 * @version 1.0.0
 */
@Getter
public final class SignaturDigitale {

    private static SignaturDigitale instance;
    private final DatabaseManager databaseManager;

    /**
     * Creates a new instance of {@link SignaturDigitale}. This constructor starts
     * the whole application.
     */
    public SignaturDigitale() {
        instance = this;
        databaseManager = new DatabaseManager();
    }

    /**
     * Signs the file with the private key.
     *
     * @param file The file to sign.
     * @param key The private key.
     */
    public void sign(@NotNull String file, @NotNull String key) {
        try {
            sign(new File(file), key);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Signs the file with the private key.
     *
     * @param file The file to sign.
     * @param key The private key.
     */
    public void sign(@NotNull File file, @NotNull String key) throws Exception {
        String content = FileUtils.readFile(file);

    }

    /**
     * Verifies the signature of the file with the public key.
     *
     * @param file The file to verify.
     * @param key The public key.
     */
    public void verify(@NotNull String file, @NotNull String key) {
        try {
            verify(new File(file), key);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Verifies the signature of the file with the public key.
     *
     * @param file The file to verify.
     * @param key The public key.
     */
    public void verify(@NotNull File file, @NotNull String key) throws Exception {
        String content = FileUtils.readFile(file);

    }

    /**
     * Get the instance of SignaturDigitale.
     *
     * @return The instance of SignaturDigitale.
     */
    public static SignaturDigitale getInstance() {
        return instance;
    }


}
