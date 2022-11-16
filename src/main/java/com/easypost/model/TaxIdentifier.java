package com.easypost.model;

import lombok.Getter;

@Getter
public final class TaxIdentifier extends EasyPostResource {
    private String entity;
    private String taxId;
    private String taxIdType;
    private String issuingCountry;
}
