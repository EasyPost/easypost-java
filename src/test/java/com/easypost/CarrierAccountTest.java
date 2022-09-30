package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.CarrierAccount;
import com.easypost.model.CarrierType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CarrierAccountTest {
    private static String testCarrierAccountId = null;

    private static TestUtils.VCR vcr;

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
                CarrierAccount carrierAccount = CarrierAccount.retrieve(testCarrierAccountId);
                carrierAccount.delete();
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

    private static CarrierAccount createBasicCarrierAccount() throws EasyPostException {
        CarrierAccount carrierAccount = CarrierAccount.create(Fixtures.basicCarrierAccount());
        testCarrierAccountId = carrierAccount.getId(); // trigger deletion after test
        return carrierAccount;
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

        CarrierAccount retrieveCarrierAccount = CarrierAccount.retrieve(carrierAccount.getId());

        assertInstanceOf(CarrierAccount.class, retrieveCarrierAccount);
        assertTrue(retrieveCarrierAccount.getId().startsWith("ca_"));
        assertThat(carrierAccount).usingRecursiveComparison().isEqualTo(retrieveCarrierAccount);
    }

    /**
     * Test retrieving all carrier accounts.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        List<CarrierAccount> carrierAccounts = CarrierAccount.all();

        assertTrue(carrierAccounts.stream().allMatch(carrier -> carrier instanceof CarrierAccount));
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

        CarrierAccount updatedCarrierAccount = carrierAccount.update(updateParams);

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

        assertDoesNotThrow(() -> carrierAccount.delete());
    }

    /**
     * Test retrieving the carrier account types available.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testTypes() throws EasyPostException {
        vcr.setUpTest("types");

        List<CarrierType> types = CarrierType.all();

        assertInstanceOf(List.class, types);
        assertTrue(types.stream().allMatch(type -> type instanceof CarrierType));
    }
}
