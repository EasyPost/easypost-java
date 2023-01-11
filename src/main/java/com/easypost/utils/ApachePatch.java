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

        public DecoderException() {}

        public DecoderException(String message) {
            super(message);
        }

        public DecoderException(String message, Throwable cause) {
            super(message, cause);
        }

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

        public EncoderException() {}

        public EncoderException(String message) {
            super(message);
        }

        public EncoderException(String message, Throwable cause) {
            super(message, cause);
        }

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

        public static byte[] decodeHex(String data) throws DecoderException {
            return decodeHex(data.toCharArray());
        }

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

        public static char[] encodeHex(byte[] data) {
            return encodeHex(data, true);
        }

        public static char[] encodeHex(ByteBuffer data) {
            return encodeHex(data, true);
        }

        public static char[] encodeHex(byte[] data, boolean toLowerCase) {
            return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
        }

        public static char[] encodeHex(ByteBuffer data, boolean toLowerCase) {
            return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
        }

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

        protected static char[] encodeHex(ByteBuffer data, char[] toDigits) {
            return encodeHex(data.array(), toDigits);
        }

        public static String encodeHexString(byte[] data) {
            return new String(encodeHex(data));
        }

        public static String encodeHexString(byte[] data, boolean toLowerCase) {
            return new String(encodeHex(data, toLowerCase));
        }

        public static String encodeHexString(ByteBuffer data) {
            return new String(encodeHex(data));
        }

        public static String encodeHexString(ByteBuffer data, boolean toLowerCase) {
            return new String(encodeHex(data, toLowerCase));
        }

        protected static int toDigit(char ch, int index) throws DecoderException {
            int digit = Character.digit(ch, 16);
            if (digit == -1) {
                throw new DecoderException("Illegal hexadecimal character " + ch + " at index " + index);
            } else {
                return digit;
            }
        }

        public Hex() {
            this.charset = DEFAULT_CHARSET;
        }

        public Hex(Charset charset) {
            this.charset = charset;
        }

        public Hex(String charsetName) {
            this(Charset.forName(charsetName));
        }

        public byte[] decode(byte[] array) throws DecoderException {
            return decodeHex((new String(array, this.getCharset())).toCharArray());
        }

        public byte[] decode(ByteBuffer buffer) throws DecoderException {
            return decodeHex((new String(buffer.array(), this.getCharset())).toCharArray());
        }

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

        public byte[] encode(byte[] array) {
            return encodeHexString(array).getBytes(this.getCharset());
        }

        public byte[] encode(ByteBuffer array) {
            return encodeHexString(array).getBytes(this.getCharset());
        }

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

        public Charset getCharset() {
            return this.charset;
        }

        public String getCharsetName() {
            return this.charset.name();
        }

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
