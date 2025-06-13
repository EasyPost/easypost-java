package com.easypost.model;

import lombok.Getter;

@Getter
public final class AiResults {
    private String carrier;
    private Boolean meetsRulesetRequirements;
    private String predictedDeliverByDate;
    private Integer predictedDeliverDays;
    private String rateId;
    private String rateUsd;
    private String service;
}
