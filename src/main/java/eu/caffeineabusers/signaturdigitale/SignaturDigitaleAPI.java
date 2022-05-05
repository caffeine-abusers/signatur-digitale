package eu.caffeineabusers.signaturdigitale;

import eu.caffeineabusers.signaturdigitale.certificate.Certificate;
import eu.caffeineabusers.signaturdigitale.signature.Signature;
import eu.caffeineabusers.signaturdigitale.utils.CipherUtils;
import eu.caffeineabusers.signaturdigitale.utils.FileUtils;
import eu.caffeineabusers.signaturdigitale.utils.HashUtils;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the custom API used by the {@link SignaturDigitale} application
 * to sign and verify files.
 *
 * @author Tomáš Plánský
 */
@UtilityClass
public final class SignaturDigitaleAPI {

    // {SIGNATURE:ID:HASH}
    private static final Pattern SIGNATURE_PATTERN = Pattern.compile("\\{SIGNATURE:([1]):([a-zA-Z\\d]+)}");

    /**
     * Signs the file with the given {@link Certificate}.
     *
     * @param file The file to sign.
     * @param certificate The certificate.
     */
    public static void sign(@NotNull String file, @NotNull Certificate certificate) {
        try {
            sign(new File(file), certificate);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Signs the file with the given {@link Certificate}.
     *
     * @param file The file to sign.
     * @param certificate The certificate.
     */
    public static void sign(@NotNull File file, @NotNull Certificate certificate) throws Exception {
        String encipheredContentHash = getEncipheredFileContentHash(file, certificate);
        // Create the signature.
        Signature signature = new Signature(certificate.subjectId().toString(), encipheredContentHash);
        // Put the signature into the file.
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.append(signature.toString());
            fileWriter.flush();
        }
    }

    /**
     * Verifies the signature of the file with the given {@link Certificate}.
     *
     * @param file The file to verify.
     * @param certificate The certificate.
     * @return True if the file is signed and the signature is valid, false otherwise.
     */
    public static boolean verify(@NotNull String file, @NotNull Certificate certificate) {
        try {
            return verify(new File(file), certificate);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifies the signature of the file with the given {@link Certificate}.
     *
     * @param file The file to verify.
     * @param certificate The certificate.
     * @return True if the file is signed and the signature is valid, false otherwise.
     */
    public static boolean verify(@NotNull File file, @NotNull Certificate certificate) throws Exception {
        String content = FileUtils.readFile(file);
        Signature signature = findSignature(content);
        if (signature == null) {
            return false;
        }
        String encipheredContentHash = getEncipheredFileContentHash(file, certificate);

        return true;
    }

    /**
     * Utility method that gets the content of a file, hashes it and enciphers it with the key
     * from the given {@link Certificate}. The enciphered hash is returned.
     *
     * @param file The file.
     * @param certificate The certificate.
     * @return The enciphered hash.
     * @throws Exception If the file content couldn't be read.
     */
    private static String getEncipheredFileContentHash(@NotNull File file, @NotNull Certificate certificate) throws Exception {
        // Get the content of the file as a String.
        String content = FileUtils.readFile(file);
        // Hash the content.
        String contentHash = HashUtils.hash(content);
        // Encipher the content hash with the key from the certificate.
        return CipherUtils.encipher(contentHash, certificate.key());
    }

    /**
     * Attempts to find a signature in the given file content string. If the signature
     * is present, it will be returned as the {@link Signature} object.
     *
     * @param fileContent The content to search in.
     * @return The signature or null if it wasn't found in the content.
     */
    @Nullable
    public static Signature findSignature(@NotNull String fileContent) {
        Matcher matcher = SIGNATURE_PATTERN.matcher(fileContent);
        if (matcher.find()) {
            String id = matcher.group(1);
            String hash = matcher.group(2);
            return new Signature(id, hash);
        }
        return null;
    }

}
