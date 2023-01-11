package com.easypost.utils;

import com.easypost.utils.ApachePatch.Hex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    }

    /**
     * Hex-encode a byte array to a string.
     *
     * @param bytes the byte array to hex-encode.
     * @return the hex-encoded byte array string.
     */
    public static String hexEncodeToString(byte @NotNull [] bytes) {
        return new String(Hex.encodeHex(bytes));
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
                                                Normalizer.@Nullable Form normalizationForm) {
        if (normalizationForm != null) {
            key = Normalizer.normalize(key, normalizationForm);
        }

        byte[] hmacBytes = createHMAC(data, key, HmacAlgorithm.SHA256);
        return hexEncodeToString(hmacBytes);
    }

    /**
     * Calculate the HMAC hex digest of a string.
     *
     * @param data      Data to calculate hex digest for.
     * @param key       Key to use in HMAC calculation.
     * @param algorithm {@link HmacAlgorithm} to use to calculate HMAC.
     * @return Hex digest of data.
     */
    public static byte[] createHMAC(byte @NotNull [] data, @NotNull String key, @NotNull HmacAlgorithm algorithm) {
        // create HMAC-SHA256 generator and compute hash of data
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec keyHash = new SecretKeySpec(keyBytes, algorithm.algorithmString);

        try {
            Mac hmac = Mac.getInstance(algorithm.algorithmString);
            hmac.init(keyHash);
            return hmac.doFinal(data);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("Cannot initialize Mac Generator", e);
        }
    }

    /**
     * Check whether two signatures match. This is safe against timing attacks.
     *
     * @param signature1 First signature to check.
     * @param signature2 Second signature to check.
     * @return True if signatures match, false otherwise.
     */
    public static boolean signaturesMatch(byte @NotNull [] signature1, byte @NotNull [] signature2) {
        // after Java SE 6 Update 17, MessageDigest.isEqual() is safe against timing attacks.
        // see: https://codahale.com//a-lesson-in-timing-attacks/
        return MessageDigest.isEqual(signature1, signature2);
    }

    /**
     * Check whether two signatures match. This is safe against timing attacks.
     *
     * @param signature1 First signature to check.
     * @param signature2 Second signature to check.
     * @return True if signatures match, false otherwise.
     */
    public static boolean signaturesMatch(@NotNull String signature1, @NotNull String signature2) {
        byte[] signature1Bytes = signature1.getBytes(StandardCharsets.UTF_8);
        byte[] signature2Bytes = signature2.getBytes(StandardCharsets.UTF_8);
        return signaturesMatch(signature1Bytes, signature2Bytes);
    }
}

