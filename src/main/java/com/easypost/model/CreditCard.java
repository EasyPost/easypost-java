package com.easypost.model;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;

import java.util.HashMap;
import java.util.Map;

public class CreditCard extends BaseCreditCard {
    /**
     * Fund your EasyPost wallet by charging your primary or secondary card on file.
     *
     * @param amount             amount to fund.
     * @param primaryOrSecondary primary or secondary payment method.
     * @return CreditCardFund object.
     * @throws EasyPostException when the request fails.
     */
    public static boolean fund(String amount, CreditCardPriority primaryOrSecondary) throws EasyPostException {
        return fund(amount, primaryOrSecondary,  null);
    }

    /**
     * Fund your EasyPost wallet by charging your primary or secondary card on file.
     *
     * @param amount             amount to fund.
     * @param primaryOrSecondary primary or secondary payment method.
     * @param apiKey             API key to use in request (overrides default API key).
     * @return CreditCardFund object.
     * @throws EasyPostException when the request fails.
     */
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
        
        if (cardID == null || cardID.isEmpty() || !cardID.startsWith("card_")) {
            throw new EasyPostException("The chosen payment method is not a credit card. Please try again.");
        }
    
        Map<String, Object> params = new HashMap<String, Object>();
            params.put("amount", amount);

        request(RequestMethod.POST, 
        String.format("%s/%s/%s/%s", EasyPost.API_BASE, "credit_cards", cardID, "charges"), params,
        CreditCard.class, apiKey);
            
        return true;  
    }

    /**
     * Delete a credit card by ID.
     *
     * @param creditCardId ID of credit card to delete.
     * @throws EasyPostException when the request fails.
     */
    public static void delete(String creditCardId) throws EasyPostException {
        delete(creditCardId, null);
    }

    /**
     * Delete a credit card by ID.
     *
     * @param creditCardId ID of credit card to delete.
     * @param apiKey       API key to use in request (overrides default API key).
     * @throws EasyPostException when the request fails.
     */
    public static void delete(String creditCardId, String apiKey) throws EasyPostException {
        request(RequestMethod.DELETE, String.format("%s/%s/%s", EasyPost.API_BASE, "credit_cards", creditCardId), null,
                CreditCard.class, apiKey);
    }
}
