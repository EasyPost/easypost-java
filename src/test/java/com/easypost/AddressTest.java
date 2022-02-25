package com.easypost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.easypost.model.AddressCollection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class AddressTest {
    private static Address globalAddress;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        globalAddress = Address.create(Fixture.basicAddress());
    }

    /**
     * Test creating an address.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        Address address = Address.create(Fixture.basicAddress());

        assertTrue(address instanceof Address);
        assertEquals("388 Townsend St", address.getStreet1());
        assertTrue(address.getId().startsWith("adr_"));
    }

    /**
     * Test creating an address with verify_strict param.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateVerifyStrict() throws EasyPostException {
        Map<String, Object> addressData = Fixture.basicAddress();
        List<String> verificationList = new ArrayList<>();
        
        verificationList.add("true");
        addressData.put("verify_strict", verificationList);

        Address address = Address.create(addressData);

        assertTrue(address instanceof Address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("388 TOWNSEND ST APT 20", address.getStreet1());
    }

    /**
     * Test retrieving an address.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException{
        Address retrievedAddress = Address.retrieve(globalAddress.getId());

        assertTrue(retrievedAddress instanceof Address);
        assertTrue(retrievedAddress.getId().startsWith("adr_"));
        assertThat(globalAddress).usingRecursiveComparison().isEqualTo(retrievedAddress);
    }

    /**
     * Test retrieving all addresses.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException{
        Map<String, Object> params = new HashMap<>();

        params.put("page_size", Fixture.pageSize());

        AddressCollection addresses = Address.all(params);

        List<Address> addressesArray = addresses.getAddresses();

        assertTrue(addressesArray.size() <= Fixture.pageSize());
        assertNotNull(addresses.getHasMore());
        for(Address address: addressesArray) {
            assertTrue(address instanceof Address);
        }
    }

    /**
     * Test creating a verified address.
     * We purposefully pass in slightly incorrect data to get the corrected address back once verified.
     *
     *  @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateVerify() throws EasyPostException {
        Address address = Address.create(Fixture.incorrectAddressToVerify());

        assertTrue(address instanceof Address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("417 MONTGOMERY ST STE 500", address.getStreet1());
    }

    /**
     * Test creating a verified address.
     * We purposefully pass in slightly incorrect data to get the corrected address back once verified.
     *
     *  @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateAndVerify() throws EasyPostException {
        Address address = Address.createAndVerify(Fixture.incorrectAddressToVerify());

        assertTrue(address instanceof Address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("417 MONTGOMERY ST STE 500", address.getStreet1());
    }

    /**
     * Test we can verify an already created address.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testVerify() throws EasyPostException {
        globalAddress = globalAddress.verify();

        assertTrue(globalAddress instanceof Address);
        assertTrue(globalAddress.getId().startsWith("adr_"));
        assertEquals("388 TOWNSEND ST APT 20", globalAddress.getStreet1());
    }
}
