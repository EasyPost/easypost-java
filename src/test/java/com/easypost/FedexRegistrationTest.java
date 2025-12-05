package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.FedexRegistration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class FedexRegistrationTest {
    private static TestUtils.VCR vcr;
    private static final String TEST_FEDEX_ACCOUNT_NUMBER = "123456789";

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("fedex_registration", TestUtils.ApiKey.TEST);
    }

    /**
     * Test registering a FedEx account address.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRegisterAddress() throws EasyPostException {
        vcr.setUpTest("register_address");

        Map<String, Object> addressValidation = new HashMap<>();
        addressValidation.put("name", "Test Account");
        addressValidation.put("street1", "179 N Harbor Dr");
        addressValidation.put("city", "Redondo Beach");
        addressValidation.put("state", "CA");
        addressValidation.put("postal_code", "90277");
        addressValidation.put("country_code", "US");

        Map<String, Object> easypostDetails = new HashMap<>();
        easypostDetails.put("carrier_account_id", "ca_123456778");

        Map<String, Object> params = new HashMap<>();
        params.put("address_validation", addressValidation);
        params.put("easypost_details", easypostDetails);

        FedexRegistration registration = vcr.client.fedexRegistration.registerAddress(TEST_FEDEX_ACCOUNT_NUMBER,
                params);

        assertInstanceOf(FedexRegistration.class, registration);
        assertNotNull(registration.getId());
    }

    /**
     * Test requesting a PIN for FedEx account verification.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRequestPin() throws EasyPostException {
        vcr.setUpTest("request_pin");

        Map<String, Object> pinMethod = new HashMap<>();
        pinMethod.put("option", "SMS");

        Map<String, Object> params = new HashMap<>();
        params.put("pin_method", pinMethod);

        FedexRegistration registration = vcr.client.fedexRegistration.requestPin(TEST_FEDEX_ACCOUNT_NUMBER, params);

        assertInstanceOf(FedexRegistration.class, registration);
        assertNotNull(registration.getId());
    }

    /**
     * Test validating a PIN for FedEx account verification.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testValidatePin() throws EasyPostException {
        vcr.setUpTest("validate_pin");

        Map<String, Object> pinValidation = new HashMap<>();
        pinValidation.put("pin_code", "123456");
        pinValidation.put("name", "Test Account");

        Map<String, Object> easypostDetails = new HashMap<>();
        easypostDetails.put("carrier_account_id", "ca_123456778");

        Map<String, Object> params = new HashMap<>();
        params.put("pin_validation", pinValidation);
        params.put("easypost_details", easypostDetails);

        FedexRegistration registration = vcr.client.fedexRegistration.validatePin(TEST_FEDEX_ACCOUNT_NUMBER, params);

        assertInstanceOf(FedexRegistration.class, registration);
        assertNotNull(registration.getId());
    }

    /**
     * Test submitting invoice information for FedEx account registration.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testSubmitInvoice() throws EasyPostException {
        vcr.setUpTest("submit_invoice");

        Map<String, Object> invoiceValidation = new HashMap<>();
        invoiceValidation.put("name", "Test Account");
        invoiceValidation.put("invoice_number", "INV-12345");
        invoiceValidation.put("invoice_date", "2025-01-01");
        invoiceValidation.put("invoice_amount", "100.00");
        invoiceValidation.put("invoice_currency", "USD");

        Map<String, Object> easypostDetails = new HashMap<>();
        easypostDetails.put("carrier_account_id", "ca_123456778");

        Map<String, Object> params = new HashMap<>();
        params.put("invoice_validation", invoiceValidation);
        params.put("easypost_details", easypostDetails);

        FedexRegistration registration = vcr.client.fedexRegistration.submitInvoice(TEST_FEDEX_ACCOUNT_NUMBER, params);

        assertInstanceOf(FedexRegistration.class, registration);
        assertNotNull(registration.getId());
    }

}
