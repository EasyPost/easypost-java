package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public class BaseUser extends EasyPostResource {
    private String name;
    private String email;
    private String phoneNumber;
    private String balance;
    private String rechargeAmount;
    private String secondaryRechargeAmount;
    private String rechargeThreshold;
    private String object;
    private String parentId;
    private boolean verified;
    private String pricePerShipment;
    private boolean hasBillingMethod;
    private String ccFeeRate;
    private String defaultInsuranceAmount;
    private String insuranceFeeRate;
    private String insuranceFeeMinimum;
    private List<User> children;

    /**
     * Get the balance of the User.
     *
     * @return the balance of the User.
     */
    public String getBalance() {
        return balance;
    }

    /**
     * Set the balance of the User.
     *
     * @param balance the balance of the User.
     */
    public void setBalance(final String balance) {
        this.balance = balance;
    }

    /**
     * Get the cc fee rate of the User.
     *
     * @return the cc fee rate of the User.
     */
    public String getCcFeeRate() {
        return ccFeeRate;
    }

    /**
     * Set the cc fee rate of the User.
     *
     * @param ccFeeRate the cc fee rate of the User.
     */
    public void setCcFeeRate(String ccFeeRate) {
        this.ccFeeRate = ccFeeRate;
    }

    /**
     * Get the children of the User.
     *
     * @return List of User objects.
     */
    public List<User> getChildren() {
        return children;
    }

    /**
     * Set the children of the User.
     *
     * @param children List of User objects.
     */
    public void setChildren(final List<User> children) {
        this.children = children;
    }

    /**
     * Get the default insurance amount of the User.
     *
     * @return the default insurance amount of the User.
     */
    public String getDefaultInsuranceAmount() {
        return defaultInsuranceAmount;
    }

    /**
     * Set the default insurance amount of the User.
     *
     * @param defaultInsuranceAmount the default insurance amount of the User.
     */
    public void setDefaultInsuranceAmount(String defaultInsuranceAmount) {
        this.defaultInsuranceAmount = defaultInsuranceAmount;
    }

    /**
     * Get the email of the User.
     *
     * @return the email of the User.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the User.
     *
     * @param email the email of the User.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Get the has billing method of the User.
     *
     * @return the has billing method of the User.
     */
    public boolean getHasBillingMethod() {
        return hasBillingMethod;
    }

    /**
     * Set the has billing method of the User.
     *
     * @param hasBillingMethod the has billing method of the User.
     */
    public void setHasBillingMethod(boolean hasBillingMethod) {
        this.hasBillingMethod = hasBillingMethod;
    }

    /**
     * Get the insurance fee minimum of the User.
     *
     * @return the insurance fee minimum of the User.
     */
    public String getInsuranceFeeMinimum() {
        return insuranceFeeMinimum;
    }

    /**
     * Set the insurance fee minimum of the User.
     *
     * @param insuranceFeeMinimum the insurance fee minimum of the User.
     */
    public void setInsuranceFeeMinimum(String insuranceFeeMinimum) {
        this.insuranceFeeMinimum = insuranceFeeMinimum;
    }

    /**
     * Get the insurance fee rate of the User.
     *
     * @return the insurance fee rate of the User.
     */
    public String getInsuranceFeeRate() {
        return insuranceFeeRate;
    }

    /**
     * Set the insurance fee rate of the User.
     *
     * @param insuranceFeeRate the insurance fee rate of the User.
     */
    public void setInsuranceFeeRate(String insuranceFeeRate) {
        this.insuranceFeeRate = insuranceFeeRate;
    }

    /**
     * Get the name of the User.
     *
     * @return the name of the User.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the User.
     *
     * @param name the name of the User.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the object of the User.
     *
     * @return the object of the User.
     */
    public String getObject() {
        return object;
    }

    /**
     * Set the object of the User.
     *
     * @param object the object of the User.
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Get the parent ID of the User.
     *
     * @return the parent ID of the User.
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Set the parent ID of the User.
     *
     * @param parentId the parent ID of the User.
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * Get the phone number of the User.
     *
     * @return the phone number of the User.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phone number of the User.
     *
     * @param phoneNumber the phone number of the User.
     */
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the price per shipment of the User.
     *
     * @return the price per shipment of the User.
     */
    public String getPricePerShipment() {
        return pricePerShipment;
    }

    /**
     * Set the price per shipment of the User.
     *
     * @param pricePerShipment the price per shipment of the User.
     */
    public void setPricePerShipment(String pricePerShipment) {
        this.pricePerShipment = pricePerShipment;
    }

    /**
     * Get the recharge amount of the User.
     *
     * @return the recharge amount of the User.
     */
    public String getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     * Set the recharge amount of the User.
     *
     * @param rechargeAmount the recharge amount of the User.
     */
    public void setRechargeAmount(final String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     * Get the recharge threshold of the User.
     *
     * @return the recharge threshold of the User.
     */
    public String getRechargeThreshold() {
        return rechargeThreshold;
    }

    /**
     * Set the recharge threshold of the User.
     *
     * @param rechargeThreshold the recharge threshold of the User.
     */
    public void setRechargeThreshold(final String rechargeThreshold) {
        this.rechargeThreshold = rechargeThreshold;
    }

    /**
     * Get the secondary recharge amount of the User.
     *
     * @return the secondary recharge amount of the User.
     */
    public String getSecondaryRechargeAmount() {
        return secondaryRechargeAmount;
    }

    /**
     * Set the secondary recharge amount of the User.
     *
     * @param secondaryRechargeAmount the secondary recharge amount of the User.
     */
    public void setSecondaryRechargeAmount(final String secondaryRechargeAmount) {
        this.secondaryRechargeAmount = secondaryRechargeAmount;
    }

    /**
     * Get the verified of the User.
     *
     * @return the verified of the User.
     */
    public boolean getVerified() {
        return verified;
    }

    /**
     * Set the verified of the User.
     *
     * @param verified the verified of the User.
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
