package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.easypost.model.AddressThreadSafe;
import com.easypost.service.EasyPostClient;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;

public class ThreadSafeTest {
    private static TestUtils.VCR vcr;

    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("address_thread_safe", TestUtils.ApiKey.TEST);
    }

    // Test creating/retrieving/verifying an address (thread-safe) with EasyPostClient object
    @Test
    public void testAddress() throws EasyPostException {
        vcr.setUpTest("create");
        EasyPostClient client = new EasyPostClient(System.getenv("EASYPOST_TEST_API_KEY"));
        
        AddressThreadSafe address = client.Address().create(Fixtures.caAddress1());
        assertInstanceOf(AddressThreadSafe.class, address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("388 Townsend St", address.getStreet1());

        AddressThreadSafe retrievedAddress = client.Address().retrieve(address.getId());
        assertTrue(address.equals(retrievedAddress));

        Address verifiedAddress = client.Address().verify(retrievedAddress);
        assertNotNull(verifiedAddress);
    }

    // Test creating multiple EasyPostClient objects
    @Test void testMulitpleClient() {
        EasyPostClient client1 = new EasyPostClient("123");
        EasyPostClient client2 = new EasyPostClient("321", 10000, 20000);

        assertTrue(client1.getApiKey() == "123");
        assertTrue(client1.getConnectionTimeout() == 30000);
        assertTrue(client1.getReadtimeout() == 60000);
        assertTrue(client2.getApiKey() == "321");
        assertTrue(client2.getConnectionTimeout() == 10000);
        assertTrue(client2.getReadtimeout() == 20000);
    }
}
