package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Event;
import com.easypost.model.Webhook;
import com.easypost.utils.Utilities;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public final class WebhookTest {
    private static String testWebhookId = null;
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("webhook", TestUtils.ApiKey.TEST);
    }

    /**
     * Clean up test attributes after each unit test.
     */
    @AfterEach
    public void cleanup() {
        if (testWebhookId != null) {
            try {
                vcr.client.webhook.delete(testWebhookId);
                testWebhookId = null;
            } catch (Exception e) {
                // in case we try to delete something that's already been deleted
            }
        }
    }

    /**
     * Create a webhook.
     *
     * @return Webhook object
     */
    private static Webhook createBasicWebhook() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("url", Fixtures.webhookUrl());
        params.put("webhook_secret", Fixtures.webhookSecret());

        Webhook webhook = vcr.client.webhook.create(params);
        testWebhookId = webhook.getId(); // trigger deletion after test
        return webhook;
    }

    /**
     * Test creating a webhook.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        Webhook webhook = createBasicWebhook();

        assertInstanceOf(Webhook.class, webhook);
        assertTrue(webhook.getId().startsWith("hook_"));
        assertEquals(Fixtures.webhookUrl(), webhook.getUrl());
    }

    /**
     * Test retrieving a webhook.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        Webhook webhook = createBasicWebhook();

        Webhook retrievedWebhook = vcr.client.webhook.retrieve(webhook.getId());

        assertInstanceOf(Webhook.class, retrievedWebhook);
        assertTrue(webhook.equals(retrievedWebhook));
    }

    /**
     * Test retrieving all webhooks.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        createBasicWebhook();

        List<Webhook> webhooks = vcr.client.webhook.all();

        assertTrue(webhooks.size() > 0);
        assertTrue(webhooks.stream().allMatch(webhook -> webhook != null));
    }

    /**
     * Test updating a webhook.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdate() throws EasyPostException {
        vcr.setUpTest("update");

        Webhook webhook = createBasicWebhook();
        Map<String, Object> params = new HashMap<>();
        params.put("webhook_secret", Fixtures.webhookSecret());

        vcr.client.webhook.update(webhook.getId(), params);

        assertInstanceOf(Webhook.class, webhook);
    }

    /**
     * Test deleting a webhook.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testDelete() throws EasyPostException {
        vcr.setUpTest("delete");

        Webhook webhook = createBasicWebhook();
        Webhook retrievedWebhook = vcr.client.webhook.retrieve(webhook.getId());

        assertDoesNotThrow(() -> vcr.client.webhook.delete(retrievedWebhook.getId()));

        testWebhookId = null; // need to disable post-test deletion for test to work
    }

    /**
     * Test validating a webhook.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testValidateWebhook() throws EasyPostException {
        Map<String, Object> headers = ImmutableMap.of("X-Hmac-Signature",
                Fixtures.webhookHmacSignature());

        Event event = Utilities.validateWebhook(Fixtures.eventBytes(), headers, Fixtures.webhookSecret());

        assertEquals("tracker.updated", event.getDescription());
        assertEquals(614.4, event.getResult().get("weight")); // Ensure we convert floats properly
    }

    /**
     * Test validating a webhook.
     */
    @Test
    public void testValidateWebhookInvalidSecret() {
        String webhookSecret = "invalid_secret";
        Map<String, Object> headers = ImmutableMap.of("X-Hmac-Signature", "some-signature");

        assertThrows(EasyPostException.class, () -> {
            Utilities.validateWebhook(Fixtures.eventBytes(), headers, webhookSecret);
        });
    }

    /**
     * Test validating a webhook.
     */
    @Test
    public void testValidateWebhookMissingSecret() {
        String webhookSecret = "123";
        Map<String, Object> headers = ImmutableMap.of("some-header", "some-value");

        assertThrows(EasyPostException.class, () -> {
            Utilities.validateWebhook(Fixtures.eventBytes(), headers, webhookSecret);
        });
    }
}
