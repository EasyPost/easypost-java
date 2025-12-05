package com.easypost.model;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Getter;

@Getter
public final class FedExAccountValidationResponse extends EasyPostResource {
    @SerializedName("email_address")
    private String emailAddress;
    private Map<String, String> options;
    @SerializedName("phone_number")
    private String phoneNumber;
}
