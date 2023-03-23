package com.easypost;

import com.easypost.exception.API.InvalidRequestError;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.model.Address;
import com.easypost.model.AddressCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class AddressTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("address", TestUtils.ApiKey.TEST);
    }

    /**
     * Create a basic address.
     *
     * @return basic Address object
     * @throws EasyPostException
     */
    public Address createBasicAddress() throws EasyPostException {
        return vcr.client.address.create(Fixtures.caAddress1());
    }

    /**
     * Test creating an address.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        Address address = createBasicAddress();

        assertInstanceOf(Address.class, address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("388 Townsend St", address.getStreet1());
    }

    /**
     * Test creating an address with the verify param.
     * We purposefully pass in slightly incorrect data to get the corrected address
     * back once verified.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateVerify() throws EasyPostException {
        vcr.setUpTest("create_verify");

        Map<String, Object> addressData = Fixtures.incorrectAddress();
        addressData.put("verify", true);

        Address address = vcr.client.address.create(addressData);

        assertInstanceOf(Address.class, address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("417 MONTGOMERY ST FL 5", address.getStreet1());
    }

    /**
     * Test creating an address with the verify_strict param.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateVerifyStrict() throws EasyPostException {
        vcr.setUpTest("create_verify_strict");

        Map<String, Object> addressData = Fixtures.caAddress1();
        addressData.put("verify_strict", true);

        Address address = vcr.client.address.create(addressData);

        assertInstanceOf(Address.class, address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("388 TOWNSEND ST APT 20", address.getStreet1());
    }

    /**
     * Test creating an address with the verify param.
     * We purposefully pass in slightly incorrect data to get the corrected address
     * back once verified.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateVerifyArray() throws EasyPostException {
        vcr.setUpTest("create_verify_array");

        Map<String, Object> addressData = Fixtures.incorrectAddress();
        List<Boolean> verificationList = new ArrayList<>();
        verificationList.add(true);
        addressData.put("verify", verificationList);

        Address address = vcr.client.address.create(addressData);

        assertInstanceOf(Address.class, address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("417 MONTGOMERY ST FL 5", address.getStreet1());
    }

    /**
     * Test retrieving an address.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        Address address = createBasicAddress();
        Address retrievedAddress = vcr.client.address.retrieve(address.getId());

        assertInstanceOf(Address.class, retrievedAddress);
        assertTrue(address.equals(retrievedAddress));
    }

    /**
     * Test retrieving all addresses.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());

        AddressCollection addresses = vcr.client.address.all(params);

        List<Address> addressesList = addresses.getAddresses();

        assertTrue(addressesList.size() <= Fixtures.pageSize());
        assertNotNull(addresses.getHasMore());
        assertTrue(addressesList.stream().allMatch(address -> address instanceof Address));
    }

    /**
     * Test retrieving the next page.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetNextPage() throws EasyPostException {
        vcr.setUpTest("get_next_page");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());
        AddressCollection collection = vcr.client.address.all(params);

        try {
            AddressCollection nextPage = vcr.client.address.getNextPage(collection);

            assertNotNull(nextPage);

            String firstIdOfFirstPage = collection.getAddresses().get(0).getId();
            String firstIdOfSecondPage = nextPage.getAddresses().get(0).getId();

            assertNotEquals(firstIdOfFirstPage, firstIdOfSecondPage);
        } catch (EndOfPaginationError e) { // There's no next page, that's not a failure
            assertTrue(true);
        } catch (Exception e) { // Any other exception is a failure
            fail();
        }
    }

    /**
     * Test creating a verified address.
     * We purposefully pass in slightly incorrect data to get the corrected address
     * back once verified.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateAndVerify() throws EasyPostException {
        vcr.setUpTest("create_and_verify");

        Map<String, Object> addressData = Fixtures.caAddress1();

        Address address = vcr.client.address.createAndVerify(addressData);

        assertInstanceOf(Address.class, address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("388 TOWNSEND ST APT 20", address.getStreet1());
    }

    /**
     * Test verifying an existing address.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testVerify() throws EasyPostException {
        vcr.setUpTest("verify");

        Address address = createBasicAddress();

        Address verifiedAddress = vcr.client.address.verify(address.getId());

        assertInstanceOf(Address.class, address);
        assertTrue(verifiedAddress.getId().startsWith("adr_"));
        assertEquals("388 TOWNSEND ST APT 20", verifiedAddress.getStreet1());
    }

    /**
     * Test creating invalid address creation to see if the error has correct properties.
     *
     * @throws EasyPostException
     */
    @Test
    public void testInvalidAddressCreation() throws EasyPostException {
        vcr.setUpTest("error_address_creation");
        InvalidRequestError exception =
                assertThrows(InvalidRequestError.class, () -> vcr.client.address.createAndVerify(null));

        assertEquals("PARAMETER.REQUIRED", exception.getCode());
        assertEquals(422, exception.getStatusCode());
        assertEquals("Missing required parameter.", exception.getMessage());
    }
}
