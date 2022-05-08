package eu.caffeineabusers.signaturdigitale.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class for ciphering and deciphering
 *
 * @author Marek Charvát, Tomáš Plánský
 *
 */


@UtilityClass
public final class CipherUtils {

    @Contract("_, _ -> new")
    public static @NotNull String encipher(@NotNull String string, @NotNull String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return new String(cipher.doFinal(string.getBytes()));
    }

    @Contract("_, _ -> new")
    public static @NotNull String decipher(@NotNull String string, @NotNull String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(string.getBytes()));
    }

}
