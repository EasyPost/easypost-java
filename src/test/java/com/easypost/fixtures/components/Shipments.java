package com.easypost.fixtures.components;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public final class Shipments {
    @SerializedName ("basic_domestic")
    public HashMap<String, Object> basicDomestic;

    @SerializedName ("full")
    public HashMap<String, Object> full;

}
