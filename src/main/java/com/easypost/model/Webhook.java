package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;
import com.easypost.utils.Cryptography;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Webhook extends EasyPostResource {
    private String id;
    private String mode;
    private String url;
    private Date disabledAt;

    /**
     * Get the date and time when the webhook was disabled.
     *
     * @return the date and time when the webhook was disabled
     */
    public Date getDisabledAt() {
        return disabledAt;
    }

    /**
     * Set the date and time when the webhook was disabled.
     *
     * @param disabledAt the date and time when the webhook was disabled
     */
    public void setDisabledAt(final Date disabledAt) {
        this.disabledAt = disabledAt;
    }

    /**
     * Get the URL of the webhook.
     *
     * @return the URL of the webhook
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the URL of the webhook.
     *
     * @param url the URL of the webhook
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Create a Webhook object from a map of parameters.
     *
     * @param params the map of parameters
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public static Webhook create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a Webhook object from a map of parameters.
     *
     * @param params the map of parameters
     * @param apiKey API key to use in request (overrides default API key).
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public static Webhook create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("webhook", params);

        return request(RequestMethod.POST, classURL(Webhook.class), wrappedParams, Webhook.class, apiKey);
    }

    /**
     * Retrieve a Webhook object from the API.
     *
     * @param id the ID of the Webhook to retrieve
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public static Webhook retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Webhook object from the API.
     *
     * @param id     the ID of the Webhook to retrieve
     * @param apiKey API key to use in request (overrides default API key).
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public static Webhook retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Webhook.class, id), null, Webhook.class, apiKey);
    }

    /**
     * Get a list of all Webhook objects.
     *
     * @return WebhookCollection object
     * @throws EasyPostException when the request fails.
     */
    public static WebhookCollection all() throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        return all(params, null);
    }

    /**
     * Get a list of all Webhook objects.
     *
     * @param params params for request
     * @param apiKey API key to use in request (overrides default API key).
     * @return WebhookCollection object
     * @throws EasyPostException when the request fails.
     */
    public static WebhookCollection all(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Webhook.class), params, WebhookCollection.class, apiKey);
    }

    /**
     * Get a list of all Webhook objects.
     *
     * @param params params for request
     * @return WebhookCollection object
     * @throws EasyPostException when the request fails.
     */
    public static WebhookCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * Delete this Webhook.
     *
     * @throws EasyPostException when the request fails.
     */
    public void delete() throws EasyPostException {
        this.delete(null);
    }

    /**
     * Delete this Webhook.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @throws EasyPostException when the request fails.
     */
    public void delete(final String apiKey) throws EasyPostException {
        request(RequestMethod.DELETE, instanceURL(Webhook.class, this.getId()), null, Webhook.class, apiKey);
    }

    /**
     * Get the ID of the webhook.
     *
     * @return the ID of the webhook
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the webhook.
     *
     * @param id the ID of the webhook
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the mode of the webhook.
     *
     * @return the mode of the webhook
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the mode of the webhook.
     *
     * @param mode the mode of the webhook
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Update this webhook.
     *
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook update() throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        return this.update(params, null);
    }

    /**
     * Update this webhook.
     *
     * @param params the map of parameters
     * @param apiKey API key to use in request (overrides default API key).
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook update(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("webhook", params);

        Webhook response =
                request(RequestMethod.PUT, instanceURL(Webhook.class, this.getId()), wrappedParams, Webhook.class,
                        apiKey);

        this.merge(this, response);
        return this;
    }

    /**
     * Update this webhook.
     *
     * @param params the map of parameters
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook update(final Map<String, Object> params) throws EasyPostException {
        return this.update(params, null);
    }

    /**
     * Validate a webhook by comparing the HMAC signature header sent from EasyPost to your shared secret.
     * If the signatures do not match, an error will be raised signifying the webhook either did not originate
     * from EasyPost or the secrets do not match. If the signatures do match, the `event_body` will be returned as JSON.
     *
     * @param data    Data to validate
     * @param headers Headers received from the webhook
     * @param secret  Shared secret to use in validation
     * @return JSON string of the event body if the signatures match, otherwise an error will be raised.
     * @throws EasyPostException        when the request fails.
     * @throws NoSuchAlgorithmException when the HMAC algorithm is not available.
     * @throws InvalidKeyException      when the shared secret is invalid.
     */
    @SuppressWarnings ("rawtypes")
    public static Map validateWebhook(byte[] data, Map<String, Object> headers, String secret)
            throws EasyPostException, NoSuchAlgorithmException, InvalidKeyException {
        // Extract the signature from the headers
        String providedSignature = headers.get("X-Hmac-Signature").toString();
        if (providedSignature == null) {
            throw new EasyPostException("Webhook received does not contain an HMAC signature.");
        }

        // Compute the HMAC hex digest of the data (normalize secret with NFKD profile)
        String computedSignature = Cryptography.toHMACSHA256HexDigest(data, secret, Normalizer.Form.NFKD);

        // Prepend prefix to computed signature
        computedSignature = "hmac-sha256-hex=" + computedSignature;

        // Compare the computed signature to the signature in the headers
        boolean signatureValid = Cryptography.signaturesMatch(providedSignature, computedSignature);

        if (!signatureValid) {
            throw new EasyPostException(
                    "Webhook received did not originate from EasyPost or had a webhook secret mismatch.");
        }

        String dataString = new String(data);
        return EasyPostResource.GSON.fromJson(dataString, Map.class);
    }
}
