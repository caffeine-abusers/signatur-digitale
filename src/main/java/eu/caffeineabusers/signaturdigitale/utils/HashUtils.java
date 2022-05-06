package eu.caffeineabusers.signaturdigitale.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class with some methods related to hashing.
 *
 * @author Tomáš Plánský
 */
@UtilityClass
public final class HashUtils {

    /**
     * Hashes the given string using MD_5.
     *
     * @param string The string to hash.
     * @return The hashed string.
     * @throws RuntimeException If anything goes wrong.
     */
    @Contract("_ -> new")
    public static @NotNull String hash(@NotNull String string) throws RuntimeException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD_5");
            byte[] hashBytes = messageDigest.digest(string.getBytes(StandardCharsets.UTF_8));
            return new String(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
