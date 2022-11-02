package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Parcel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ParcelTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("parcel", TestUtils.ApiKey.TEST);
    }

    /**
     * Test creating a Parcel.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        Parcel parcel = createBasicParcel();

        assertInstanceOf(Parcel.class, parcel);
        assertTrue(parcel.getId().startsWith("prcl_"));
        assertEquals(15.4, parcel.getWeight(), 0.01);
    }

    /**
     * Create a parcel.
     *
     * @return Parcel object
     */
    private static Parcel createBasicParcel() throws EasyPostException {
        return vcr.client.parcel.create(Fixtures.basicParcel());
    }

    /**
     * Test retrieving a Parcel.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        Parcel parcel = createBasicParcel();

        Parcel retrievedParcel = vcr.client.parcel.retrieve(parcel.getId());

        assertInstanceOf(Parcel.class, retrievedParcel);
        assertTrue(parcel.equals(retrievedParcel));
    }
}
