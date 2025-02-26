package com.easypost;

import com.easypost.exception.API.BadRequestError;
import com.easypost.exception.API.ForbiddenError;
import com.easypost.exception.API.GatewayTimeoutError;
import com.easypost.exception.API.InternalServerError;
import com.easypost.exception.API.InvalidRequestError;
import com.easypost.exception.API.MethodNotAllowedError;
import com.easypost.exception.API.NotFoundError;
import com.easypost.exception.API.PaymentError;
import com.easypost.exception.API.RateLimitError;
import com.easypost.exception.API.RedirectError;
import com.easypost.exception.API.ServiceUnavailableError;
import com.easypost.exception.API.TimeoutError;
import com.easypost.exception.API.UnauthorizedError;
import com.easypost.exception.API.UnknownApiError;
import com.easypost.exception.APIException;
import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.easypost.model.FieldError;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ErrorTest extends Requestor {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException if any exception is thrown.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("error", TestUtils.ApiKey.TEST);
    }

    /**
     * Tests that we assign properties of an error correctly.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testError() throws EasyPostException {
        vcr.setUpTest("error");

        APIException exception = assertThrows(InvalidRequestError.class, () -> vcr.client.shipment.create(null));

        assertEquals(422, exception.getStatusCode());
        assertEquals("PARAMETER.REQUIRED", exception.getCode());
        assertEquals("Missing required parameter.", exception.getMessage());
        // assertEquals("cannot be blank", exception.getErrors().get(0));
        // assertEquals("shipment", exception.getErrors().get(0).getField());
    }

    /**
     * Tests that we assign properties of an error correctly when returned via the alternative format.
     * NOTE: Claims (among other things) uses the alternative errors format.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testErrorAlternativeFormat() throws EasyPostException {
        vcr.setUpTest("error_alternative_format");

        HashMap<String, Object> claimData = Fixtures.basicClaim();
        claimData.put("tracking_code", "123"); // Intentionally pass a bad tracking code

        APIException exception = assertThrows(NotFoundError.class, () -> vcr.client.claim.create(claimData));

        assertEquals(404, exception.getStatusCode());
        assertEquals("NOT_FOUND", exception.getCode());
        assertEquals("The requested resource could not be found.", exception.getMessage());
        assertEquals("No eligible insurance found with provided tracking code.", exception.getErrors().get(0));
    }

    /**
     * Test every error type and make sure each error has the correct properties.
     *
     * @throws EasyPostException if any exception is thrown.
     */
    @Test
    public void testKnownApiException() throws EasyPostException {
        HashMap<Integer, Class<?>> apiErrorsMap = new HashMap<Integer, Class<?>>();
        apiErrorsMap.put(300, RedirectError.class);
        apiErrorsMap.put(301, RedirectError.class);
        apiErrorsMap.put(302, RedirectError.class);
        apiErrorsMap.put(303, RedirectError.class);
        apiErrorsMap.put(304, RedirectError.class);
        apiErrorsMap.put(305, RedirectError.class);
        apiErrorsMap.put(306, RedirectError.class);
        apiErrorsMap.put(307, RedirectError.class);
        apiErrorsMap.put(308, RedirectError.class);
        apiErrorsMap.put(400, BadRequestError.class);
        apiErrorsMap.put(401, UnauthorizedError.class);
        apiErrorsMap.put(402, PaymentError.class);
        apiErrorsMap.put(403, ForbiddenError.class);
        apiErrorsMap.put(404, NotFoundError.class);
        apiErrorsMap.put(405, MethodNotAllowedError.class);
        apiErrorsMap.put(408, TimeoutError.class);
        apiErrorsMap.put(422, InvalidRequestError.class);
        apiErrorsMap.put(429, RateLimitError.class);
        apiErrorsMap.put(444, UnknownApiError.class);
        apiErrorsMap.put(500, InternalServerError.class);
        apiErrorsMap.put(503, ServiceUnavailableError.class);
        apiErrorsMap.put(504, GatewayTimeoutError.class);

        for (Map.Entry<Integer, Class<?>> entry : apiErrorsMap.entrySet()) {
            APIException exception = assertThrows(APIException.class,
                    () -> handleAPIError("{}", entry.getKey()));

            assertEquals(Constants.ErrorMessages.API_DID_NOT_RETURN_ERROR_DETAILS, exception.getMessage());
            assertEquals("NO RESPONSE CODE", exception.getCode());
            assertEquals(entry.getKey(), exception.getStatusCode());
            assertInstanceOf(entry.getValue(), exception);
        }
    }

    /**
     * Test parsing error message.
     *
     * @throws EasyPostException if any exception is thrown.
     */
    @Test
    public void testExceptionErrorMessageParsing() throws EasyPostException {
        String errorMessageStringJson = "{\n" +
                "    \"error\": {\n" +
                "        \"code\": \"ERROR_CODE\",\n" +
                "        \"message\": \"ERROR_MESSAGE_1\",\n" +
                "        \"errors\": []\n" +
                "    }\n" +
                "}";
        EasyPostException exception = assertThrows(EasyPostException.class,
                () -> handleAPIError(errorMessageStringJson, 400));

        assertEquals("ERROR_MESSAGE_1", exception.getMessage());
    }

    /**
     * Test parsing error message that is an array.
     *
     * @throws EasyPostException if any exception is thrown.
     */
    @Test
    public void testExceptionErrorArrayParsing() throws EasyPostException {
        String errorMessageArrayJson = "{\n" +
                "    \"error\": {\n" +
                "        \"code\": \"ERROR_CODE\",\n" +
                "        \"message\": [\n" +
                "            \"ERROR_MESSAGE_1\",\n" +
                "            \"ERROR_MESSAGE_2\"\n" +
                "        ],\n" +
                "        \"errors\": []\n" +
                "    }\n" +
                "}";
        EasyPostException exception = assertThrows(EasyPostException.class,
                () -> handleAPIError(errorMessageArrayJson, 400));

        assertEquals("ERROR_MESSAGE_1, ERROR_MESSAGE_2", exception.getMessage());
    }

    /**
     * Test parsing error message that is an object.
     *
     * @throws EasyPostException is any exception thrown.
     */
    @Test
    public void testExceptionErrorObjectParsing() throws EasyPostException {
        String errorMessageObjectJson = "{\n" +
                "    \"error\": {\n" +
                "        \"code\": \"UNPROCESSABLE_ENTITY\",\n" +
                "        \"message\": {\n" +
                "            \"errors\": [\n" +
                "                \"bad error.\",\n" +
                "                \"second bad error.\"\n" +
                "            ]\n" +
                "        },\n" +
                "        \"errors\": []\n" +
                "    }\n" +
                "}";

        EasyPostException exception = assertThrows(EasyPostException.class,
                () -> handleAPIError(errorMessageObjectJson, 400));

        assertEquals("bad error., second bad error.", exception.getMessage());
    }

    /**
     * Test parsing error message that has really bad format.
     *
     * @throws EasyPostException if any exception is thrown.
     */
    @Test
    public void testExceptionErrorEdgeCaseParsing() throws EasyPostException {
        String json = "{\n" +
                "    \"error\": {\n" +
                "        \"code\": \"UNPROCESSABLE_ENTITY\",\n" +
                "        \"message\": {\n" +
                "            \"errors\": [\n" +
                "                \"Bad format 1\",\n" +
                "                \"Bad format 2\"\n" +
                "            ],\n" +
                "            \"bad_data\": [\n" +
                "                {\n" +
                "                    \"first_message\": \"Bad format 3\",\n" +
                "                    \"second_message\": \"Bad format 4\",\n" +
                "                    \"thrid_message\": \"Bad format 5\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"errors\": []\n" +
                "    }\n" +
                "}";

        EasyPostException exception = assertThrows(EasyPostException.class,
                () -> handleAPIError(json, 400));

        assertEquals("Bad format 1, Bad format 2, Bad format 3, Bad format 4, Bad format 5",
                exception.getMessage());
    }
}
