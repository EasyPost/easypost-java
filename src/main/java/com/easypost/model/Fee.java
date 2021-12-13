/**
 * Fee.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

public final class Fee {
    private String type;
    private float amount;
    private Boolean charged;
    private Boolean refunded;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(final float amount) {
        this.amount = amount;
    }

    public Boolean getCharged() {
        return charged;
    }

    public void setCharged(final Boolean charged) {
        this.charged = charged;
    }

    public Boolean getRefunded() {
        return refunded;
    }

    public void setRefunded(final Boolean refunded) {
        this.refunded = refunded;
    }
}
