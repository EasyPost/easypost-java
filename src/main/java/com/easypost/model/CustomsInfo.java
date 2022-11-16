package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class CustomsInfo extends EasyPostResource {
    private String contentsType;
    private String contentsExplanation;
    private boolean customsCertify;
    private String customsSigner;
    private String nonDeliveryOption;
    private String restrictionType;
    private String restrictionComments;
    private List<CustomsItem> customsItems;
    private String eelPfc;
    private String declaration;
}
