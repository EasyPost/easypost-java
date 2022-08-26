package com.easypost.fixtures.sections;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public final class Addresses {
    @SerializedName("ca_address_1")
    public HashMap<String, Object> caAddress1;

    @SerializedName("ca_address_2")
    public HashMap<String, Object> caAddress2;

    @SerializedName("incorrect")
    public HashMap<String, Object> incorrectAddress;
}
