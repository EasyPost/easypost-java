package com.easypost.model;

import com.easypost.model.enums.FeeType;

public class Fee{
    FeeType type;
    float amount;
    Boolean charged;
    Boolean refunded;

    public FeeType getType() {
        return type;
    }
    public void setType(FeeType type) {
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
