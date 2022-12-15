package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public class BetaPaymentRefund extends EasyPostResource{
    private String refundedAmount;
    private String paymentLogId;
    private List<String> refundedPaymentLogs;
    private List<Error> errors;
}
