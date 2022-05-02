package eu.caffeineabusers.signaturdigitale.utils;

import lombok.experimental.UtilityClass;

/**
 * Utility class that contains some common methods.
 *
 * @author Tomáš Plánský
 */
@UtilityClass
public final class Common {

    /**
     * Generates a random number between min and max.
     *
     * @param min The minimum value. (inclusive)
     * @param max The maximum value. (inclusive)
     * @return A random number between min and max.
     */
    public static int irand(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }



}
