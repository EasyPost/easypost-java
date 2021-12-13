/**
 * CustomsInfo.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CustomsInfo extends EasyPostResource {
    public String id;
    private String contentsType;
    private String contentsExplanation;
    private boolean customsCertify;
    private String customsSigner;
    private String nonDeliveryOption;
    private String restrictionType;
    private String restrictionComments;
    private List<CustomsItem> customsItems;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getContentsType() {
        return contentsType;
    }

    public void setContentsType(final String contentsType) {
        this.contentsType = contentsType;
    }

    public String getContentsExplanation() {
        return contentsExplanation;
    }

    public void setContentsExplanation(final String contentsExplanation) {
        this.contentsExplanation = contentsExplanation;
    }

    public boolean getCustomsCertify() {
        return customsCertify;
    }

    public void setCustomsCertify(final boolean customsCertify) {
        this.customsCertify = customsCertify;
    }

    public String getCustomsSigner() {
        return customsSigner;
    }

    public void setCustomsSigner(final String customsSigner) {
        this.customsSigner = customsSigner;
    }

    public String getNonDeliveryOption() {
        return nonDeliveryOption;
    }

    public void setNonDeliveryOption(final String nonDeliveryOption) {
        this.nonDeliveryOption = nonDeliveryOption;
    }

    public String getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(final String restrictionType) {
        this.restrictionType = restrictionType;
    }

    public String getRestrictionComments() {
        return restrictionComments;
    }

    public void setRestrictionComments(final String restrictionComments) {
        this.restrictionComments = restrictionComments;
    }

    public List<CustomsItem> getCustomsItems() {
        return customsItems;
    }

    public void setCustomsItems(final List<CustomsItem> customsItems) {
        this.customsItems = customsItems;
    }


    // create
    public static CustomsInfo create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static CustomsInfo create(final Map<String, Object> params,
                                     final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("customs_info", params);

        return request(RequestMethod.POST, classURL(CustomsInfo.class),
                wrappedParams, CustomsInfo.class, apiKey);
    }

    // retrieve
    public static CustomsInfo retrieve(final String id)
            throws EasyPostException {
        return retrieve(id, null);
    }

    public static CustomsInfo retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(CustomsInfo.class, id),
                null, CustomsInfo.class, apiKey);
    }

}
