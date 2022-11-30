package com.easypost.service;

import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.API.ExternalApiError;
import com.easypost.http.Constant;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.PaymentMethod;
import com.easypost.model.PaymentMethodObject;
import com.easypost.model.ReferralCustomer;
import com.easypost.model.ReferralCustomerCollection;
import com.easypost.utils.Utilities;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ReferralCustomerService {
    private final EasyPostClient client;

    /**
     * ReferralCustomerService constructor.
     * 
     * @param client The client object.
     */
    ReferralCustomerService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a Referral Customer object from parameter map. This function requires
     * the Partner User's API key.
     *
     * @param params Map of the referral user parameters.
     * @return Referral object.
     * @throws EasyPostException when the request fails.
     */
    public ReferralCustomer create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("user", params);

        String url = String.format("%s/%s/%s", client.getApiBase(), client.getApiVersion(), "referral_customers");

        return Requestor.request(RequestMethod.POST, url, wrappedParams, ReferralCustomer.class, client);
    }

    /**
     * Update a Referral object email. This function requires the Partner User's API
     * key.
     *
     * @param email  Email of the referral user to update.
     * @param userId ID of the referral user to update.
     * @throws EasyPostException when the request fails.
     */
    public void updateEmail(final String email, final String userId) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        wrappedParams.put("user", params);

        String url = String.format("%s/%s/%s/%s", client.getApiBase(), client.getApiVersion(), "referral_customers",
                userId);

        Requestor.request(RequestMethod.PUT, url, wrappedParams, ReferralCustomer.class, client);
    }

    /**
     * List all Referral objects. This function requires the Partner User's API key.
     *
     * @param params Map of parameters.
     * @return ReferralCustomerCollection object.
     * @throws EasyPostException when the request fails.
     */
    public ReferralCustomerCollection all(final Map<String, Object> params)
            throws EasyPostException {
        String url = String.format("%s/%s/%s", client.getApiBase(), client.getApiVersion(), "referral_customers");

        return Requestor.request(RequestMethod.GET, url, params, ReferralCustomerCollection.class, client);
    }

    /**
     * Add credit card to a referral user. This function requires the Referral
     * User's API key.
     *
     * @param referralApiKey  API key of the referral user.
     * @param number          Credit card number.
     * @param expirationMonth Expiration month of the credit card.
     * @param expirationYear  Expiration year of the credit card.
     * @param cvc             CVC of the credit card.
     * @return PaymentMethodObject object.
     * @throws Exception when the request fails.
     */
    public PaymentMethodObject addCreditCardToUser(final String referralApiKey, final String number,
            final int expirationMonth,
            final int expirationYear, final String cvc) throws Exception {
        return addCreditCardToUser(referralApiKey, number, expirationMonth, expirationYear, cvc,
                PaymentMethod.Priority.PRIMARY);
    }

    /**
     * Add credit card to a referral user. This function requires the Referral
     * User's API key.
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
    public PaymentMethodObject addCreditCardToUser(final String referralApiKey, final String number,
            final int expirationMonth,
            final int expirationYear, final String cvc,
            final PaymentMethod.Priority priority) throws Exception {
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
    private String retrieveEasypostStripeApiKey() throws EasyPostException {
        String url = String.format("%s/%s/%s", client.getApiBase(), client.getApiVersion(),
                "partners/stripe_public_key");
        @SuppressWarnings("unchecked")
        Map<String, String> response = Requestor.request(RequestMethod.GET, url, null, Map.class, client);

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
    private static String createStripeToken(final String number, final int expirationMonth, final int expirationYear,
            final String cvc,
            final String easypostStripeApiKey) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("number", number);
        params.put("exp_month", String.valueOf(expirationMonth));
        params.put("exp_year", String.valueOf(expirationYear));
        params.put("cvc", cvc);

        URL stripeUrl = new URL("https://api.stripe.com/v1/tokens");
        HttpURLConnection conn = (HttpURLConnection) stripeUrl.openConnection();
        String apiToken = String.format("%s %s", "Bearer", easypostStripeApiKey);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", apiToken);
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

        @SuppressWarnings("unchecked")
        Map<String, Object> responseMap = Constant.GSON.fromJson(responseBody, Map.class);

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
    private PaymentMethodObject createEasypostCreditCard(final String referralApiKey, final String stripeObjectId,
            final String priority) throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("stripe_object_id", stripeObjectId);
        params.put("priority", priority);

        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("credit_card", params);

        EasyPostClient referralClient = new EasyPostClient(referralApiKey);
        String url = String.format("%s/%s/%s", client.getApiBase(), client.getApiVersion(), "credit_cards");

        return Requestor.request(RequestMethod.POST, url, wrappedParams, PaymentMethodObject.class, referralClient);
    }
}
