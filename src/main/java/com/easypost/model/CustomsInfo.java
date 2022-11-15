package com.easypost.model;

import java.util.List;

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
     * Get this CustomsInfo's declaration.
     *
     * @return the declaration of this CustomsInfo.
     */
    public String getDeclaration() {
        return declaration;
    }

    /**
     * Set this CustomsInfo's declaration.
     *
     * @param declaration the declaration of this CustomsInfo.
     */
    public void setDeclaration(String declaration) {
        this.declaration = declaration;
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
}
