package com.easypost.service;

import com.easypost.EasyPost;
import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.CreditCard;
import com.easypost.model.CreditCardPriority;
import com.easypost.model.PaymentMethod;

import java.util.HashMap;
import java.util.Map;

public class CreditCardService {
    private final EasyPostClient client;

    /**
     * CreditCardService constructor.
     * 
     * @param client The client object.
     */
    CreditCardService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Fund your EasyPost wallet by charging your primary or secondary card on file.
     *
     * @param amount             Amount to fund.
     * @param primaryOrSecondary Primary or secondary payment method.
     * @return CreditCardFund object.
     * @throws EasyPostException when the request fails.
     * @deprecated Use
     *             {@link com.easypost.service.BillingService#fundWallet(String, PaymentMethod.Priority, String)}
     *             instead.
     *             Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public boolean fund(String amount, CreditCardPriority primaryOrSecondary)
            throws EasyPostException {
        PaymentMethod paymentMethods = client.paymentMethod.all();
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

        // will attempt to serialize the empty response to a CreditCard object (doesn't
        // matter)
        Requestor.request(RequestMethod.POST, String.format("%s/%s/%s/%s",
                EasyPost.API_BASE, "credit_cards", cardID, "charges"), params, CreditCard.class, client);

        return true;
    }

    /**
     * Delete a credit card by ID.
     *
     * @param creditCardId ID of credit card to delete.
     * @throws EasyPostException when the request fails.
     * @deprecated Use{@link com.easypost.service.BillingService#deletePaymentMethod(PaymentMethod.Priority, String)}
     *             instead.
     *             Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public void delete(String creditCardId) throws EasyPostException {
        Requestor.request(RequestMethod.DELETE, String.format("%s/%s/%s",
                EasyPost.API_BASE, "credit_cards", creditCardId), null, CreditCard.class, client);
    }
}
