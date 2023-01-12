package com.easypost.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
     * namespace org.apache.commons.codec.DecoderException,
     * released under the <a href="https://www.apache.org/licenses/LICENSE-2.0.txt">Apache License 2.0</a>.
     */
    public static class DecoderException extends Exception {

        private static final long serialVersionUID = 1L;

        /**
         * Constructs a new {@link DecoderException}.
         */
        public DecoderException() {}

        /**
         * Constructs a new {@link DecoderException} with the specified message.
         *
         * @param message the message
         */
        public DecoderException(String message) {
            super(message);
        }

        /**
         * Constructs a new {@link DecoderException} with the specified message and inner exception.
         *
         * @param message the message
         * @param cause the inner exception
         */
        public DecoderException(String message, Throwable cause) {
            super(message, cause);
        }

        /**
         * Constructs a new {@link DecoderException} with the specified inner exception.
         *
         * @param cause the inner exception
         */
        public DecoderException(Throwable cause) {
            super(cause);
        }
    }

    /**
     * This class was lifted and modified from
     * <a href="https://mvnrepository.com/artifact/commons-codec/commons-codec/1.15">
     * common-codec:common-codec version 1.15</a>,
     * namespace org.apache.commons.codec.EncoderException,
     * released under the <a href="https://www.apache.org/licenses/LICENSE-2.0.txt">Apache License 2.0</a>.
     */
    public static class EncoderException extends Exception {

        private static final long serialVersionUID = 1L;

        /**
         * Constructs a new {@link EncoderException}.
         */
        public EncoderException() {}

        /**
         * Constructs a new {@link EncoderException} with the specified message.
         *
         * @param message the message
         */
        public EncoderException(String message) {
            super(message);
        }

        /**
         * Constructs a new {@link EncoderException} with the specified message and inner exception.
         *
         * @param message the message
         * @param cause the inner exception
         */
        public EncoderException(String message, Throwable cause) {
            super(message, cause);
        }

        /**
         * Constructs a new {@link EncoderException} with the specified inner exception.
         *
         * @param cause the inner exception
         */
        public EncoderException(Throwable cause) {
            super(cause);
        }
    }

    /**
     * This class was lifted and modified from
     * <a href="https://mvnrepository.com/artifact/commons-codec/commons-codec/1.15">
     * common-codec:common-codec version 1.15</a>,
     * namespace org.apache.commons.codec.binary.Hex,
     * released under the <a href="https://www.apache.org/licenses/LICENSE-2.0.txt">Apache License 2.0</a>.
     */
    public static class Hex {
        public static final Charset DEFAULT_CHARSET;
        private static final char[] DIGITS_LOWER;
        private static final char[] DIGITS_UPPER;
        private final Charset charset;

        /**
         * Decode a hex string into a byte array.
         *
         * @param data the string to decode
         * @return the decoded byte array
         */
        public static byte[] decodeHex(String data) throws DecoderException {
            return decodeHex(data.toCharArray());
        }

        /**
         * Decode a hex char array into a byte array.
         *
         * @param data the char array to decode
         * @return the decoded byte array
         */
        public static byte[] decodeHex(char[] data) throws DecoderException {
            int len = data.length;
            if ((len & 1) != 0) {
                throw new DecoderException("Odd number of characters.");
            } else {
                byte[] out = new byte[len >> 1];
                int i = 0;

                for (int j = 0; j < len; ++i) {
                    int f = toDigit(data[j], j) << 4;
                    ++j;
                    f |= toDigit(data[j], j);
                    ++j;
                    out[i] = (byte) (f & 255);
                }

                return out;
            }
        }

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
         * Encode a ByteBuffer into a hex char array.
         *
         * @param data the ByteBuffer to encode
         * @return the hex encoded char array
         */
        public static char[] encodeHex(ByteBuffer data) {
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
         * Encode a ByteBuffer into a hex char array.
         *
         * @param data the ByteBuffer to encode
         * @param toLowerCase {@code true} converts to lowercase, {@code false} to uppercase
         * @return the hex encoded char array
         */
        public static char[] encodeHex(ByteBuffer data, boolean toLowerCase) {
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

        /**
         * Encode a ByteBuffer into a hex char array.
         *
         * @param data the ByteBuffer to encode
         * @param toDigits map of elements to convert to digits
         * @return the hex encoded char array
         */
        protected static char[] encodeHex(ByteBuffer data, char[] toDigits) {
            return encodeHex(data.array(), toDigits);
        }

        /**
         * Encode a byte array into a hex string.
         *
         * @param data the byte array to encode
         * @return the hex encoded string
         */
        public static String encodeHexString(byte[] data) {
            return new String(encodeHex(data));
        }

        /**
         * Encode a byte array into a hex string.
         *
         * @param data the byte array to encode
         * @param toLowerCase {@code true} converts to lowercase, {@code false} to uppercase
         * @return the hex encoded string
         */
        public static String encodeHexString(byte[] data, boolean toLowerCase) {
            return new String(encodeHex(data, toLowerCase));
        }

        /**
         * Encode a ByteBuffer into a hex string.
         *
         * @param data the ByteBuffer to encode
         * @return the hex encoded char array
         */
        public static String encodeHexString(ByteBuffer data) {
            return new String(encodeHex(data));
        }

        /**
         * Encode a ByteBuffer into a hex string.
         *
         * @param data the ByteBuffer to encode
         * @param toLowerCase {@code true} converts to lowercase, {@code false} to uppercase
         * @return the hex encoded char array
         */
        public static String encodeHexString(ByteBuffer data, boolean toLowerCase) {
            return new String(encodeHex(data, toLowerCase));
        }

        /**
         * Decode a character to a digit.
         *
         * @param ch the character to decode
         * @param index the index of the character in the source
         * @return the digit
         * @throws DecoderException Thrown if ch is an illegal hex character
         */
        protected static int toDigit(char ch, int index) throws DecoderException {
            int digit = Character.digit(ch, 16);
            if (digit == -1) {
                throw new DecoderException("Illegal hexadecimal character " + ch + " at index " + index);
            } else {
                return digit;
            }
        }

        /**
         * Construct a new Hex codec with the default charset.
         */
        public Hex() {
            this.charset = DEFAULT_CHARSET;
        }

        /**
         * Construct a new Hex codec with the given charset.
         *
         * @param charset the charset to use for string decoding and encoding
         */
        public Hex(Charset charset) {
            this.charset = charset;
        }

        /**
         * Construct a new Hex codec with the given charset name.
         *
         * @param charsetName the name of the charset to use for string decoding and encoding
         */
        public Hex(String charsetName) {
            this(Charset.forName(charsetName));
        }

        /**
         * Decode a hex byte array into a byte array.
         *
         * @param array An array of hex-encoded characters
         * @return A byte array representing the decoded hex string
         */
        public byte[] decode(byte[] array) throws DecoderException {
            return decodeHex((new String(array, this.getCharset())).toCharArray());
        }

        /**
         * Decode a hex ByteBuffer into a byte array.
         *
         * @param buffer A ByteBuffer of hex-encoded characters
         * @return A byte array representing the decoded hex string
         * @throws DecoderException Thrown if the ByteBuffer is not backed by an array
         */
        public byte[] decode(ByteBuffer buffer) throws DecoderException {
            return decodeHex((new String(buffer.array(), this.getCharset())).toCharArray());
        }

        /**
         * Decode a hex object.
         *
         * @param object The object to decode
         * @return A byte array representing the decoded hex string
         * @throws DecoderException Thrown if the object is not a String or byte[]
         */
        public Object decode(Object object) throws DecoderException {
            if (object instanceof String) {
                return this.decode((Object) ((String) object).toCharArray());
            } else if (object instanceof byte[]) {
                return this.decode((byte[]) ((byte[]) object));
            } else if (object instanceof ByteBuffer) {
                return this.decode((ByteBuffer) object);
            } else {
                try {
                    return decodeHex((char[]) ((char[]) object));
                } catch (ClassCastException var3) {
                    throw new DecoderException(var3.getMessage(), var3);
                }
            }
        }

        /**
         * Decode a hex byte array into a byte array.
         *
         * @param array An array of hex-encoded characters
         * @return A byte array representing the decoded hex string
         */
        public byte[] encode(byte[] array) {
            return encodeHexString(array).getBytes(this.getCharset());
        }

        /**
         * Decode a hex ByteBuffer into a byte array.
         *
         * @param array A ByteBuffer of hex-encoded characters
         * @return A byte array representing the decoded hex string
         */
        public byte[] encode(ByteBuffer array) {
            return encodeHexString(array).getBytes(this.getCharset());
        }

        /**
         * Encode an object.
         *
         * @param object The object to encode
         * @return An encoded object
         * @throws EncoderException Thrown if the object is not a String or byte[]
         */
        public Object encode(Object object) throws EncoderException {
            byte[] byteArray;
            if (object instanceof String) {
                byteArray = ((String) object).getBytes(this.getCharset());
            } else if (object instanceof ByteBuffer) {
                byteArray = ((ByteBuffer) object).array();
            } else {
                try {
                    byteArray = (byte[]) ((byte[]) object);
                } catch (ClassCastException var4) {
                    throw new EncoderException(var4.getMessage(), var4);
                }
            }

            return encodeHex(byteArray);
        }

        /**
         * Gets the charset.
         *
         * @return the charset
         */
        public Charset getCharset() {
            return this.charset;
        }

        /**
         * Gets the charset name.
         *
         * @return the name of the charset
         */
        public String getCharsetName() {
            return this.charset.name();
        }

        /**
         * Returns a string representation of the object, which includes the charset name.
         *
         * @return a string representation of the object
         */
        public String toString() {
            return super.toString() + "[charsetName=" + this.charset + "]";
        }

        static {
            DEFAULT_CHARSET = StandardCharsets.UTF_8;
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
