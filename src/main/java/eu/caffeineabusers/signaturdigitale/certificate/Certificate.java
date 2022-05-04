package eu.caffeineabusers.signaturdigitale.certificate;

import java.util.UUID;

/**
 * This class represents a certificate. It stores some information about the certificate
 * and is able to verify a signature.
 *
 * @author Tomáš Plánský
 */
public record Certificate(UUID uid, String key, UUID subjectId, long expiry) {



}
