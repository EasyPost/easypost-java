package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.FedExAccountValidationResponse;
import com.easypost.model.FedExGeneratePinResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class FedExTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("fedex", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Test creating an FedExs Session.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testVerifyBillingAddress() throws EasyPostException {
        vcr.setUpTest("verify_billing_address");

        HashMap<String, Object> addressValidation = new HashMap<>();
        addressValidation.put("name", "EasyPost Test 2");
        addressValidation.put("street1", "2600 N ASHTON BLVD");
        addressValidation.put("street2", "APT 325");
        addressValidation.put("city", "Lehi");
        addressValidation.put("state", "UT");
        addressValidation.put("postal_code", "84043");
        addressValidation.put("country_code", "US");

        HashMap<String, Object> params = new HashMap<>();
        params.put("address_validation", addressValidation);

        FedExAccountValidationResponse response = vcr.client.fedex.verifyBillingAddress("206666561", params);

        assertEquals("FedExsSession", response.getObject());
    }

    /**
     * Test creating an FedExs Session.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGeneratePin() throws EasyPostException {
        vcr.setUpTest("generate_pin");

        FedExGeneratePinResponse response = vcr.client.fedex.generatePin("206666561", "SMS");

        assertEquals("FedExsSession", response.getMessage());
    }

    /**
     * Test creating an FedExs Session.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testValidatePin() throws EasyPostException {
        vcr.setUpTest("validate_pin");

        HashMap<String, Object> pinValidation = new HashMap<>();
        pinValidation.put("name", "EasyPost Test 2");
        pinValidation.put("pin_code", "784365");

        HashMap<String, Object> params = new HashMap<>();
        params.put("pin_validation", pinValidation);

        FedExAccountValidationResponse response = vcr.client.fedex.validatePin("206666561", params);

        assertEquals("FedExsSession", response.getObject());
    }
}
