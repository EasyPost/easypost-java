package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Event;
import com.easypost.model.Webhook;
import com.easypost.model.WebhookCollection;
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

@TestMethodOrder (MethodOrderer.OrderAnnotation.class)
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
                Webhook webhook = vcr.client.webhook.retrieve(testWebhookId);
                vcr.client.webhook.delete(webhook.getId());
                testWebhookId = null;
            } catch (Exception e) {
                // in case we try to delete something that's already been deleted
            }
        }
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
     * Create a webhook.
     *
     * @return Webhook object
     */
    private static Webhook createBasicWebhook() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("url", Fixtures.webhookUrl());

        Webhook webhook = vcr.client.webhook.create(params);
        testWebhookId = webhook.getId(); // trigger deletion after test
        return webhook;
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

        WebhookCollection webhooks = vcr.client.webhook.all();

        List<Webhook> webhooksList = webhooks.getWebhooks();

        assertTrue(webhooksList.stream().allMatch(webhook -> webhook != null));
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

        vcr.client.webhook.update(webhook.getId());

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
        String webhookSecret = "sécret";
        Map<String, Object> headers = ImmutableMap.of("X-Hmac-Signature",
                "hmac-sha256-hex=e93977c8ccb20363d51a62b3fe1fc402b7829be1152da9e88cf9e8d07115a46b");

        Event event = Utilities.validateWebhook(Fixtures.eventBytes(), headers, webhookSecret);

        assertEquals("batch.created", event.getDescription());
        assertEquals("batch_123...", event.getResult().get("id"));
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
