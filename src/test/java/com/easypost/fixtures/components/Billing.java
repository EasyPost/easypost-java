package com.easypost.fixtures.components;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public final class Billing {
    @SerializedName ("payment_method_id")
    public String paymentMethodId;

    @SerializedName ("financial_connections_id")
    public String financialConnectionsId;
    
    @SerializedName ("mandate_data")
    public HashMap<String, Object> mandateData;

    @SerializedName ("priority")
    public String priority;

}
