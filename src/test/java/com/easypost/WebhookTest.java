package com.easypost;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Webhook;
import com.easypost.model.WebhookCollection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WebhookTest {
    private static Map<String, Object> params = new HashMap<>();
    private static Webhook globalWebhook;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    // @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        params.put("url", "http://example.com");

        globalWebhook = Webhook.create(params);
    }

    /**
     * Test creating a webhook.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(1)
    @Disabled // This test is ignored because we can't run webhook test simultaneously in CI test.
    public void testCreate() throws EasyPostException {
        Map<String, Object> p = new HashMap<>();
        p.put("url", "http://examples.com");

        Webhook webhook = Webhook.create(p);

        assertTrue(webhook instanceof Webhook);
        assertTrue(webhook.getId().startsWith("hook_"));
        assertEquals("http://examples.com", webhook.getUrl());

        webhook.delete(); // we are deleting the webhook here so we don't keep sending events to a dead webhook.
    }

    /**
     * Test retrieving a webhook.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(2)
    @Disabled // This test is ignored because we can't run webhook test simultaneously in CI test.
    public void testRetrieve() throws EasyPostException {
        Webhook retrievedWebhook = Webhook.retrieve(globalWebhook.getId());

        assertTrue(retrievedWebhook instanceof Webhook);
        assertThat(globalWebhook).usingRecursiveComparison().isEqualTo(retrievedWebhook);
    }

    /**
     * Test retrieving all webhooks.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(3)
    @Disabled // This test is ignored because we can't run webhook test simultaneously in CI test.
    public void testAll() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("page_size", Fixture.pageSize());

        WebhookCollection webhooks = Webhook.all(params);

        List<Webhook> webhooksList = webhooks.getWebhooks();

        assertTrue(webhooksList.size() <= Fixture.pageSize());
        assertTrue(webhooksList.stream().allMatch(webhook -> webhook instanceof Webhook));
    }

    /**
     * Test updating a webhook.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled
    public void testUpdate() throws EasyPostException {
        // Cannot be easily tested - requires a disabled webhook.
    }

    /**
     * Test deleting a webhook.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(4)
    @Disabled // This test is ignored because we can't run webhook test simultaneously in CI test.
    public void testDelete() throws EasyPostException {
        // This endpoint/method does not return anything, just make sure the request doesn't fail.
        globalWebhook.delete();
    }
}
