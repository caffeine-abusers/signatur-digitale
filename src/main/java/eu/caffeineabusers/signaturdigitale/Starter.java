package eu.caffeineabusers.signaturdigitale;

import eu.caffeineabusers.signaturdigitale.certificate.Certificate;
import eu.caffeineabusers.signaturdigitale.signature.VerificationResult;

/**
 * This class is responsible for starting the application.
 *
 * @author Tomáš Plánský, Marek Charvát
 */
public final class Starter {

    public static void main(String[] args) {


        if (args.length != 3) {
            System.out.println("Usage: java SignaturDigitale <sign|verify> <file> <certificateName>");
            return;
        }

        String action = args[0];
        String file = args[1];
        String certificateName = args[2];

        SignaturDigitale signaturDigitale = new SignaturDigitale();
        Certificate certificate = signaturDigitale.getCertificateRegistry().get(certificateName);
        if (certificate == null) {
            System.out.println("Invalid certificate.");
            return;
        }

        if (action.equals("sign")) {
            SignaturDigitaleAPI.sign(file, certificate);
        } else if (action.equals("verify")) {
            VerificationResult result = SignaturDigitaleAPI.verify(file);
            switch (result) {
                case VALID -> System.out.println("Signature is valid.");
                case INVALID -> System.out.println("Signature is invalid.");
                case EXPIRED -> System.out.println("Certificate expired.");
                case NOT_SIGNED -> System.out.println("File is not signed.");
            }
        } else {
            System.out.println("Usage: java SignaturDigitale <sign|verify> <file> <certificateName>");
        }
    }

}
