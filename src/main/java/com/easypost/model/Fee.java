package com.easypost.model;

public class Fee{
    String type;
    float amount;
    Boolean charged;
    Boolean refunded;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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

    public Boolean getRefunded() {
        return refunded;
    }
    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }
}
