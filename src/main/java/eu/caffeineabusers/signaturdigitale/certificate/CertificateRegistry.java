package eu.caffeineabusers.signaturdigitale.certificate;

import eu.caffeineabusers.signaturdigitale.utils.Registry;
import org.jetbrains.annotations.NotNull;

/**
 * This registry stores and handles all certificates. It is used to create/get/remove
 * certificates.
 *
 * @author Tomáš Plánský, Marek Charvát
 */
public class CertificateRegistry extends Registry<String, Certificate> {

    /**
     * Creates a new {@link Certificate} for the given subject with the given
     * public key. This method will also register the new certificate in this registry.
     *
     * @param name Name of the certificate.
     * @param subject The subject.
     * @param key The key.
     * @return The new certificate.
     */
    public Certificate createCertificate(@NotNull String name, @NotNull String key, @NotNull String subject) {
        Certificate certificate = new Certificate(name, subject, key, 0);
        this.register(certificate.name(), certificate);
        return certificate;
    }

}
