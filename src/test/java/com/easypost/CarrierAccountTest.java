package com.easypost;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.model.CarrierType;
import com.easypost.model.CarrierAccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrierAccountTest {
    private static CarrierAccount globalCarrierAccount;
    private static Map<String, Object> params = new HashMap<>();

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_PROD_API_KEY");

        Map<String, Object> credentials = new HashMap<>();

        credentials.put("account_number", "A1A1A1");
        credentials.put("user_id", "USERID");
        credentials.put("password", "PASSWORD");
        credentials.put("access_license_number", "ALN");
        params.put("type", "UpsAccount");
        params.put("credentials", credentials);

        globalCarrierAccount = CarrierAccount.create(params);
    }

    /**
     * Test creating a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(1)
    public void testCreate() throws EasyPostException {
        CarrierAccount carrierAccount = CarrierAccount.create(params);

        assertTrue(carrierAccount instanceof CarrierAccount);
        assertTrue(carrierAccount.getId().startsWith("ca_"));

        carrierAccount.delete(); // Delete the carrier account once it's done being tested.
    }

    /**
     * Test retrieving a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(2)
    public void testRetrieve() throws EasyPostException {
        CarrierAccount retrieveCarrierAccount = CarrierAccount.retrieve(globalCarrierAccount.getId());

        assertTrue(retrieveCarrierAccount instanceof CarrierAccount);
        assertTrue(retrieveCarrierAccount.getId().startsWith("ca_"));
        assertThat(globalCarrierAccount).usingRecursiveComparison().isEqualTo(retrieveCarrierAccount);
    }

    /**
     * Test retrieving all carrier accounts.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(3)
    public void testAll() throws EasyPostException {
        List<CarrierAccount> carrierAccounts = CarrierAccount.all(null);

        assertTrue(carrierAccounts.stream().allMatch(carrier -> carrier instanceof CarrierAccount));
    }

    /**
     * Test updating a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(4)
    public void testUpdate() throws EasyPostException {
        String testDescription = "My custom description";
        Map<String, Object> updateParams = new HashMap<>();

        updateParams.put("description", testDescription);

        globalCarrierAccount = CarrierAccount.create(params);

        CarrierAccount updatedCarrierAccount = globalCarrierAccount.update(updateParams);

        assertTrue(updatedCarrierAccount instanceof CarrierAccount);
        assertTrue(updatedCarrierAccount.getId().startsWith("ca_"));
        assertEquals(testDescription, updatedCarrierAccount.getDescription());
    }

    /**
     * Test deleting a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(5)
    public void testDelete() throws EasyPostException {
        globalCarrierAccount.delete();

        assertTrue(globalCarrierAccount instanceof CarrierAccount);
    }

    /**
     * Test retrieving the carrier account types available.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testTypes() throws EasyPostException{
        List<CarrierType> types = CarrierType.all();

        assertTrue(types instanceof List);
    }
}
