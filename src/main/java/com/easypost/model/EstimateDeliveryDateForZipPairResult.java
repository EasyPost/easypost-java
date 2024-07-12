package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public class EstimateDeliveryDateForZipPairResult {
    private Boolean saturdayDelivery;
    private List<DeliveryDateForZipPairEstimate> results;
    private List<String> carriersWithoutTintEstimates;
    private String desiredDeliveryDate;
    private String fromZip;
    private String toZip;
}
