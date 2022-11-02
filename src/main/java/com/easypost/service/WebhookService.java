package com.easypost.service;

import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.SignatureVerificationError;
import com.easypost.http.Constant;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Event;
import com.easypost.model.Webhook;
import com.easypost.model.WebhookCollection;
import com.easypost.utils.Cryptography;
import com.easypost.utils.Utilities;

import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class WebhookService {
    private final EasyPostClient client;

    /**
     * WebhookService constructor.
     * 
     * @param client The client object.
     */
    WebhookService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a Webhook object from a map of parameters.
     *
     * @param params the map of parameters
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("webhook", params);

        return Requestor.request(RequestMethod.POST, Utilities.classURL(Webhook.class),
                wrappedParams, Webhook.class, client);
    }

    /**
     * Retrieve a Webhook object from the API.
     *
     * @param id the ID of the Webhook to retrieve
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.instanceURL(Webhook.class, id),
                null, Webhook.class, client);
    }

    /**
     * Get a list of all Webhook objects.
     *
     * @return WebhookCollection object
     * @throws EasyPostException when the request fails.
     */
    public WebhookCollection all() throws EasyPostException {
        return all(null);
    }

    /**
     * Get a list of all Webhook objects.
     *
     * @param params params for request
     * @return WebhookCollection object
     * @throws EasyPostException when the request fails.
     */
    public WebhookCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.classURL(Webhook.class),
                params, WebhookCollection.class, client);
    }

    /**
     * Delete this Webhook.
     *
     * @param id The ID of webhook.
     * @throws EasyPostException when the request fails.
     */
    public void delete(final String id) throws EasyPostException {
        Requestor.request(RequestMethod.DELETE, Utilities.instanceURL(Webhook.class,
                id), null, Webhook.class, client);
    }

    /**
     * Update this webhook.
     *
     * @param id The ID of webhook.
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook update(final String id) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        return this.update(params, id);
    }

    /**
     * Update this webhook.
     *
     * @param params  Map of parameters
     * @param id The ID of webhook.
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook update(final Map<String, Object> params, final String id) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("webhook", params);

        return Requestor.request(RequestMethod.PUT,
                Utilities.instanceURL(Webhook.class, id), wrappedParams, Webhook.class, client);
    }

    /**
     * Validate a webhook by comparing the HMAC signature header sent from EasyPost
     * to your shared secret.
     * If the signatures do not match, an error will be raised signifying
     * the webhook either did not originate from EasyPost or the secrets do not
     * match.
     * If the signatures do match, the `event_body` will be returned as JSON.
     *
     * @param eventBody     Data to validate
     * @param headers       Headers received from the webhook
     * @param webhookSecret Shared secret to use in validation
     * @return JSON string of the event body if the signatures match, otherwise an
     *         error will be raised.
     * @throws EasyPostException when the request fails.
     */
    public Event validateWebhook(byte[] eventBody, Map<String, Object> headers, String webhookSecret)
            throws EasyPostException {

        String providedSignature = null;
        try {
            providedSignature = headers.get("X-Hmac-Signature").toString();
        } catch (NullPointerException ignored) { // catch error raised if header key doesn't exist
        }

        if (providedSignature != null) {
            String calculatedDigest = Cryptography.toHMACSHA256HexDigest(eventBody, webhookSecret,
                    Normalizer.Form.NFKD);
            String calculatedSignature = "hmac-sha256-hex=" + calculatedDigest;

            if (Cryptography.signaturesMatch(providedSignature, calculatedSignature)) {
                // Serialize data into a JSON string, then into an Event object
                String json = new String(eventBody, StandardCharsets.UTF_8);
                return Constant.GSON.fromJson(json, Event.class);
            } else {
                throw new SignatureVerificationError(Constants.WEBHOOK_DOES_NOT_MATCH);
            }
        } else {
            throw new SignatureVerificationError(Constants.INVALID_WEBHOOK_SIGNATURE);
        }
    }
}
