package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.ApiKey;
import com.easypost.model.ApiKeys;
import com.easypost.model.Brand;
import com.easypost.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class UserTest {
    private static String testUserId = null;
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setUp() throws EasyPostException {
        vcr = new TestUtils.VCR("user", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Clean up test attributes after each unit test.
     */
    @AfterEach
    public void cleanup() {
        if (testUserId != null) {
            try {
                User user = vcr.client.user.retrieve(testUserId);
                vcr.client.user.delete(user.getId());
                testUserId = null;
            } catch (Exception e) {
                // in case we try to delete something that's already been deleted
            }
        }
    }

    /**
     * Test creating a child user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        User user = createUser();

        assertInstanceOf(User.class, user);
        assertTrue(user.getId().startsWith("user_"));
        assertEquals("Test User", user.getName());
    }

    /**
     * Create a user.
     *
     * @return User object
     */
    private static User createUser() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Test User");
        User user = vcr.client.user.create(params);
        testUserId = user.getId(); // trigger deletion after test
        return user;
    }

    /**
     * Test retrieving a user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        User authenticatedUser = retrieveMe();

        String userId = authenticatedUser.getId();

        User user = vcr.client.user.retrieve(userId);

        assertInstanceOf(User.class, user);
        assertTrue(user.getId().startsWith("user_"));
        assertEquals(userId, user.getId());
    }

    /**
     * Retrieve current user.
     *
     * @return User object
     */
    private static User retrieveMe() throws EasyPostException {
        return vcr.client.user.retrieveMe();
    }

    /**
     * Test retrieving user itself.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveMe() throws EasyPostException {
        vcr.setUpTest("retrieve_me");

        User user = vcr.client.user.retrieveMe();

        assertInstanceOf(User.class, user);
        assertTrue(user.getId().startsWith("user_"));
    }

    /**
     * Test updating a user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdate() throws EasyPostException {
        vcr.setUpTest("update");

        User user = createUser();

        String testName = "New Name";
        Map<String, Object> params = new HashMap<>();

        params.put("name", testName);

        User updatedUser = vcr.client.user.update(user.getId(), params);

        assertInstanceOf(User.class, updatedUser);
        assertTrue(updatedUser.getId().startsWith("user_"));
        assertEquals(testName, updatedUser.getName());
    }

    /**
     * Test deleting a user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testDelete() throws EasyPostException {
        vcr.setUpTest("delete");

        User user = createUser();

        assertDoesNotThrow(() -> vcr.client.user.delete(user.getId()));
    }

    /**
     * Test retrieving all API keys.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAllApiKeys() throws EasyPostException {
        vcr.setUpTest("all_api_keys");

        ApiKeys apikeys = vcr.client.apikeys.all();

        assertInstanceOf(ApiKeys.class, apikeys);
    }

    /**
     * Test retrieving all API keys for a user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testApiKeys() throws EasyPostException {
        vcr.setUpTest("api_keys");

        User user = createUser();

        List<ApiKey> apiKeys = vcr.client.user.apiKeys(user.getId());
        assertNotNull(apiKeys);
    }

    /**
     * Test updating a brand.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdateBrand() throws EasyPostException {
        vcr.setUpTest("update_brand");

        User user = createUser();

        String color = "#123456";

        Map<String, Object> params = new HashMap<>();
        params.put("color", color);

        Brand brand = vcr.client.user.updateBrand(user.getId(), params);

        assertInstanceOf(Brand.class, brand);
        assertTrue(brand.getId().startsWith("brd_"));
        assertEquals(color, brand.getColor());
    }
}
