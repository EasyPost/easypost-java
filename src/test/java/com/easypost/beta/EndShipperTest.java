package com.easypost.beta;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.EasyPost;
import com.easypost.Fixture;
import com.easypost.exception.EasyPostException;
import com.easypost.model.beta.EndShipper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class EndShipperTest {
    
    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        EasyPost.apiKey = System.getenv("EASYPOST_PROD_API_KEY");
    }

    /**
     * Test creating an EndShipper object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        EndShipper endShipper = EndShipper.create(Fixture.endShipperAddress());

        assertTrue(endShipper instanceof EndShipper);
        assertTrue(endShipper.getId().startsWith("es_"));
        assertEquals("388 TOWNSEND ST APT 20", endShipper.getStreet1());
    }

    /**
     * Test retrieving an EndShipper object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException{
        EndShipper endShipper = EndShipper.create(Fixture.endShipperAddress());
        EndShipper retrievedEndShipper = EndShipper.retrieve(endShipper.getId());

        assertTrue(retrievedEndShipper instanceof EndShipper);
        assertEquals(endShipper.getStreet1(), retrievedEndShipper.getStreet1());
    }

    /**
     * Test retrieving all EndShipper objects.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("page_size", Fixture.pageSize());

        List<EndShipper> endShippers = EndShipper.all(params);

        assertTrue(endShippers.size() <= Fixture.pageSize());
        assertTrue(endShippers.stream().allMatch(address -> address instanceof EndShipper));
    }

    /**
     * Test updating an EndShipper object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdate() throws EasyPostException {
        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("name", "Jack Sparrow");
        updateParams.put("company", "EasyPost");
        updateParams.put("street1", "388 Townsend St");
        updateParams.put("street2", "Apt 20");
        updateParams.put("city", "San Francisco");
        updateParams.put("state", "CA");
        updateParams.put("zip", "94107");
        updateParams.put("country", "US");
        updateParams.put("phone", "9999999999");
        updateParams.put("email", "test@example.com");

        EndShipper endShipper = EndShipper.create(Fixture.endShipperAddress());

        EndShipper updatedEndShipper = endShipper.update(updateParams);

        assertTrue(updatedEndShipper instanceof EndShipper);
        assertTrue(updatedEndShipper.getId().startsWith("es_"));
        assertEquals("9999999999", updatedEndShipper.getPhone());
    }
}
