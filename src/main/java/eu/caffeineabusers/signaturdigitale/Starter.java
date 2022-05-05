package eu.caffeineabusers.signaturdigitale;

import eu.caffeineabusers.signaturdigitale.certificate.Certificate;

import java.util.Optional;
import java.util.UUID;

/**
 * This class is responsible for starting the application.
 *
 * @author Tomáš Plánský
 */
public final class Starter {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java SignaturDigitale <sign|verify> <file> <certificateId>");
            System.exit(1);
        }

        String action = args[0];
        String file = args[1];
        String certificateId = args[2];
        UUID certificateUid = UUID.fromString(certificateId);

        SignaturDigitale signaturDigitale = new SignaturDigitale();
        Optional<Certificate> certificate = signaturDigitale.getCertificateManager().getCertificate(certificateUid);
        if (certificate.isEmpty()) {
            System.out.println("Invalid certificate.");
            System.exit(1);
        }

        if (action.equals("sign")) {
            SignaturDigitaleAPI.sign(file, certificate.get());
        } else if (action.equals("verify")) {
            SignaturDigitaleAPI.verify(file, certificate.get());
        } else {
            System.out.println("Usage: java SignaturDigitale <sign|verify> <file> <certificateId>");
            System.exit(1);
        }
    }

}
