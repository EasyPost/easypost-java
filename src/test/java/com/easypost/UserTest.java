package com.easypost;

import com.easypost.model.*;

import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private static User globalUser;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setUp() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_PROD_API_KEY");
        globalUser = User.retrieveMe();
    }

    /**
     * Test creating a child user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // This endpoint returns the child user keys in plain text, do not run this
              // test.
    public void testCreate() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("name", "Test User");

        User user = User.create(params);

        assertTrue(user instanceof User);
        assertTrue(user.getId().startsWith("user_"));
    }

    /**
     * Test retrieving a user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        User authenticatedUser = User.retrieveMe();

        User user = User.retrieve(authenticatedUser.getChildren().get(0).getId());

        assertTrue(user instanceof User);
        assertTrue(user.getId().startsWith("user_"));
    }

    /**
     * Test retrieving user itself.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveMe() throws EasyPostException {
        User user = User.retrieveMe();

        assertTrue(user instanceof User);
        assertTrue(user.getId().startsWith("user_"));
    }

    /**
     * Test updating a user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdate() throws EasyPostException {
        String testPhone = "5555555555";
        Map<String, Object> params = new HashMap<>();

        params.put("phone", testPhone);

        globalUser.update(params);

        assertTrue(globalUser instanceof User);
        assertTrue(globalUser.getId().startsWith("user_"));
        assertEquals(testPhone, globalUser.getPhoneNumber());
    }

    /**
     * Test deleting a user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // Due to our inability to create child users securely, we must also skip
              // deleting them as we cannot replace the deleted ones easily.
    public void testDelete() throws EasyPostException {
        globalUser.delete();
    }

    /**
     * Test retrieving all API keys.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // API keys are returned as plaintext, do not run this test.
    public void testAppApiKeys() throws EasyPostException {
        ApiKeys apikeys = ApiKeys.all();

        assertTrue(apikeys instanceof ApiKeys);
    }

    /**
     * Test retrieving all API keys for a user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // API keys are returned as plaintext, do not run this test.
    public void testApiKeys() throws EasyPostException {
        globalUser.apiKeys();
    }

    /**
     * Test updating a brand.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdateBrand() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        String color = "#123456";

        params.put("color", color);

        Brand brand = globalUser.updateBrand(params);

        assertTrue(brand instanceof Brand);
        assertTrue(brand.getId().startsWith("brd_"));
        assertEquals(color, brand.getColor());
    }
}
