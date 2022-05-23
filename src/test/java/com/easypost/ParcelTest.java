package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Parcel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParcelTest {
    private static TestUtils.VCR _vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        _vcr = new TestUtils.VCR("parcel", TestUtils.ApiKey.TEST);
    }

    /**
     * Create a parcel.
     */
    private static Parcel createBasicParcel() throws EasyPostException {
        return Parcel.create(Fixture.basicParcel());
    }

    /**
     * Test creating a Parcel.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        _vcr.setUpTest("create");

        Parcel parcel = createBasicParcel();

        assertInstanceOf(Parcel.class, parcel);
        assertTrue(parcel.getId().startsWith("prcl_"));
        assertEquals(15.4, parcel.getWeight(), 0.01);
    }

    /**
     * Test retrieving a Parcel.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        _vcr.setUpTest("retrieve");

        Parcel parcel = createBasicParcel();

        Parcel retrievedParcel = Parcel.retrieve(parcel.getId());

        assertInstanceOf(Parcel.class, retrievedParcel);
        assertThat(parcel).usingRecursiveComparison().isEqualTo(retrievedParcel);
    }
}
