package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.easypost.model.CustomsItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class EasyPostResourceTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("easypost_resource", TestUtils.ApiKey.TEST);
    }

    /**
     * Test string representation of an EasyPostResource.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testToString() throws EasyPostException {
        vcr.setUpTest("to_string");

        Address address = vcr.client.address.create(Fixtures.caAddress1());

        String stringRepresentation = address.toString();
        String stringRepresentationRegex = "^<com\\.easypost\\.model\\.Address@\\d{10} id=adr_[a-f0-9]{32}>$";

        boolean stringRepresentationMatches = stringRepresentation.matches(stringRepresentationRegex);

        assertTrue(stringRepresentationMatches);
    }

    /**
     * Test pretty print of an EasyPostResource.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testPrettyPrint() throws EasyPostException {
        vcr.setUpTest("pretty_print");

        Address address = vcr.client.address.create(Fixtures.caAddress1());

        String prettyPrint = address.prettyPrint();
        String prettyPrintRegex = "> JSON: {";

        // Partial regex matching is terrible in Java. Works in debug mode, not in run mode, possibly due to timing

        boolean prettyPrintMatches = prettyPrint.contains(prettyPrintRegex);

        assertTrue(prettyPrintMatches);
    }
}
