package eu.caffeineabusers.signaturdigitale.certificate;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CertificateManager {

    private final Map<UUID, Certificate> certificateMap;

    public CertificateManager() {
        this.certificateMap = new HashMap<>();
    }

    public void registerCertificate(@NotNull Certificate certificate) {
        this.certificateMap.put(certificate.uid(), certificate);
    }

    public Optional<Certificate> getCertificate(@NotNull UUID uid) {
        return Optional.ofNullable(certificateMap.get(uid));
    }

}
