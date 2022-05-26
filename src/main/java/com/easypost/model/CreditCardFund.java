package com.easypost.model;

public class CreditCardFund {
    private String id;
    private String amount;

    /**
     * Get the ID of the CreditCardFund.
     *
     * @return the ID of the CreditCardFund.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the CreditCardFund.
     *
     * @param id the ID of the CreditCardFund.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the amount of the CreditCardFund.
     *
     * @return the amount of the CreditCardFund.
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Set the amount of the CreditCardFund.
     *
     * @param amount the amount of the CreditCardFund.
     */
    public void setAmount(final String amount) {
        this.amount = amount;
    }
}
