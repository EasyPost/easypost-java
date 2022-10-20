package com.easypost.model;

import com.easypost.EasyPost;
import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.ExternalApiError;
import com.easypost.net.Constant;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferralCustomer extends BaseUser {
    private List<ApiKey> apiKeys;

    /**
     * Get the api keys of the Referral user.
     *
     * @return the api keys of the Referral user.
     */
    public List<ApiKey> getApiKeys() {
        return apiKeys;
    }

    /**
     * Set the api keys of the Referral user.
     *
     * @param apiKeys the api keys of the Referral user.
     */
    public void setApiKeys(List<ApiKey> apiKeys) {
        this.apiKeys = apiKeys;
    }

    /**
     * Create a Referral object from parameter map. This function requires the Partner User's API key.
     *
     * @param params Map of the referral user parameters.
     * @return Referral object.
     * @throws EasyPostException when the request fails.
     */
    public static ReferralCustomer create(Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a Referral Customer object from parameter map. This function requires the Partner User's API key.
     *
     * @param params Map of the referral user parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Referral object.
     * @throws EasyPostException when the request fails.
     */
    public static ReferralCustomer create(Map<String, Object> params, String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("user", params);

        return request(RequestMethod.POST, String.format("%s/%s", EasyPost.API_BASE, "referral_customers"),
                wrappedParams, ReferralCustomer.class, apiKey);
    }

    /**
     * Update a Referral object email. This function requires the Partner User's API key.
     *
     * @param email  Email of the referral user to update.
     * @param userId ID of the referral user to update.
     * @throws EasyPostException when the request fails.
     */
    public static void updateEmail(String email, String userId) throws EasyPostException {
        updateEmail(email, userId, null);
    }

    /**
     * Update a Referral object email. This function requires the Partner User's API key.
     *
     * @param email  Email of the referral user to update.
     * @param userId ID of the referral user to update.
     * @param apiKey API key to use in request (overrides default API key).
     * @throws EasyPostException when the request fails.
     */
    public static void updateEmail(String email, String userId, String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        wrappedParams.put("user", params);

        request(RequestMethod.PUT, String.format("%s/%s/%s", EasyPost.API_BASE, "referral_customers", userId),
                wrappedParams, ReferralCustomer.class, apiKey);
    }

    /**
     * List all Referral objects. This function requires the Partner User's API key.
     *
     * @param params Map of parameters.
     * @return List<Referral> object.
     * @throws EasyPostException when the request fails.
     */
    public static ReferralCustomerCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * List all Referral objects. This function requires the Partner User's API key.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return ReferralCustomerCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static ReferralCustomerCollection all(final Map<String, Object> params, String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, String.format("%s/%s", EasyPost.API_BASE, "referral_customers"),
                params, ReferralCustomerCollection.class, apiKey);
    }

    /**
     * Add credit card to a referral user. This function requires the Referral User's API key.
     *
     * @param referralApiKey  API key of the referral user.
     * @param number          Credit card number.
     * @param expirationMonth Expiration month of the credit card.
     * @param expirationYear  Expiration year of the credit card.
     * @param cvc             CVC of the credit card.
     * @return PaymentMethodObject object.
     * @throws Exception when the request fails.
     */
    public static PaymentMethodObject addCreditCardToUser(String referralApiKey, String number, int expirationMonth,
                                                          int expirationYear, String cvc) throws Exception {
        return addCreditCardToUser(referralApiKey, number, expirationMonth, expirationYear, cvc,
                PaymentMethod.Priority.PRIMARY);
    }

    /**
     * Add credit card to a referral user. This function requires the Referral User's API key.
     *
     * @param referralApiKey  API key of the referral user.
     * @param number          Credit card number.
     * @param expirationMonth Expiration month of the credit card.
     * @param expirationYear  Expiration year of the credit card.
     * @param cvc             CVC of the credit card.
     * @param priority        Priority of this credit card.
     * @return PaymentMethodObject object.
     * @throws Exception when the request fails.
     */
    public static PaymentMethodObject addCreditCardToUser(String referralApiKey, String number, int expirationMonth,
                                                          int expirationYear, String cvc,
                                                          PaymentMethod.Priority priority) throws Exception {
        String easypostStripeApiKey = retrieveEasypostStripeApiKey();
        String stripeToken;

        try {
            stripeToken = createStripeToken(number, expirationMonth, expirationYear, cvc, easypostStripeApiKey);
        } catch (Exception e) {
            throw new ExternalApiError(String.format(Constants.EXTERNAL_API_CALL_FAILED, "Stripe"));
        }

        return createEasypostCreditCard(referralApiKey, stripeToken, priority.toString().toLowerCase());
    }

    /**
     * Retrieve EasyPost Stripe API key.
     *
     * @return EasyPost Stripe API key.
     * @throws EasyPostException when the request fails.
     */
    private static String retrieveEasypostStripeApiKey() throws EasyPostException {
        @SuppressWarnings ("unchecked") Map<String, String> response =
                request(RequestMethod.GET, String.format("%s/%s", EasyPost.API_BASE, "partners/stripe_public_key"),
                        null, Map.class, null);

        return response.getOrDefault("public_key", "");
    }

    /**
     * Get credit card token from Stripe.
     *
     * @param number               Credit card number.
     * @param expirationMonth      Expiration month of the credit card.
     * @param expirationYear       Expiration year of the credit card.
     * @param cvc                  CVC of the credit card.
     * @param easypostStripeApiKey EasyPost Stripe API key.
     * @return Stripe token.
     * @throws Exception when the request fails.
     */
    private static String createStripeToken(String number, int expirationMonth, int expirationYear, String cvc,
                                            String easypostStripeApiKey) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("number", number);
        params.put("exp_month", String.valueOf(expirationMonth));
        params.put("exp_year", String.valueOf(expirationYear));
        params.put("cvc", cvc);

        URL stripeUrl = new URL("https://api.stripe.com/v1/tokens");
        HttpURLConnection conn = (HttpURLConnection) stripeUrl.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", String.format("%s %s", "Bearer", easypostStripeApiKey));
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);

        String encodedURL = Utilities.getEncodedURL(params, "card");
        byte[] postData = encodedURL.getBytes(StandardCharsets.UTF_8);

        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }

        StringBuilder response;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

            String line;
            response = new StringBuilder();

            while ((line = br.readLine()) != null) {
                response.append(line);
                response.append(System.lineSeparator());
            }
            br.close();
        } finally {
            conn.disconnect();
        }

        String responseBody = response.toString();

        @SuppressWarnings ("unchecked") Map<String, Object> responseMap =
            Constant.GSON.fromJson(responseBody, Map.class);

        return responseMap.get("id").toString();
    }

    /**
     * Submit Stripe credit card token to EasyPost.
     *
     * @param referralApiKey API key of the referral user.
     * @param stripeObjectId Stripe token.
     * @param priority       Credit card priority.
     * @return CreditCard object.
     * @throws EasyPostException when the request fails.
     */
    private static PaymentMethodObject createEasypostCreditCard(String referralApiKey, String stripeObjectId,
                                                                String priority) throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("stripe_object_id", stripeObjectId);
        params.put("priority", priority);

        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("credit_card", params);

        return request(RequestMethod.POST, String.format("%s/%s", EasyPost.API_BASE, "credit_cards"),
                wrappedParams, PaymentMethodObject.class, referralApiKey);
    }
}
