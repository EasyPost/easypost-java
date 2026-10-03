package com.easypost;

import com.easypost.exception.API.HttpError;
import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.model.Address;
import com.easypost.service.EasyPostClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class RequestorTest extends Requestor {
    private static final long MAX_BYTES = Constants.Http.MAX_RESPONSE_BODY_BYTES;
    private static final String TOO_LARGE_MESSAGE = String.format(Constants.ErrorMessages.RESPONSE_BODY_TOO_LARGE,
            Constants.Http.MAX_RESPONSE_BODY_BYTES);

    /**
     * An InputStream that generates bytes on demand, so tests can simulate very large responses
     * without allocating them up front.
     */
    private static final class GeneratedInputStream extends InputStream {
        private final long length;
        private long bytesRead = 0;
        private boolean closed = false;

        /**
         * GeneratedInputStream constructor.
         *
         * @param length The number of bytes the stream will produce.
         */
        GeneratedInputStream(final long length) {
            this.length = length;
        }

        @Override
        public int read() {
            if (bytesRead >= length) {
                return -1;
            }
            bytesRead++;
            return 'a';
        }

        @Override
        public int read(final byte[] b, final int off, final int len) {
            if (bytesRead >= length) {
                return -1;
            }
            int count = (int) Math.min(len, length - bytesRead);
            Arrays.fill(b, off, off + count, (byte) 'a');
            bytesRead += count;
            return count;
        }

        @Override
        public void close() {
            closed = true;
        }
    }

    /**
     * Build a mocked connection that returns a 200 response with the given body and Content-Length.
     *
     * @param body          The response body stream.
     * @param contentLength The declared Content-Length, or -1 if unknown.
     * @return HttpsURLConnection object.
     * @throws IOException if the mock cannot be set up.
     */
    private static HttpsURLConnection mockConnection(final InputStream body, final long contentLength)
            throws IOException {
        HttpsURLConnection connection = Mockito.mock(HttpsURLConnection.class);
        Mockito.when(connection.getResponseCode()).thenReturn(200);
        Mockito.when(connection.getContentLengthLong()).thenReturn(contentLength);
        Mockito.when(connection.getInputStream()).thenReturn(body);
        return connection;
    }

    /**
     * Clear the connection override after each test.
     */
    @AfterEach
    public void tearDown() {
        EasyPost._vcrUrlFunction = null;
    }

    /**
     * Test reading a normal response body.
     *
     * @throws EasyPostException when the request fails.
     * @throws IOException       when the stream cannot be read.
     */
    @Test
    public void testGetResponseBody() throws EasyPostException, IOException {
        String json = "{\"name\": \"Jürgen 中文\"}";
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);

        assertEquals(json, getResponseBody(new ByteArrayInputStream(bytes), bytes.length));
    }

    /**
     * Test that a multi-byte UTF-8 character split across read chunks is decoded correctly.
     *
     * @throws EasyPostException when the request fails.
     * @throws IOException       when the stream cannot be read.
     */
    @Test
    public void testGetResponseBodyMultiByteCharacterAcrossReads() throws EasyPostException, IOException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8191; i++) {
            builder.append('a');
        }
        builder.append('ü');
        String body = builder.toString();
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);

        assertEquals(body, getResponseBody(new ByteArrayInputStream(bytes), -1));
    }

    /**
     * Test that empty and missing response streams return an empty body.
     *
     * @throws EasyPostException when the request fails.
     * @throws IOException       when the stream cannot be read.
     */
    @Test
    public void testGetResponseBodyEmpty() throws EasyPostException, IOException {
        assertEquals("", getResponseBody(new ByteArrayInputStream(new byte[0]), 0));
        assertEquals("", getResponseBody(null, -1));
    }

    /**
     * Test that a response body exactly at the size limit is read.
     *
     * @throws EasyPostException when the request fails.
     * @throws IOException       when the stream cannot be read.
     */
    @Test
    public void testGetResponseBodyAtLimit() throws EasyPostException, IOException {
        GeneratedInputStream stream = new GeneratedInputStream(MAX_BYTES);

        assertEquals(MAX_BYTES, getResponseBody(stream, MAX_BYTES).length());
        assertTrue(stream.closed);
    }

    /**
     * Test that an oversized Content-Length is rejected before any of the body is read.
     */
    @Test
    public void testGetResponseBodyRejectsOversizedContentLength() {
        GeneratedInputStream stream = new GeneratedInputStream(MAX_BYTES + 1);

        HttpError error = assertThrows(HttpError.class, () -> getResponseBody(stream, MAX_BYTES + 1));

        assertEquals(TOO_LARGE_MESSAGE, error.getMessage());
        assertEquals(0, stream.bytesRead);
        assertTrue(stream.closed);
    }

    /**
     * Test that the size limit is enforced while streaming when Content-Length is missing.
     */
    @Test
    public void testGetResponseBodyRejectsOversizedBodyWithoutContentLength() {
        GeneratedInputStream stream = new GeneratedInputStream(Long.MAX_VALUE);

        HttpError error = assertThrows(HttpError.class, () -> getResponseBody(stream, -1));

        assertEquals(TOO_LARGE_MESSAGE, error.getMessage());
        assertTrue(stream.bytesRead < 2 * MAX_BYTES);
        assertTrue(stream.closed);
    }

    /**
     * Test that the size limit is enforced while streaming when Content-Length understates the body size.
     */
    @Test
    public void testGetResponseBodyRejectsOversizedBodyWithWrongContentLength() {
        GeneratedInputStream stream = new GeneratedInputStream(MAX_BYTES + 1);

        HttpError error = assertThrows(HttpError.class, () -> getResponseBody(stream, 100));

        assertEquals(TOO_LARGE_MESSAGE, error.getMessage());
        assertTrue(stream.closed);
    }

    /**
     * Test that a normal API response is read and deserialized.
     *
     * @throws EasyPostException when the request fails.
     * @throws IOException       when the mock cannot be set up.
     */
    @Test
    public void testRequestReadsNormalResponse() throws EasyPostException, IOException {
        byte[] body = "{\"id\": \"adr_123\", \"object\": \"Address\"}".getBytes(StandardCharsets.UTF_8);
        HttpsURLConnection connection = mockConnection(new ByteArrayInputStream(body), -1);
        EasyPost._vcrUrlFunction = url -> connection;
        EasyPostClient client = new EasyPostClient("fake_api_key");

        Address address = client.address.retrieve("adr_123");

        assertEquals("adr_123", address.getId());
    }

    /**
     * Test that an API request fails without reading the body when Content-Length exceeds the limit.
     *
     * @throws EasyPostException when the request fails.
     * @throws IOException       when the mock cannot be set up.
     */
    @Test
    public void testRequestRejectsOversizedContentLength() throws EasyPostException, IOException {
        GeneratedInputStream stream = new GeneratedInputStream(MAX_BYTES + 1);
        HttpsURLConnection connection = mockConnection(stream, MAX_BYTES + 1);
        EasyPost._vcrUrlFunction = url -> connection;
        EasyPostClient client = new EasyPostClient("fake_api_key");

        HttpError error = assertThrows(HttpError.class, () -> client.address.retrieve("adr_123"));

        assertEquals(TOO_LARGE_MESSAGE, error.getMessage());
        assertEquals(0, stream.bytesRead);
    }

    /**
     * Test that an API request fails when an oversized body is streamed without a Content-Length.
     *
     * @throws EasyPostException when the request fails.
     * @throws IOException       when the mock cannot be set up.
     */
    @Test
    public void testRequestRejectsOversizedStreamedBody() throws EasyPostException, IOException {
        GeneratedInputStream stream = new GeneratedInputStream(Long.MAX_VALUE);
        HttpsURLConnection connection = mockConnection(stream, -1);
        EasyPost._vcrUrlFunction = url -> connection;
        EasyPostClient client = new EasyPostClient("fake_api_key");

        HttpError error = assertThrows(HttpError.class, () -> client.address.retrieve("adr_123"));

        assertEquals(TOO_LARGE_MESSAGE, error.getMessage());
        assertTrue(stream.closed);
    }
}
