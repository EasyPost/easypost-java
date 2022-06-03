package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.CustomsItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomsItemTest {
    private static TestUtils.VCR _vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        _vcr = new TestUtils.VCR("customs_item", TestUtils.ApiKey.TEST);
    }

    /**
     * Create a customs item.
     */
    private static CustomsItem createBasicCustomsItem() throws EasyPostException {
        return CustomsItem.create(Fixture.basicCustomsItem());
    }

    /**
     * Test creating a CustomsItem.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        _vcr.setUpTest("create");

        CustomsItem customsItem = createBasicCustomsItem();

        assertInstanceOf(CustomsItem.class, customsItem);
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
        _vcr.setUpTest("retrieve");

        CustomsItem customsItem = createBasicCustomsItem();

        CustomsItem retrieveCustomsItem = CustomsItem.retrieve(customsItem.getId());

        assertInstanceOf(CustomsItem.class, customsItem);
        assertThat(customsItem).usingRecursiveComparison().isEqualTo(retrieveCustomsItem);
    }
}
