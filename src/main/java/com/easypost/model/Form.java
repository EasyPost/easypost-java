package com.easypost.model;

public final class Form {
    private String id;
    private String mode;
    private String formType;
    private String formUrl;
    private Boolean submittedElectronically;

    /**
     * Get the ID of the Form.
     *
     * @return the ID of the Form.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the Form.
     *
     * @param id the ID of the Form.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the mode of the Form.
     *
     * @return the mode of the Form.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the mode of the Form.
     *
     * @param mode the mode of the Form.
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Get the form type of the Form.
     *
     * @return the form type of the Form.
     */
    public String getFormType() {
        return formType;
    }

    /**
     * Set the form type of the Form.
     *
     * @param formType the form type of the Form.
     */
    public void setFormType(final String formType) {
        this.formType = formType;
    }

    /**
     * Get the form url of the Form.
     *
     * @return the form url of the Form.
     */
    public String getFormUrl() {
        return formUrl;
    }

    /**
     * Set the form url of the Form.
     *
     * @param formUrl the form url of the Form.
     */
    public void setFormUrl(final String formUrl) {
        this.formUrl = formUrl;
    }

    /**
     * Get whether the Form is submitted electronically.
     *
     * @return whether the Form is submitted electronically.
     */
    public Boolean getSubmittedElectronically() {
        return submittedElectronically;
    }

    /**
     * Set whether the Form is submitted electronically.
     *
     * @param submittedElectronically if the Form is submitted electronically.
     */
    public void setSubmittedElectronically(final Boolean submittedElectronically) {
        this.submittedElectronically = submittedElectronically;
    }
}
