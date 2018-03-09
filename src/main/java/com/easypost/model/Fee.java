package com.easypost.model;

public class Fee{
    float amount;
    Boolean charged;
    Boolean refunded;

    public Boolean getRefunded() {
        return refunded;
    }
    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Boolean getCharged() {
        return charged;
    }
    public void setCharged(Boolean charged) {
        this.charged = charged;
    }
}