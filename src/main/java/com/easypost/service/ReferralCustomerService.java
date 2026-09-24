package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.ReferralCustomerCollection;
import com.easypost.model.PaymentMethod;
import com.easypost.model.PaymentMethodObject;
import com.easypost.model.ReferralCustomer;
import lombok.SneakyThrows;

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
    public String retrieveEasypostStripeApiKey() throws EasyPostException {
        String endpoint = "partners/stripe_public_key";

        @SuppressWarnings ("unchecked") Map<String, String> response =
                Requestor.request(RequestMethod.GET, endpoint, null, Map.class, client);

        return response.getOrDefault("public_key", "");
    }
}
