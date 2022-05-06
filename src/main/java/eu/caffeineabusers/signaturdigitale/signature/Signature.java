package eu.caffeineabusers.signaturdigitale.signature;

import eu.caffeineabusers.signaturdigitale.SignaturDigitale;
import eu.caffeineabusers.signaturdigitale.certificate.Certificate;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * This class represents a signature of a file. It contains information
 * about the signature including  of the certificate and the encrypted hash.
 *
 * @author Tomáš Plánský (tomas-plansky)
 */
@ToString
public record Signature(
        @NotNull String certificate,
        @NotNull String hash
) {

    /**
     * Get the {@link Certificate} of this signature.
     *
     * @return The certificate.
     */
    public Certificate getCertificate() {
        return SignaturDigitale.getInstance().getCertificateRegistry().get(certificate());
    }

}
