package com.easypost;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.FedExAccountValidationResponse;
import com.easypost.model.FedExRequestPinResponse;

public final class FedExRegistrationTest {
    private static TestUtils.VCR vcr;
    private static MockedStatic<Requestor> requestMock = Mockito.mockStatic(Requestor.class);

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("fedex_registration", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Release the static mock once it has been used.
     */
    @AfterAll
    public static void cleanup() {
        requestMock.close();
    }

    /**
     * Test registering a billing address.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRegisterAddress() throws EasyPostException {
        String fedexAccountNumber = "123456789";
        Map<String, Object> addressValidation = new java.util.HashMap<>();
        addressValidation.put("name", "BILLING NAME");
        addressValidation.put("street1", "1234 BILLING STREET");
        addressValidation.put("city", "BILLINGCITY");
        addressValidation.put("state", "ST");
        addressValidation.put("postal_code", "12345");
        addressValidation.put("country_code", "US");

        Map<String, Object> easypostDetails = new java.util.HashMap<>();
        easypostDetails.put("carrier_account_id", "ca_123");

        Map<String, Object> params = new java.util.HashMap<>();
        params.put("address_validation", addressValidation);
        params.put("easypost_details", easypostDetails);

        String jsonResponse = "{\"email_address\":null,\"options\":[\"SMS\",\"CALL\",\"INVOICE\"],"
                + "\"phone_number\":\"***-***-9721\"}";
        FedExAccountValidationResponse expectedResponse = Constants.Http.GSON.fromJson(jsonResponse,
                FedExAccountValidationResponse.class);

        requestMock.when(() -> Requestor.request(
                RequestMethod.POST,
                String.format("fedex_registrations/%s/address", fedexAccountNumber),
                params,
                FedExAccountValidationResponse.class,
                vcr.client)).thenReturn(expectedResponse);

        FedExAccountValidationResponse response = vcr.client.fedexRegistration.registerAddress(fedexAccountNumber,
                params);
        assertNull(response.getEmailAddress());
        assertTrue(response.getOptions().contains("SMS"));
        assertTrue(response.getOptions().contains("CALL"));
        assertTrue(response.getOptions().contains("INVOICE"));
        assertEquals("***-***-9721", response.getPhoneNumber());
    }

    /**
     * Test requesting a pin.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRequestPin() throws EasyPostException {
        String fedexAccountNumber = "123456789";
        Map<String, Object> pinMethodMap = new java.util.HashMap<>();
        pinMethodMap.put("option", "SMS");
        Map<String, Object> params = new java.util.HashMap<>();
        params.put("pin_method", pinMethodMap);

        String jsonResponse = "{\"message\":\"sent secured Pin\"}";
        FedExRequestPinResponse pinResponse = Constants.Http.GSON.fromJson(jsonResponse, FedExRequestPinResponse.class);

        requestMock.when(() -> Requestor.request(
                RequestMethod.POST,
                String.format("fedex_registrations/%s/pin",
                        fedexAccountNumber),
                params,
                FedExRequestPinResponse.class,
                vcr.client))
                .thenReturn(pinResponse);

        FedExRequestPinResponse response = vcr.client.fedexRegistration.requestPin(fedexAccountNumber, "SMS");
        assertEquals("sent secured Pin", response.getMessage());
    }

    /**
     * Test validating a pin.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testValidatePin() throws EasyPostException {
        String fedexAccountNumber = "123456789";
        Map<String, Object> pinValidation = new java.util.HashMap<>();
        pinValidation.put("pin_code", "123456");
        pinValidation.put("name", "BILLING NAME");

        Map<String, Object> easypostDetails = new java.util.HashMap<>();
        easypostDetails.put("carrier_account_id", "ca_123");

        Map<String, Object> params = new java.util.HashMap<>();
        params.put("pin_validation", pinValidation);
        params.put("easypost_details", easypostDetails);

        String jsonResponse = "{\"id\":\"ca_123\",\"object\":\"CarrierAccount\",\"type\":\"FedexAccount\","
                + "\"credentials\":{\"account_number\":\"123456789\",\"mfa_key\":\"123456789-XXXXX\"}}";
        FedExAccountValidationResponse expectedResponse = Constants.Http.GSON.fromJson(jsonResponse,
                FedExAccountValidationResponse.class);

        requestMock.when(() -> Requestor.request(
                RequestMethod.POST,
                String.format("fedex_registrations/%s/pin/validate", fedexAccountNumber),
                params,
                FedExAccountValidationResponse.class,
                vcr.client)).thenReturn(expectedResponse);

        FedExAccountValidationResponse response = vcr.client.fedexRegistration.validatePin(fedexAccountNumber, params);
        assertEquals("ca_123", response.getId());
        assertEquals("CarrierAccount", response.getObject());
        assertEquals("FedexAccount", response.getType());
        assertEquals("123456789", response.getCredentials().get("account_number"));
        assertEquals("123456789-XXXXX", response.getCredentials().get("mfa_key"));
    }

    /**
     * Test submitting details about an invoice.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testSubmitInvoice() throws EasyPostException {
        String fedexAccountNumber = "123456789";
        Map<String, Object> invoiceValidation = new java.util.HashMap<>();
        invoiceValidation.put("name", "BILLING NAME");
        invoiceValidation.put("invoice_number", "INV-12345");
        invoiceValidation.put("invoice_date", "2025-12-08");
        invoiceValidation.put("invoice_amount", "100.00");
        invoiceValidation.put("invoice_currency", "USD");

        Map<String, Object> easypostDetails = new java.util.HashMap<>();
        easypostDetails.put("carrier_account_id", "ca_123");

        Map<String, Object> params = new java.util.HashMap<>();
        params.put("invoice_validation", invoiceValidation);
        params.put("easypost_details", easypostDetails);

        String jsonResponse = "{\"id\":\"ca_123\",\"object\":\"CarrierAccount\",\"type\":\"FedexAccount\","
                + "\"credentials\":{\"account_number\":\"123456789\",\"mfa_key\":\"123456789-XXXXX\"}}";
        FedExAccountValidationResponse expectedResponse = Constants.Http.GSON.fromJson(jsonResponse,
                FedExAccountValidationResponse.class);

        requestMock.when(() -> Requestor.request(
                RequestMethod.POST,
                String.format("fedex_registrations/%s/invoice", fedexAccountNumber),
                params,
                FedExAccountValidationResponse.class,
                vcr.client)).thenReturn(expectedResponse);

        FedExAccountValidationResponse response = vcr.client.fedexRegistration.submitInvoice(fedexAccountNumber,
                params);
        assertEquals("ca_123", response.getId());
        assertEquals("CarrierAccount", response.getObject());
        assertEquals("FedexAccount", response.getType());
        assertEquals("123456789", response.getCredentials().get("account_number"));
        assertEquals("123456789-XXXXX", response.getCredentials().get("mfa_key"));
    }
}
