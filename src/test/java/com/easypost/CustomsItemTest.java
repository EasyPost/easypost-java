package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.CustomsItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertEquals(23.0, customsItem.getValue(), 0.01);
    }

    /**
     * Create a customs item.
     *
     * @return CustomsItem object.
     */
    private static CustomsItem createBasicCustomsItem() throws EasyPostException {
        return CustomsItem.create(Fixture.basicCustomsItem());
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

        CustomsItem retrieveCustomsItem = CustomsItem.retrieve(customsItem.getId());

        assertInstanceOf(CustomsItem.class, customsItem);
        assertThat(customsItem).usingRecursiveComparison().isEqualTo(retrieveCustomsItem);
    }
}
