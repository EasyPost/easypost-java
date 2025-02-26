package com.easypost;

import com.easypost.exception.API.InvalidRequestError;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.model.Address;
import com.easypost.model.AddressCollection;
import com.easypost.model.AddressDetail;
import com.easypost.model.AddressVerificationFieldError;
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
import static org.junit.jupiter.api.Assertions.assertNull;
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
     * @throws EasyPostException if an exception is thrown.
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
        assertEquals("Address", address.getObject());
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

        // Creating normally (without specifying "verify") will make the address, perform no verifications
        Address address = vcr.client.address.create(addressData);

        assertInstanceOf(Address.class, address);
        assertNull(address.getVerifications().getDelivery());
        assertNull(address.getVerifications().getZip4());

        // Creating with verify would make the address and perform verifications
        // internally, we're just checking for the presence of "verify" in the dictionary, so the value doesn't matter
        addressData.put("verify", true);
        address = vcr.client.address.create(addressData);

        assertInstanceOf(Address.class, address);

        assertEquals(false, address.getVerifications().getDelivery().getSuccess());
        assertInstanceOf(AddressDetail.class, address.getVerifications().getDelivery().getDetails());
        AddressVerificationFieldError addressVerificationFieldErrorGetDelivery = address.getVerifications().getDelivery().getErrors().get(0);
        assertEquals("E.ADDRESS.NOT_FOUND", addressVerificationFieldErrorGetDelivery.getCode());
        assertEquals("address", addressVerificationFieldErrorGetDelivery.getField());
        assertNull(addressVerificationFieldErrorGetDelivery.getSuggestion());
        assertEquals("Address not found", addressVerificationFieldErrorGetDelivery.getMessage());

        assertEquals(false, address.getVerifications().getZip4().getSuccess());
        assertNull(address.getVerifications().getZip4().getDetails());
        AddressVerificationFieldError addressVerificationFieldErrorZip4 = address.getVerifications().getZip4().getErrors().get(0);
        assertEquals("E.ADDRESS.NOT_FOUND", addressVerificationFieldErrorZip4.getCode());
        assertEquals("address", addressVerificationFieldErrorZip4.getField());
        assertNull(addressVerificationFieldErrorZip4.getSuggestion());
        assertEquals("Address not found", addressVerificationFieldErrorZip4.getMessage());
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

        // Creating normally (without specifying "verify") will make the address, perform no verifications
        Address address = vcr.client.address.create(addressData);

        assertInstanceOf(Address.class, address);
        assertNull(address.getVerifications().getDelivery());
        assertNull(address.getVerifications().getZip4());

        // Creating with verify would make the address and perform verifications
        // internally, we're just checking for the presence of "verify" in the dictionary, so the value doesn't matter
        List<Boolean> verificationList = new ArrayList<>();
        verificationList.add(true);
        addressData.put("verify", verificationList);

        address = vcr.client.address.create(addressData);

        assertInstanceOf(Address.class, address);
        assertNotNull(address.getVerifications().getDelivery());
        assertNotNull(address.getVerifications().getZip4());
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
        assertTrue(addressesList.stream().allMatch(address -> address != null));
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
            AddressCollection nextPage = vcr.client.address.getNextPage(collection, Fixtures.pageSize());

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

        assertInstanceOf(Address.class, verifiedAddress);
        assertTrue(verifiedAddress.getId().startsWith("adr_"));
        assertEquals("388 TOWNSEND ST APT 20", verifiedAddress.getStreet1());
    }

    /**
     * Test creating invalid address creation to see if the error has correct properties.
     *
     * @throws EasyPostException if an exception is thrown.
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
