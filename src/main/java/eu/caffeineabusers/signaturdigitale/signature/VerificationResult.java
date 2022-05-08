package eu.caffeineabusers.signaturdigitale.signature;

/**
 * This enum stores all possible outcomes of file signature verification.
 *
 * @author Tomáš Plánský, Marek Charvát
 */
public enum VerificationResult {
    VALID, INVALID, EXPIRED, NOT_SIGNED
}
