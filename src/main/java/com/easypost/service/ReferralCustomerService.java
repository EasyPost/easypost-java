package com.easypost.service;

import com.easypost.Constants;
import com.easypost.EasyPost;
import com.easypost.exception.API.EncodingError;
import com.easypost.exception.API.ExternalApiError;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.ReferralCustomerCollection;
import com.easypost.model.PaymentMethod;
import com.easypost.model.PaymentMethodObject;
import com.easypost.model.ReferralCustomer;
import com.easypost.utils.InternalUtilities;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

        String endpoint = "referral_customers";

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, ReferralCustomer.class,
                client);
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

        String endpoint = "referral_customers/" + userId;

        Requestor.request(RequestMethod.PUT, endpoint, wrappedParams, ReferralCustomer.class,
                client);
    }

    /**
     * List all Referral objects. This function requires the Partner User's API key.
     *
     * @param params Map of parameters.
     * @return ReferralCustomerCollection object.
     * @throws EasyPostException when the request fails.
     */
    public ReferralCustomerCollection all(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "referral_customers";

        return Requestor.request(RequestMethod.GET, endpoint, params, ReferralCustomerCollection.class,
                client);
    }

    /**
     * Get the next page of an ReferralCustomerCollection.
     *
     * @param collection ReferralCustomerCollection to get next page of.
     * @return ReferralCustomerCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public ReferralCustomerCollection getNextPage(ReferralCustomerCollection collection) throws EndOfPaginationError {
        return getNextPage(collection, null);
    }

    /**
     * Get the next page of an ReferralCustomerCollection.
     *
     * @param collection ReferralCustomerCollection to get next page of.
     * @param pageSize   The number of results to return on the next page.
     * @return ReferralCustomerCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public ReferralCustomerCollection getNextPage(
            ReferralCustomerCollection collection, Integer pageSize) throws EndOfPaginationError {
        return collection.getNextPage(new Function<Map<String, Object>, ReferralCustomerCollection>() {
            @Override @SneakyThrows
            public ReferralCustomerCollection apply(Map<String, Object> parameters) {
                return all(parameters);
            }
        }, collection.getReferralCustomers(), pageSize);
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
     * @throws EasyPostException when the request fails.
     */
    public PaymentMethodObject addCreditCardToUser(final String referralApiKey, final String number,
                                                   final int expirationMonth, final int expirationYear,
                                                   final String cvc) throws EasyPostException {
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
     * @throws EasyPostException when the request fails.
     */
    public PaymentMethodObject addCreditCardToUser(final String referralApiKey, final String number,
                                                   final int expirationMonth, final int expirationYear,
                                                   final String cvc, final PaymentMethod.Priority priority)
            throws EasyPostException {
        String easypostStripeApiKey = retrieveEasypostStripeApiKey();
        String stripeToken;

        try {
            stripeToken = createStripeToken(number, expirationMonth, expirationYear, cvc, easypostStripeApiKey);
        } catch (Exception e) {
            throw new ExternalApiError(String.format(Constants.ErrorMessages.EXTERNAL_API_CALL_FAILED, "Stripe"));
        }

        return createEasypostCreditCard(referralApiKey, stripeToken, priority.toString().toLowerCase());
    }

    /**
     * Add a credit card to EasyPost for a ReferralCustomer with a payment method ID from Stripe.
     * This function requires the ReferralCustomer User's API key.
     *
     * @param referralApiKey  API key of the referral user.
     * @param paymentMethodId Payment method ID from Stripe.
     * @param priority        Priority of the credit card (e.g., "primary" or "secondary").
     * @return PaymentMethodObject object.
     * @throws EasyPostException when the request fails.
     */
    public PaymentMethodObject addCreditCardFromStripe(final String referralApiKey, final String paymentMethodId,
                                                    final PaymentMethod.Priority priority) throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> creditCardParams = new HashMap<>();
        creditCardParams.put("payment_method_id", paymentMethodId);
        creditCardParams.put("priority", priority.toString().toLowerCase());
        params.put("credit_card", creditCardParams);

        EasyPostClient referralClient = new EasyPostClient(referralApiKey);

        String endpoint = "credit_cards";

        return Requestor.request(RequestMethod.POST, endpoint, params, PaymentMethodObject.class, referralClient);
    }

    /**
     * Add a bank account to EasyPost for a ReferralCustomer.
     * This function requires the ReferralCustomer User's API key.
     *
     * @param referralApiKey          API key of the referral user.
     * @param financialConnectionsId  Financial connections ID from Stripe.
     * @param mandateData             Mandate data for the bank account.
     * @param priority                Priority of the bank account (e.g., "primary" or "secondary").
     * @return PaymentMethodObject object.
     * @throws EasyPostException when the request fails.
     */
    public PaymentMethodObject addBankAccountFromStripe(final String referralApiKey, 
                                                        final String financialConnectionsId,
                                                        final Map<String, Object> mandateData,
                                                        final PaymentMethod.Priority priority)
            throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("financial_connections_id", financialConnectionsId);
        params.put("mandate_data", mandateData);
        params.put("priority", priority.toString().toLowerCase());

        EasyPostClient referralClient = new EasyPostClient(referralApiKey);

        String endpoint = "bank_accounts";

        return Requestor.request(RequestMethod.POST, endpoint, params, PaymentMethodObject.class, referralClient);
    }

    /**
     * Retrieve EasyPost Stripe API key.
     *
     * @return EasyPost Stripe API key.
     * @throws EasyPostException when the request fails.
     */
    private String retrieveEasypostStripeApiKey() throws EasyPostException {
        String endpoint = "partners/stripe_public_key";

        @SuppressWarnings ("unchecked") Map<String, String> response =
                Requestor.request(RequestMethod.GET, endpoint, null, Map.class, client);

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
     * @throws EncodingError when the request details could not be encoded.
     * @throws IOException   when the request fails.
     */
    private static String createStripeToken(final String number, final int expirationMonth, final int expirationYear,
                                            final String cvc, final String easypostStripeApiKey)
            throws EncodingError, IOException {
        String apiToken = String.format("%s %s", "Bearer", easypostStripeApiKey);

        Map<String, String> params = new HashMap<>();
        params.put("number", number);
        params.put("exp_month", String.valueOf(expirationMonth));
        params.put("exp_year", String.valueOf(expirationYear));
        params.put("cvc", cvc);

        String encodedURL = InternalUtilities.getEncodedURL(params, "card");
        URL stripeUrl = new URL("https://api.stripe.com/v1/tokens?" + encodedURL);

        HttpURLConnection conn;
        if (EasyPost._vcrUrlFunction != null) {
            conn = EasyPost._vcrUrlFunction.apply(stripeUrl.toString());
        } else {
            conn = (HttpURLConnection) stripeUrl.openConnection();
        }

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", apiToken);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);

        StringBuilder response;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
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
                Constants.Http.GSON.fromJson(responseBody, Map.class);

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

        String endpoint = "credit_cards";

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, PaymentMethodObject.class,
                referralClient);
    }
}
