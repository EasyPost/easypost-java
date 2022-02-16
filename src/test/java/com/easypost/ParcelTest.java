package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Parcel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ParcelTest {
    private static Parcel globalParcel;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        globalParcel = Parcel.create(Fixture.basicParcel());
    }

    /**
     * Test creating a Parcel.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        Parcel parcel = Parcel.create(Fixture.basicParcel());

        assertTrue(parcel instanceof Parcel);
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
        Parcel retrievedParcel = Parcel.retrieve(globalParcel.getId());

        assertTrue(retrievedParcel instanceof Parcel);
        assertThat(globalParcel).usingRecursiveComparison().isEqualTo(retrievedParcel);
    }
}
