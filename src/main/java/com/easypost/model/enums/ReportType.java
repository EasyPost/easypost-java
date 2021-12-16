package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum ReportType implements EasyPostEnum {
    CASH_FLOW("CashFlowReport"),
    PAYMENT_LOG("PaymentLogReport"),
    REFUND("RefundReport"),
    SHIPMENT("ShipmentReport"),
    SHIPMENT_INVOICE("ShipmentInvoiceReport"),
    TRACKER("TrackerReport");

    private String value;

    ReportType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ReportType getEnum(String value) throws EasyPostException {
        return (ReportType) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
