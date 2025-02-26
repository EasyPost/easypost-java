package com.easypost;

import com.easypost.exception.API.InvalidRequestError;
import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.model.CarrierAccount;
import com.easypost.model.CarrierType;
import com.easypost.model.Pickup;
import com.easypost.model.Shipment;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CarrierAccountTest {
    private static String testCarrierAccountId = null;

    private static TestUtils.VCR vcr;

    private static MockedStatic<Requestor> requestMock = Mockito.mockStatic(Requestor.class);

    private static CarrierAccount createBasicCarrierAccount() throws EasyPostException {
        // This method creates DhlEcsAccount carrier account.
        CarrierAccount carrierAccount = vcr.client.carrierAccount.create(Fixtures.basicCarrierAccount());
        testCarrierAccountId = carrierAccount.getId(); // trigger deletion after test
        return carrierAccount;
    }

    private static CarrierAccount createUpsCarrierAccount() throws EasyPostException {
        Map<String, Object> data = new HashMap<>();
        data.put("type", "UpsAccount");
        data.put("account_number", "123456789");

        CarrierAccount upsAccount = vcr.client.carrierAccount.create(data);
        testCarrierAccountId = upsAccount.getId(); // trigger deletion after test
        return upsAccount;
    }

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("carrier_account", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Clean up test attributes after each unit test.
     */
    @AfterEach
    public void cleanup() {
        if (testCarrierAccountId != null) {
            try {
                CarrierAccount carrierAccount = vcr.client.carrierAccount.retrieve(testCarrierAccountId);
                vcr.client.carrierAccount.delete(carrierAccount.getId());
                testCarrierAccountId = null;
            } catch (Exception e) {
                // in case we try to delete something that's already been deleted
            }
        }
    }

    /**
     * Test creating a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        CarrierAccount carrierAccount = createBasicCarrierAccount();

        assertInstanceOf(CarrierAccount.class, carrierAccount);
        assertTrue(carrierAccount.getId().startsWith("ca_"));
        assertEquals("DhlEcsAccount", carrierAccount.getType());
    }

    /**
     * Test creating a carrier account with a custom workflow.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateWithCustomWorkflow() throws EasyPostException {
        vcr.setUpTest("create_with_custom_workflow");

        Map<String, Object> data = new HashMap<>();
        data.put("type", "FedexAccount");
        data.put("registration_data", ImmutableMap.of("some", "data"));

        try {
            CarrierAccount carrierAccount = vcr.client.carrierAccount.create(data);
            testCarrierAccountId = carrierAccount.getId();  // clean up after test, should never get here
        } catch (InvalidRequestError e) {
            // We're sending bad data to the API, so we expect an error
            assertEquals(422, e.getStatusCode());
            // We expect one of the sub-errors to be regarding a missing field
            // assertTrue(e.getErrors().stream().anyMatch(error -> error.getField().equals("account_number") &&
            //         error.getMessage().equals("must be present and a string")));
        }
    }


    /**
     * Test creating a UPS carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateWithUPS() throws EasyPostException {
        vcr.setUpTest("create_with_ups");

        CarrierAccount upsAccount = createUpsCarrierAccount();

        assertInstanceOf(CarrierAccount.class, upsAccount);
        assertTrue(upsAccount.getId().startsWith("ca_"));
        assertEquals("UpsAccount", upsAccount.getType());
    }

    /**
     * Test retrieving a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        CarrierAccount carrierAccount = createBasicCarrierAccount();

        CarrierAccount retrieveCarrierAccount = vcr.client.carrierAccount.retrieve(carrierAccount.getId());

        assertInstanceOf(CarrierAccount.class, retrieveCarrierAccount);
        assertTrue(retrieveCarrierAccount.getId().startsWith("ca_"));
        assertTrue(carrierAccount.equals(retrieveCarrierAccount));
    }

    /**
     * Test retrieving all carrier accounts.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        List<CarrierAccount> carrierAccounts = vcr.client.carrierAccount.all();

        assertTrue(carrierAccounts.stream().allMatch(carrier -> carrier != null));
    }

    /**
     * Test updating a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdate() throws EasyPostException {
        vcr.setUpTest("update");

        CarrierAccount carrierAccount = createBasicCarrierAccount();

        String testDescription = "My custom description";

        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("description", testDescription);

        CarrierAccount updatedCarrierAccount = vcr.client.carrierAccount.update(carrierAccount.getId(), updateParams);

        assertInstanceOf(CarrierAccount.class, carrierAccount);
        assertTrue(updatedCarrierAccount.getId().startsWith("ca_"));
        assertEquals(testDescription, updatedCarrierAccount.getDescription());
    }

    /**
     * Test updating an UPS account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdateUpsAccount() throws EasyPostException {
        vcr.setUpTest("update_ups");

        CarrierAccount upsAccount = createUpsCarrierAccount();
        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("account_number", "987654321");

        CarrierAccount updatedUpsAccount = vcr.client.carrierAccount.update(upsAccount.getId(), updateParams);

        assertInstanceOf(CarrierAccount.class, updatedUpsAccount);
        assertTrue(updatedUpsAccount.getId().startsWith("ca_"));
        assertEquals("UpsAccount", updatedUpsAccount.getType());
    }

    /**
     * Test deleting a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testDelete() throws EasyPostException {
        vcr.setUpTest("delete");

        CarrierAccount carrierAccount = createBasicCarrierAccount();

        assertDoesNotThrow(() -> vcr.client.carrierAccount.delete(carrierAccount.getId()));
    }

    /**
     * Test retrieving the carrier account types available.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testTypes() throws EasyPostException {
        vcr.setUpTest("types");

        List<CarrierType> types = vcr.client.carrierType.all();

        assertInstanceOf(List.class, types);
        assertTrue(types.stream().allMatch(type -> type != null));
    }

    /**
     * Test that the CarrierAccount fields are correctly deserialized from the API response.
     * None of the demo carrier accounts used in the above tests have credentials or test credentials fields,
     * so we need to use some mock data.
     */
    @Test
    public void testCarrierFieldsJsonDeserialization() {
        String carrierAccountJson = "[{\"id\":\"ca_123\",\"object\":\"CarrierAccount\"," +
                "\"fields\":{\"credentials\":{\"account_number\":{\"visibility\":\"visible\"," +
                "\"label\":\"DHL Account Number\",\"value\":\"123456\"},\"country\":{\"visibility\":\"visible\"," +
                "\"label\":\"Account Country Code (2 Letter)\",\"value\":\"US\"},\"site_id\":{\"visibility\":" +
                "\"visible\",\"label\":\"Site ID (Optional)\",\"value\": null },\"password\":{\"visibility\":" +
                "\"password\",\"label\":\"Password (Optional)\",\"value\":\"\"},\"is_reseller\":{\"visibility\":" +
                "\"checkbox\",\"label\":\"Reseller Account? (check if yes)\",\"value\":null}}}}]";
        CarrierAccount[] carrierAccounts = Constants.Http.GSON.fromJson(carrierAccountJson, CarrierAccount[].class);

        CarrierAccount carrierAccount = carrierAccounts[0];
        assertEquals("ca_123", carrierAccount.getId());
        assertEquals("CarrierAccount", carrierAccount.getObject());
        assertEquals("DHL Account Number",
                carrierAccount.getFields().getCredentials().get("account_number").getLabel());
    }

    /**
     * Test that the CarrierAccount fields are correctly serialized to the API request.
     */
    @Test
    public void testCarrierFieldsJsonSerialization() {
        String carrierAccountJson = "[{\"id\":\"ca_123\",\"object\":\"CarrierAccount\",\"fields\":{\"credentials\":" +
                "{\"account_number\":{\"visibility\":\"visible\",\"label\":\"DHL Account Number\"," +
                "\"value\":\"123456\"},\"country\":{\"visibility\":\"visible\",\"label\":" +
                "\"Account Country Code (2 Letter)\",\"value\":\"US\"},\"site_id\":{\"visibility\":\"visible\"," +
                "\"label\":\"Site ID (Optional)\",\"value\": null },\"password\":{\"visibility\":\"password\"," +
                "\"label\":\"Password (Optional)\",\"value\":\"\"},\"is_reseller\":{\"visibility\":\"checkbox\"," +
                "\"label\":\"Reseller Account? (check if yes)\",\"value\":null}}}}]";
        CarrierAccount[] carrierAccounts = Constants.Http.GSON.fromJson(carrierAccountJson, CarrierAccount[].class);
        CarrierAccount carrierAccount = carrierAccounts[0];

        // Prepare a parameter set for creating a pickup, using the carrier account object
        Map<String, Object> pickupData = Fixtures.basicPickup();
        pickupData.put("shipment", new Shipment());
        pickupData.put("carrier_accounts", new CarrierAccount[] { carrierAccount });

        // Avoid making a real request to the API, interested in pre-request serialization, not interested in response
        requestMock.when(() -> Requestor.request(Requestor.RequestMethod.POST, "pickups", pickupData, Shipment.class,
                vcr.client)).thenReturn(new Pickup());

        // This will throw an exception if the carrier account fields could not be serialized properly
        assertDoesNotThrow(() -> vcr.client.pickup.create(pickupData));

        // Close mock
        requestMock.close();
    }
}
