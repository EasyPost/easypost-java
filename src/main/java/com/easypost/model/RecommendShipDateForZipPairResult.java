package com.easypost.model;

import java.util.List;

import lombok.Getter;

@Getter
public class RecommendShipDateForZipPairResult {
    private Boolean saturdayDelivery;
    private List<ShipDateForZipPairRecommendation> results;
    private List<String> carriersWithoutTintEstimates;
    private String desiredDeliveryDate;
    private String fromZip;
    private String toZip;
}
