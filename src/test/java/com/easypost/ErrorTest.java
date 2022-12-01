package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.API.RedirectError;
import com.easypost.exception.API.ServiceUnavailablError;
import com.easypost.exception.API.UnauthorizedError;
import com.easypost.exception.API.UnknownApiError;
import com.easypost.exception.General.MissingParameterError;
import com.easypost.http.Requestor;
import com.easypost.exception.API.PaymentError;
import com.easypost.exception.API.RateLimitError;
import com.easypost.exception.API.NotFoundError;
import com.easypost.exception.API.MethodNotAllowedError;
import com.easypost.exception.API.TimeoutError;
import com.easypost.exception.API.ForbiddenError;
import com.easypost.exception.API.GatewayTimeoutError;
import com.easypost.exception.API.InternalServerError;
import com.easypost.exception.API.InvalidRequestError;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.HashMap;
import java.util.Map;

public final class ErrorTest extends Requestor {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     * @throws MissingParameterError
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws MissingParameterError {
        vcr = new TestUtils.VCR("error", TestUtils.ApiKey.TEST);
    }

    /**
     * Test creating a bad shipment and retrieving errors.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testError() throws EasyPostException {
        vcr.setUpTest("error");
        
        EasyPostException exception = assertThrows(InvalidRequestError.class, () -> vcr.client.shipment.create(null));

        assertEquals(422, exception.getStatusCode());
        assertEquals("PARAMETER.REQUIRED", exception.getCode());
        assertEquals("Missing required parameter.", exception.getMessage());
        assertEquals("cannot be blank", exception.getErrors().get(0).getMessage());
        assertEquals("shipment", exception.getErrors().get(0).getField());
    }

    /**
     * Test every error type and make sure each error has the correct properties.
     *
     * @throws EasyPostException
     */
    @Test
    public void testKnownApiException() throws EasyPostException {
        HashMap<Integer, Class<?>> apiErrorsMap = new HashMap<Integer, Class<?>>() {{
            put(300, RedirectError.class);
            put(301, RedirectError.class);
            put(302, RedirectError.class);
            put(303, RedirectError.class);
            put(304, RedirectError.class);
            put(305, RedirectError.class);
            put(306, RedirectError.class);
            put(307, RedirectError.class);
            put(308, RedirectError.class);
            put(401, UnauthorizedError.class);
            put(402, PaymentError.class);
            put(403, ForbiddenError.class);
            put(404, NotFoundError.class);
            put(405, MethodNotAllowedError.class);
            put(408, TimeoutError.class);
            put(422, InvalidRequestError.class);
            put(429, RateLimitError.class);
            put(444, UnknownApiError.class);
            put(500, InternalServerError.class);
            put(503, ServiceUnavailablError.class);
            put(504, GatewayTimeoutError.class);
        }};

        for (Map.Entry<Integer, Class<?>> entry: apiErrorsMap.entrySet()) {
            EasyPostException exception = assertThrows(EasyPostException.class,
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
     * @throws EasyPostException
     */
    @Test
    public void testExceptionErrorMessageParsing() throws EasyPostException {
        String errorMessageStringJson =
            "{\"error\": {\"code\": \"ERROR_CODE\", \"message\": \"ERROR_MESSAGE_1\", \"errors\": []}}";
        EasyPostException exception = assertThrows(EasyPostException.class,
            () -> handleAPIError(errorMessageStringJson, 400));

        assertEquals("ERROR_MESSAGE_1", exception.getMessage());
    }

    /**
     * Test parsing error message that is an array.
     *
     * @throws EasyPostException
     */
    @Test
    public void testExceptionErrorArrayParsing() throws EasyPostException {
        String errorMessageArrayJson = "{\"error\": {\"code\": \"ERROR_CODE\", \"message\":" +
            "[\"ERROR_MESSAGE_1\", \"ERROR_MESSAGE_2\"], \"errors\": []}}";

        EasyPostException exception = assertThrows(EasyPostException.class,
            () -> handleAPIError(errorMessageArrayJson, 400));

        assertEquals("ERROR_MESSAGE_1, ERROR_MESSAGE_2", exception.getMessage());
    }
}
