package eu.caffeineabusers.signaturdigitale.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;

/**
 * Utility class for file operations.
 *
 * @author Tomáš Plánský, Marek Charvát
 */
@UtilityClass
public final class FileUtils {

    /**
     * Reads the file and returns its content as a string.
     *
     * @param file The file to read.
     * @return The content of the file as a string.
     */
    @NotNull
    public static String readFile(@NotNull File file) throws Exception {
        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader(file);
        int c;
        while ((c = fr.read()) != -1) {
            content.append((char) c);
        }
        fr.close();
        return content.toString();
    }

}
