package eu.caffeineabusers.signaturdigitale;

/**
 * This class is responsible for starting the application.
 *
 * @author Tomáš Plánský
 */
public final class Starter {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java SignaturDigitale <sign|verify> <file> <key>");
            System.exit(1);
        }

        String action = args[0];
        String file = args[1];
        String key = args[2];

        SignaturDigitale signaturDigitale = new SignaturDigitale();
        if (action.equals("sign")) {
            signaturDigitale.sign(file, key);
        } else if (action.equals("verify")) {
            signaturDigitale.verify(file, key);
        } else {
            System.out.println("Usage: java SignaturDigitale <sign|verify> <file> <key>");
            System.exit(1);
        }
    }

}
