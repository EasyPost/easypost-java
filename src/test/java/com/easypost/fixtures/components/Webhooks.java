package com.easypost.fixtures.components;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class Webhooks {
    @SerializedName ("hmac_signature")
    public String hmacSignature;

    @SerializedName ("secret")
    public String secret;
    
    @SerializedName ("url")
    public String url;

    @SerializedName ("custom_headers")
    public List<Object> customHeaders;

}
