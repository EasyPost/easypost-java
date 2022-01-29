package com.easypost.model;

public final class Fee {
    private String type;
    private float amount;
    private Boolean charged;
    private Boolean refunded;

    /**
     * Get Fee type.
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Set Fee type.
     *
     * @param type type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Get Fee amount.
     *
     * @return amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Set Fee amount.
     *
     * @param amount amount
     */
    public void setAmount(final float amount) {
        this.amount = amount;
    }

    /**
     * Get whether the fee is charged.
     *
     * @return true if the fee is charged
     */
    public Boolean getCharged() {
        return charged;
    }

    /**
     * Set whether the fee is charged.
     *
     * @param charged true if the fee is charged
     */
    public void setCharged(final Boolean charged) {
        this.charged = charged;
    }

    /**
     * Check if the fee is refunded.
     *
     * @return true if the fee is refunded
     */
    public Boolean getRefunded() {
        return refunded;
    }

    /**
     * Set whether the fee is refunded.
     *
     * @param refunded true if the fee is refunded
     */
    public void setRefunded(final Boolean refunded) {
        this.refunded = refunded;
    }
}
