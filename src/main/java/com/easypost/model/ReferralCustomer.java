package com.easypost.model;

import java.util.List;

public class ReferralCustomer extends BaseUser {
    private List<ApiKey> apiKeys;

    /**
     * Get the api keys of the Referral user.
     *
     * @return the api keys of the Referral user.
     */
    public List<ApiKey> getApiKeys() {
        return apiKeys;
    }

    /**
     * Set the api keys of the Referral user.
     *
     * @param apiKeys the api keys of the Referral user.
     */
    public void setApiKeys(List<ApiKey> apiKeys) {
        this.apiKeys = apiKeys;
    }
}
