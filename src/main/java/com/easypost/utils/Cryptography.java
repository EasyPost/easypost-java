package com.easypost.utils;

import org.apache.commons.codec.digest.HmacUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.Normalizer;

/**
 * Class for various cryptography utilities.
 */
public abstract class Cryptography {
    /**
     * Enums for the supported HMAC algorithms.
     */
    public enum HmacAlgorithm {
        MD5("HmacMD5"),
        SHA1("HmacSHA1"),
        SHA256("HmacSHA256"),
        SHA512("HmacSHA512");

        private final String algorithmString;

        /**
         * Constructor.
         *
         * @param algorithmString the algorithm string
         */
        HmacAlgorithm(String algorithmString) {
            this.algorithmString = algorithmString;
        }

        /**
         * Get the algorithm string.
         *
         * @return the algorithm string
         */
        String getAlgorithmString() {
            return algorithmString;
        }
    }

    /**
     * Calculate the HMAC-SHA256 hex digest of a string.
     *
     * @param data              Data to calculate hex digest for.
     * @param key               Key to use in HMAC calculation.
     * @param normalizationForm {@link Normalizer.Form} to use when normalizing key. No normalization when null.
     * @return Hex digest of data.
     */
    public static String toHMACSHA256HexDigest(byte @NotNull [] data, @NotNull String key,
                                               @Nullable Normalizer.Form normalizationForm) {
        if (normalizationForm != null) {
            key = Normalizer.normalize(key, normalizationForm);
        }

        return createHMACHexDigest(data, key, HmacAlgorithm.SHA256);
    }

    /**
     * Calculate the HMAC-SHA256 hex digest of a string.
     *
     * @param data              Data to calculate hex digest for.
     * @param key               Key to use in HMAC calculation.
     * @param normalizationForm {@link Normalizer.Form} to use when normalizing key. No normalization when null.
     * @return Hex digest of data.
     */
    public static String toHMACSHA256HexDigest(@NotNull String data, @NotNull String key,
                                               @Nullable Normalizer.Form normalizationForm) {
        byte[] dataBytes = data.getBytes();
        return toHMACSHA256HexDigest(dataBytes, key, normalizationForm);
    }

    /**
     * Calculate the HMAC hex digest of a string.
     *
     * @param data      Data to calculate hex digest for.
     * @param key       Key to use in HMAC calculation.
     * @param algorithm {@link HmacAlgorithm} to use to calculate HMAC.
     * @return Hex digest of data.
     */
    public static String createHMACHexDigest(byte @NotNull [] data, @NotNull String key,
                                             @NotNull HmacAlgorithm algorithm) {
        // create HMAC-SHA256 generator and compute hex digest of data
        return new HmacUtils(algorithm.algorithmString, key).hmacHex(data);
    }

    /**
     * Check whether two signatures match.
     *
     * @param signature1 First signature to check.
     * @param signature2 Second signature to check.
     * @return True if signatures match, false otherwise.
     */
    public static boolean signaturesMatch(@NotNull String signature1, @Nullable String signature2) {
        return signature1.equals(signature2);
    }
}
