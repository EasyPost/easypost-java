package com.easypost.utils;

/**
 * This class is a collection of Apache functions that have been extracted for use in this application.
 * This class is required because Apache's libraries are notorious for security vulnerabilities, so we'll only
 * extract the safe functions that we need, rather than importing an entire vulnerable dependency.
 * <p>
 * A lot of the functions in here are lifted straight from Apache's source code with minor alterations,
 * so any janky code complaints should be directed at the Apache developers who wrote them probably 20 years ago.
 */
public abstract class ApachePatch {

    /**
     * This class was lifted and modified from
     * <a href="https://mvnrepository.com/artifact/commons-codec/commons-codec/1.15">
     * common-codec:common-codec version 1.15</a>,
     * namespace org.apache.commons.codec.binary.Hex,
     * released under the <a href="https://www.apache.org/licenses/LICENSE-2.0.txt">Apache License 2.0</a>.
     */
    public static class Hex {
        private static final char[] DIGITS_LOWER;
        private static final char[] DIGITS_UPPER;

        /**
         * Encode a byte array into a hex char array.
         *
         * @param data the byte array to encode
         * @return the hex encoded char array
         */
        public static char[] encodeHex(byte[] data) {
            return encodeHex(data, true);
        }

        /**
         * Encode a byte array into a hex char array.
         *
         * @param data the byte array to encode
         * @param toLowerCase {@code true} converts to lowercase, {@code false} to uppercase
         * @return the hex encoded char array
         */
        public static char[] encodeHex(byte[] data, boolean toLowerCase) {
            return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
        }

        /**
         * Encode a byte array into a hex char array.
         *
         * @param data the byte array to encode
         * @param toDigits map of elements to convert to digits
         * @return the hex encoded char array
         */
        protected static char[] encodeHex(byte[] data, char[] toDigits) {
            int l = data.length;
            char[] out = new char[l << 1];
            int i = 0;

            for (int j = 0; i < l; ++i) {
                out[j++] = toDigits[(240 & data[i]) >>> 4];
                out[j++] = toDigits[15 & data[i]];
            }

            return out;
        }

        static {
            DIGITS_LOWER = new char[] {
                    '0',
                    '1',
                    '2',
                    '3',
                    '4',
                    '5',
                    '6',
                    '7',
                    '8',
                    '9',
                    'a',
                    'b',
                    'c',
                    'd',
                    'e',
                    'f'
            };
            DIGITS_UPPER = new char[] {
                    '0',
                    '1',
                    '2',
                    '3',
                    '4',
                    '5',
                    '6',
                    '7',
                    '8',
                    '9',
                    'A',
                    'B',
                    'C',
                    'D',
                    'E',
                    'F'
            };
        }
    }

}
