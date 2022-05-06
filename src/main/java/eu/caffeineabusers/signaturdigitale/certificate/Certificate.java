package eu.caffeineabusers.signaturdigitale.certificate;

import lombok.ToString;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a certificate. It stores some information about the certificate
 * and is able to verify a signature.
 *
 * @author Tomáš Plánský
 */
@ToString
public record Certificate(
        @NotNull String name,
        @NotNull String subjectName,
        @NotNull String key,
        long expiry
) {

    /**
     * Checks if the certificate is expired.
     *
     * @return True if the certificate is expired, false otherwise.
     */
    public boolean isExpired() {
        return expiry < System.currentTimeMillis();
    }

}
