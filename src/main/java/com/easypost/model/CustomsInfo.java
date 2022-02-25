package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CustomsInfo extends EasyPostResource {
    private String id;
    private String contentsType;
    private String contentsExplanation;
    private boolean customsCertify;
    private String customsSigner;
    private String nonDeliveryOption;
    private String restrictionType;
    private String restrictionComments;
    private List<CustomsItem> customsItems;
    private String eelPfc;

    /**
     * Create a CustomsInfo from a map of parameters.
     *
     * @param params the map of parameters.
     * @return CustomsInfo object.
     * @throws EasyPostException when the request fails.
     */
    public static CustomsInfo create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a CustomsInfo from a map of parameters.
     *
     * @param params the map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return CustomsInfo object.
     * @throws EasyPostException when the request fails.
     */
    public static CustomsInfo create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("customs_info", params);

        return request(RequestMethod.POST, classURL(CustomsInfo.class), wrappedParams, CustomsInfo.class, apiKey);
    }

    /**
     * Retrieve a CustomsInfo from the API.
     *
     * @param id the ID of the CustomsInfo to retrieve.
     * @return CustomsInfo object.
     * @throws EasyPostException when the request fails.
     */
    public static CustomsInfo retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a CustomsInfo from the API.
     *
     * @param id     the ID of the CustomsInfo to retrieve.
     * @param apiKey API key to use in request (overrides default API key).
     * @return CustomsInfo object.
     * @throws EasyPostException when the request fails.
     */
    public static CustomsInfo retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(CustomsInfo.class, id), null, CustomsInfo.class, apiKey);
    }

    /**
     * Get this CustomsInfo's ID.
     *
     * @return the ID of this CustomsInfo.
     */
    public String getId() {
        return id;
    }

    /**
     * Set this CustomsInfo's ID.
     *
     * @param id the ID of this CustomsInfo.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get this CustomsInfo's contents type.
     *
     * @return the contents type of this CustomsInfo.
     */
    public String getContentsType() {
        return contentsType;
    }

    /**
     * Set this CustomsInfo's contents type.
     *
     * @param contentsType the contents type of this CustomsInfo.
     */
    public void setContentsType(final String contentsType) {
        this.contentsType = contentsType;
    }

    /**
     * Get an explanation of this CustomsInfo's contents.
     *
     * @return the explanation of this CustomsInfo's contents.
     */
    public String getContentsExplanation() {
        return contentsExplanation;
    }

    /**
     * Set an explanation of this CustomsInfo's contents.
     *
     * @param contentsExplanation the explanation of this CustomsInfo's contents.
     */
    public void setContentsExplanation(final String contentsExplanation) {
        this.contentsExplanation = contentsExplanation;
    }

    /**
     * Get whether this CustomsInfo is certified by customs.
     *
     * @return true if this CustomsInfo is certified by customs.
     */
    public boolean getCustomsCertify() {
        return customsCertify;
    }

    /**
     * Set whether this CustomsInfo is certified by customs.
     *
     * @param customsCertify true if this CustomsInfo is certified by customs.
     */
    public void setCustomsCertify(final boolean customsCertify) {
        this.customsCertify = customsCertify;
    }

    /**
     * Get this CustomsInfo's signer.
     *
     * @return the signer of this CustomsInfo.
     */
    public String getCustomsSigner() {
        return customsSigner;
    }

    /**
     * Set this CustomsInfo's signer.
     *
     * @param customsSigner the signer of this CustomsInfo.
     */
    public void setCustomsSigner(final String customsSigner) {
        this.customsSigner = customsSigner;
    }

    /**
     * Get the non-delivery option of this CustomsInfo.
     *
     * @return the non-delivery option of this CustomsInfo.
     */
    public String getNonDeliveryOption() {
        return nonDeliveryOption;
    }

    /**
     * Set this CustomsInfo's non-delivery option.
     *
     * @param nonDeliveryOption the non-delivery option of this CustomsInfo.
     */
    public void setNonDeliveryOption(final String nonDeliveryOption) {
        this.nonDeliveryOption = nonDeliveryOption;
    }

    /**
     * Get this CustomsInfo's restriction type.
     *
     * @return the restriction type of this CustomsInfo.
     */
    public String getRestrictionType() {
        return restrictionType;
    }

    /**
     * Set this CustomsInfo's restriction type.
     *
     * @param restrictionType the restriction type of this CustomsInfo.
     */
    public void setRestrictionType(final String restrictionType) {
        this.restrictionType = restrictionType;
    }

    /**
     * Get this CustomsInfo's restriction comments.
     *
     * @return the restriction comments of this CustomsInfo.
     */
    public String getRestrictionComments() {
        return restrictionComments;
    }

    /**
     * Set this CustomsInfo's restriction comments.
     *
     * @param restrictionComments the restriction comments of this CustomsInfo.
     */
    public void setRestrictionComments(final String restrictionComments) {
        this.restrictionComments = restrictionComments;
    }

    /**
     * Get this CustomsInfo's customs items.
     *
     * @return the customs items of this CustomsInfo.
     */
    public List<CustomsItem> getCustomsItems() {
        return customsItems;
    }

    /**
     * Set this CustomsInfo's customs items.
     *
     * @param customsItems the customs items of this CustomsInfo.
     */
    public void setCustomsItems(final List<CustomsItem> customsItems) {
        this.customsItems = customsItems;
    }

    /**
     * Get this CustomsInfo's EEL or PFC.
     *
     * @return the EEL or PFC of this CustomsInfo.
     */
    public String getEelPfc() {
        return eelPfc;
    }

    /**
     * Set this CustomsInfo's EEL or PFC.
     *
     * @param eelPfc the EEL or PFC of this CustomsInfo.
     */
    public void setEelPfc(String eelPfc) {
        this.eelPfc = eelPfc;
    }
}
