package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.CustomsItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CustomsItemTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("customs_item", TestUtils.ApiKey.TEST);
    }

    /**
     * Create a customs item.
     *
     * @return CustomsItem object.
     */
    private static CustomsItem createBasicCustomsItem() throws EasyPostException {
        return vcr.client.customsItem.create(Fixtures.basicCustomsItem());
    }

    /**
     * Test creating a CustomsItem.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        CustomsItem customsItem = createBasicCustomsItem();

        assertInstanceOf(CustomsItem.class, customsItem);
        assertTrue(customsItem.getId().startsWith("cstitem_"));
        assertEquals(23.25, customsItem.getValue(), 0.01);
    }

    /**
     * Test retrieving a CustomsItem.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        CustomsItem customsItem = createBasicCustomsItem();

        CustomsItem retrieveCustomsItem = vcr.client.customsItem.retrieve(customsItem.getId());

        assertInstanceOf(CustomsItem.class, customsItem);
        assertTrue(customsItem.equals(retrieveCustomsItem));
    }
}
