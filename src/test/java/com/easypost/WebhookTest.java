package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Webhook;
import com.easypost.model.WebhookCollection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
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
     * Create a webhook.
     *
     * @return Webhook object
     */
    private static Webhook createBasicWebhook() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("url", Fixture.webhookUrl());

        Webhook webhook = Webhook.create(params);
        testWebhookId = webhook.getId(); // trigger deletion after test
        return webhook;
    }

    /**
     * Clean up test attributes after each unit test.
     */
    @AfterEach
    public void cleanup() {
        if (testWebhookId != null) {
            try {
                Webhook webhook = Webhook.retrieve(testWebhookId);
                webhook.delete();
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
        assertEquals(Fixture.webhookUrl(), webhook.getUrl());
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

        Webhook retrievedWebhook = Webhook.retrieve(webhook.getId());

        assertInstanceOf(Webhook.class, retrievedWebhook);
        assertThat(webhook).usingRecursiveComparison().isEqualTo(retrievedWebhook);
    }

    /**
     * Test retrieving all webhooks.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        WebhookCollection webhooks = Webhook.all();

        List<Webhook> webhooksList = webhooks.getWebhooks();

        assertTrue(webhooksList.stream().allMatch(webhook -> webhook instanceof Webhook));
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

        webhook.update();

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
        Webhook retrievedWebhook = Webhook.retrieve(webhook.getId());

        retrievedWebhook.delete();

        testWebhookId = null; // need to disable post-test deletion for test to work
    }
}
