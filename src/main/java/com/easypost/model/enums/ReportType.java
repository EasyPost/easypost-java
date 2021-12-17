package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum ReportType implements EasyPostEnum {
    @SerializedName ("CashFlowReport")
    CASH_FLOW("CashFlowReport"),
    @SerializedName("PaymentLogReport")
    PAYMENT_LOG("PaymentLogReport"),
    @SerializedName("RefundReport")
    REFUND("RefundReport"),
    @SerializedName("ShipmentReport")
    SHIPMENT("ShipmentReport"),
    @SerializedName("ShipmentInvoiceReport")
    SHIPMENT_INVOICE("ShipmentInvoiceReport"),
    @SerializedName("TrackerReport")
    TRACKER("TrackerReport");

    private String value;

    ReportType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ReportType getEnum(String value) throws EasyPostException {
        return (ReportType) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
