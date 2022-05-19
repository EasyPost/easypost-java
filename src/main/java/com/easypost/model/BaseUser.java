package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public class BaseUser extends EasyPostResource {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String balance;
    private String rechargeAmount;
    private String secondaryRechargeAmount;
    private String rechargeThreshold;
    private List<User> children;

    /**
     * Get the ID of the User.
     *
     * @return the ID of the User.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the User.
     *
     * @param id the ID of the User.
     */
    public void setId(final String id) {
        this.id = id;
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
}
