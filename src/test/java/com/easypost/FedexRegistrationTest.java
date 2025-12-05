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

        Map<String, Object> params = new HashMap<>();
        params.put("billing_address", Fixtures.caAddress1());

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

        Map<String, Object> params = new HashMap<>();
        params.put("pin_method", "SMS");

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

        Map<String, Object> params = new HashMap<>();
        params.put("pin", "123456");

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

        Map<String, Object> params = new HashMap<>();
        params.put("invoice_number", "INV-12345");
        params.put("invoice_date", "2025-01-01");

        FedexRegistration registration = vcr.client.fedexRegistration.submitInvoice(TEST_FEDEX_ACCOUNT_NUMBER, params);

        assertInstanceOf(FedexRegistration.class, registration);
        assertNotNull(registration.getId());
    }

    /**
     * Test that the name parameter is auto-generated when not provided.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAutoGenerateName() throws EasyPostException {
        vcr.setUpTest("auto_generate_name");

        Map<String, Object> params = new HashMap<>();
        params.put("pin_method", "EMAIL");

        FedexRegistration registration = vcr.client.fedexRegistration.requestPin(TEST_FEDEX_ACCOUNT_NUMBER, params);

        assertInstanceOf(FedexRegistration.class, registration);
        assertNotNull(registration.getId());
    }
}
