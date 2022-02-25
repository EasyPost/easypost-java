package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.CustomsInfo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class CustomsInfoTest {
    private static CustomsInfo globalCustomsInfo;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        globalCustomsInfo = CustomsInfo.create(Fixture.basicCustomsInfo());
    }

    /**
     * Test creating a CustomsInfo.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException{
        CustomsInfo customsInfo = CustomsInfo.create(Fixture.basicCustomsInfo());

        assertTrue(customsInfo instanceof  CustomsInfo);
        assertTrue(customsInfo.getId().startsWith("cstinfo_"));
        assertEquals("NOEEI 30.37(a)", customsInfo.getEelPfc());
    }

    /**
     * Test retrieving a CustomsInfo.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        CustomsInfo retrievedCustomsInfo = CustomsInfo.retrieve(globalCustomsInfo.getId());

        assertTrue(retrievedCustomsInfo instanceof CustomsInfo);
        assertThat(globalCustomsInfo).usingRecursiveComparison().isEqualTo(retrievedCustomsInfo);
    }
}
