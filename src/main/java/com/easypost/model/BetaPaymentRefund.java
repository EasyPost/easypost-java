package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public class BetaPaymentRefund extends EasyPostResource{
    private int refundedAmount;
    private List<Error> errors;
    private List<String> refundedPaymentLogs;
    private String paymentLogId;
    private String refundedAmountCurrencys;
}
