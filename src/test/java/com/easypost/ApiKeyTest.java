package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.FilteringError;
import com.easypost.model.ApiKey;
import com.easypost.model.ApiKeys;
import com.easypost.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ApiKeyTest {
    private static String testUserId = null;
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setUp() throws EasyPostException {
        vcr = new TestUtils.VCR("api_key", TestUtils.ApiKey.PRODUCTION);
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
     * Test retrieving all API keys.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAllApiKeys() throws EasyPostException {
        vcr.setUpTest("all_api_keys");

        ApiKeys apikeys = vcr.client.apiKey.all();

        assertInstanceOf(ApiKeys.class, apikeys);

        List<ApiKey> apiKeys = vcr.client.apiKey.retrieveApiKeysForUser(apikeys.getId());

        assertNotNull(apiKeys);
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

        List<ApiKey> apiKeys = vcr.client.apiKey.retrieveApiKeysForUser(user.getId());

        assertNotNull(apiKeys);

        assertThrows(FilteringError.class, () -> vcr.client.apiKey.retrieveApiKeysForUser("invalid_id"));
    }
}
