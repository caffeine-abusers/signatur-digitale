package eu.caffeineabusers.signaturdigitale.signature;

import lombok.ToString;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a signature of a file. It contains information
 * about the signature including id of the certificate and the encrypted hash.
 *
 * @author Tomáš Plánský (tomas-plansky)
 */
@ToString
public record Signature(
        @NotNull String id,
        @NotNull String hash
) {

}
