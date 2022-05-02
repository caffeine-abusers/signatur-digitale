package eu.caffeineabusers.signaturdigitale.certificate;

import eu.caffeineabusers.signaturdigitale.signature.Signature;

import java.util.UUID;

/**
 * This class represents a certificate. It stores some information about the certificate
 * and is able to verify a signature.
 *
 * @author Tomáš Plánský
 */
public record Certificate(UUID uid, String name, long expiry) {

    /**
     * Verifies the given signature.
     *
     * @param signature The signature to verify.
     * @see VerificationResult
     * @return Result of the verification.
     */
    public VerificationResult verify(Signature signature) {
        if (isExpired()) {
            return VerificationResult.EXPIRED;
        }
        // TODO: Implement verification
        return VerificationResult.INVALID;
    }

    /**
     * Checks if the certificate is expired.
     *
     * @return True if the certificate is expired, false otherwise.
     */
    public boolean isExpired() {
        return expiry < System.currentTimeMillis();
    }

    /**
     * All possible results of a signature verification.
     *
     * @author GitHub Copilot, Tomáš Plánský
     */
    public enum VerificationResult {
        VALID,
        EXPIRED,
        INVALID
    }

}
