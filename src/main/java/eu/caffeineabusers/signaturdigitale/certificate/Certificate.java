package eu.caffeineabusers.signaturdigitale.certificate;

import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * This class represents a certificate. It stores some information about the certificate
 * and is able to verify a signature.
 *
 * @author Tomáš Plánský
 */
@ToString
public record Certificate(
        @NotNull UUID uid,
        @NotNull String key,
        @NotNull UUID subjectId,
        long expiry
) {

}
