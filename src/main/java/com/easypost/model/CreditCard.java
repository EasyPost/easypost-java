package com.easypost.model;

import com.easypost.EasyPost;
import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;

import java.util.HashMap;
import java.util.Map;

/**
 * CreditCard is a model class that represents a credit card.
 *
 * @deprecated Use {@link com.easypost.model.PaymentMethodObject} instead.
 * Deprecated: v5.5.0 - v7.0.0
 */
@Deprecated
public class CreditCard extends BaseCreditCard {
    /**
     * Fund your EasyPost wallet by charging your primary or secondary card on file.
     *
     * @param amount             amount to fund.
     * @param primaryOrSecondary primary or secondary payment method.
     * @return CreditCardFund object.
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link com.easypost.model.Billing#fundWallet(String, PaymentMethod.Priority)} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public static boolean fund(String amount, CreditCardPriority primaryOrSecondary) throws EasyPostException {
        return fund(amount, primaryOrSecondary, null);
    }

    /**
     * Fund your EasyPost wallet by charging your primary or secondary card on file.
     *
     * @param amount             amount to fund.
     * @param primaryOrSecondary primary or secondary payment method.
     * @param apiKey             API key to use in request (overrides default API key).
     * @return CreditCardFund object.
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link com.easypost.model.Billing#fundWallet(String, PaymentMethod.Priority, String)} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public static boolean fund(String amount, CreditCardPriority primaryOrSecondary, String apiKey)
            throws EasyPostException {
        PaymentMethod paymentMethods = PaymentMethod.all();
        String cardID = null;

        switch (primaryOrSecondary) {
            case PRIMARY:
                cardID = paymentMethods.getPrimaryPaymentMethod().getId();
                break;
            case SECONDARY:
                cardID = paymentMethods.getSecondaryPaymentMethod().getId();
                break;
            default:
                break;
        }

        if (cardID == null || !cardID.startsWith("card_")) {
            throw new EasyPostException(Constants.INVALID_PAYMENT);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);

        // will attempt to serialize the empty response to a CreditCard object (doesn't matter)
        request(RequestMethod.POST, String.format("%s/%s/%s/%s", EasyPost.API_BASE, "credit_cards", cardID, "charges"),
                params, CreditCard.class, apiKey);

        return true;
    }

    /**
     * Delete a credit card by ID.
     *
     * @param creditCardId ID of credit card to delete.
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link com.easypost.model.Billing#deletePaymentMethod(PaymentMethod.Priority)} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public static void delete(String creditCardId) throws EasyPostException {
        delete(creditCardId, null);
    }

    /**
     * Delete a credit card by ID.
     *
     * @param creditCardId ID of credit card to delete.
     * @param apiKey       API key to use in request (overrides default API key).
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link com.easypost.model.Billing#deletePaymentMethod(PaymentMethod.Priority, String)} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public static void delete(String creditCardId, String apiKey) throws EasyPostException {
        request(RequestMethod.DELETE, String.format("%s/%s/%s", EasyPost.API_BASE, "credit_cards", creditCardId), null,
                CreditCard.class, apiKey);
    }
}
