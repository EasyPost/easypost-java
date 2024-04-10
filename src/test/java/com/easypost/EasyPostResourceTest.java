package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        List<String> substringsToContain = new ArrayList<String>(ImmutableList.of(
                "<",
                address.getClass().getName(),
                "@",
                "" + System.identityHashCode(address), // cast to string
                " id=",
                address.getId(),
                ">"
        ));

        // Regex matching doesn't work in run mode, only in debug mode (timing issue?)
        boolean matches = false;

        for (String substring : substringsToContain) {
            matches = stringRepresentation.contains(substring);
            if (!matches) {
                break;
            }
        }

        assertTrue(matches);
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

        List<String> substringsToContain = new ArrayList<String>(ImmutableList.of(
            "<",
            address.getClass().getName(),
            "@",
            "" + System.identityHashCode(address), // cast to string
            " id=",
            address.getId(),
            ">",
            " JSON: {",
            "}"
        ));

        // Regex matching doesn't work in run mode, only in debug mode (timing issue?)
        boolean matches = false;

        for (String substring : substringsToContain) {
            matches = prettyPrint.contains(substring);
            if (!matches) {
                break;
            }
        }

        assertTrue(matches);
    }
}
