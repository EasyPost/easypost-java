package com.easypost;

import com.easypost.exception.APIException;
import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.model.CarrierAccount;
import com.easypost.model.CarrierType;
import com.easypost.model.Pickup;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
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

    private static CarrierAccount createBasicCarrierAccount() throws EasyPostException {
        CarrierAccount carrierAccount = vcr.client.carrierAccount.create(Fixtures.basicCarrierAccount());
        testCarrierAccountId = carrierAccount.getId(); // trigger deletion after test
        return carrierAccount;
    }

    private static List<Map.Entry<String, String>> flattenCustomCarrierRouteMapping() {
        List<Map.Entry<String, String>> routes = new ArrayList<>();
        for (Map.Entry<String, List<String>> endpointEntry : Constants.CarrierAccountTypes
                .CARRIER_ACCOUNT_CREATE_ENDPOINT_TYPES.entrySet()) {
            for (String carrierAccountType : endpointEntry.getValue()) {
                routes.add(new AbstractMap.SimpleEntry<>(carrierAccountType, endpointEntry.getKey()));
            }
        }
        return routes;
    }

    private static Map<String, Object> carrierCreatePayloadForEndpoint(String endpoint) {
        Map<String, Object> payload = new HashMap<>();
        if ("carrier_accounts/open".equals(endpoint) || "carrier_accounts/register".equals(endpoint)) {
            payload.put("registration_data", new HashMap<>());
        }
        return payload;
    }

    private static String loadCarrierAccountCassette(String cassetteName) throws Exception {
        Path cassettePath = Paths.get(TestUtils.getSourceFileDirectory(), "src/test/cassettes/carrier_account",
                cassetteName + ".json");
        return new String(Files.readAllBytes(cassettePath), StandardCharsets.UTF_8);
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
     * Test routing carrier account create calls to the proper endpoint.
     *
     * @throws Exception when the request fails.
     */
    @Test
    public void testCreateUrlRouting() throws Exception {
        for (Map.Entry<String, String> route : flattenCustomCarrierRouteMapping()) {
            String carrierAccountType = route.getKey();
            String expectedEndpoint = route.getValue();
            String cassetteName = "create_url_routing_" + carrierAccountType;

            vcr.setUpTest(cassetteName);

            Map<String, Object> payload = new HashMap<>();
            payload.put("type", carrierAccountType);
            payload.putAll(carrierCreatePayloadForEndpoint(expectedEndpoint));

            try {
                CarrierAccount carrierAccount = vcr.client.carrierAccount.create(payload);
                assertInstanceOf(CarrierAccount.class, carrierAccount);
                assertTrue(carrierAccount.getId().startsWith("ca_"));
                vcr.client.carrierAccount.delete(carrierAccount.getId());
            } catch (APIException e) {
                Integer statusCode = e.getStatusCode();
                assertTrue(statusCode == null || (statusCode != 404 && statusCode != 500));
            }

            String cassette = loadCarrierAccountCassette(cassetteName);
            assertTrue(cassette.contains("/v2/" + expectedEndpoint));
            if ("carrier_accounts/register_oauth".equals(expectedEndpoint)) {
                assertTrue(cassette.contains("carrier_account_oauth_registrations"));
            } else {
                assertTrue(cassette.contains("carrier_account"));
            }
        }
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
        MockedStatic<Requestor> requestMock = Mockito.mockStatic(Requestor.class);

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
