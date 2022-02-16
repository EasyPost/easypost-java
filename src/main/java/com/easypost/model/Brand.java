package com.easypost.model;

import com.easypost.net.EasyPostResource;

public class Brand extends EasyPostResource {
    private String backgroundColor;
    private String color;
    private String logo;
    private String logoHref;
    private String ad;
    private String adHref;
    private String name;
    private String userID;
    private String theme;
    private String id;

    /**
     * Get the background of the Brand.
     *
     * @return the background of the Brand.
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Set the background of the Brand.
     *
     * @param backgroundColor the background of the Brand.
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Get the color of the Brand.
     *
     * @return the color of the Brand.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Set the color of the Brand.
     *
     * @param color the color of the Brand.
     */
    public void setColor(String color) {
        this.color = color;
    }

     /**
     * Get the logo of the Brand.
     *
     * @return the logo of the Brand.
     */
    public String getLogo() {
        return this.logo;
    }

    /**
     * Set the logo of the Brand.
     *
     * @param logo the logo of the Brand.
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * Get the logo href of the Brand.
     *
     * @return the logo href of the Brand.
     */
    public String getLogoHref() {
        return this.logoHref;
    }

    /**
     * Set the logo href of the Brand.
     *
     * @param logoHref the logo href of the Brand.
     */
    public void setLogoHref(String logoHref) {
        this.logoHref = logoHref;
    }

    /**
     * Get the ad of the Brand.
     *
     * @return the ad of the Brand.
     */
    public String getAd() {
        return this.ad;
    }

    /**
     * Set the ad of the Brand.
     *
     * @param ad the ad of the Brand.
     */
    public void setAd(String ad) {
        this.ad = ad;
    }

    /**
     * Get the ad href of the Brand.
     *
     * @return the ad href of the Brand.
     */
    public String getAdHref() {
        return this.adHref;
    }

    /**
     * Set the ad href of the Brand.
     *
     * @param adHref the ad href of the Brand.
     */
    public void setAdHref(String adHref) {
        this.adHref = adHref;
    }

    /**
     * Get the name of the User.
     *
     * @return the name of the User.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the User.
     *
     * @param name the name of the User.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the user ID of the Brand.
     *
     * @return the user ID of the Brand.
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Set the user ID of the Brand.
     *
     * @param userID the user ID of the Brand.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Get the theme of the Brand.
     *
     * @return the theme of the Brand.
     */
    public String getTheme() {
        return this.theme;
    }

    /**
     * Set the theme of the Brand.
     *
     * @param theme the theme of the Brand.
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * Get the ID of the Brand.
     *
     * @return the ID of the Brand.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the ID of the Brand.
     *
     * @param id the id of the Brand.
     */
    public void setId(String id) {
        this.id = id;
    }
}
