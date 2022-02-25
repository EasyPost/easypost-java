package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.CustomsItem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class CustomsItemTest {
    private static CustomsItem globalCustomsItem;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        globalCustomsItem = CustomsItem.create(Fixture.basicCustomsItem());
    }

    /**
     * Test creating a CustomsItem.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        CustomsItem customsItem = CustomsItem.create(Fixture.basicCustomsItem());

        assertTrue(customsItem instanceof CustomsItem);
        assertTrue(customsItem.getId().startsWith("cstitem_"));
        assertEquals(23.0, customsItem.getValue(), 0.01);
    }

    /**
     * Test retrieving a CustomsItem.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        CustomsItem retrieveCustomsItem = CustomsItem.retrieve(globalCustomsItem.getId());

        assertTrue(retrieveCustomsItem instanceof CustomsItem);
        assertThat(globalCustomsItem).usingRecursiveComparison().isEqualTo(retrieveCustomsItem);
    }
}
