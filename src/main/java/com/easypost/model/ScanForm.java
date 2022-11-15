package com.easypost.model;

import java.util.List;

public class ScanForm extends EasyPostResource {
    private String status;
    private String message;
    private Address fromAddress;
    private List<String> trackingCodes;
    private String formUrl;
    private String formFileType;
    private String confirmation;
    private String batchId;

    /**
     * Get the batch ID of the ScanForm.
     *
     * @return the batch ID of the ScanForm.
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * Set the batch ID of the ScanForm.
     *
     * @param batchId the batch ID of the ScanForm.
     */
    public void setBatchId(final String batchId) {
        this.batchId = batchId;
    }

    /**
     * Get the confirmation of the ScanForm.
     *
     * @return the confirmation of the ScanForm.
     */
    public String getConfirmation() {
        return confirmation;
    }

    /**
     * Set the confirmation of the ScanForm.
     *
     * @param confirmation the confirmation of the ScanForm.
     */
    public void setConfirmation(final String confirmation) {
        this.confirmation = confirmation;
    }

    /**
     * Get the from address of the ScanForm.
     *
     * @return the from address of the ScanForm.
     */
    public Address getFromAddress() {
        return fromAddress;
    }

    /**
     * Set the from address of the ScanForm.
     *
     * @param fromAddress the from address of the ScanForm.
     */
    public void setFromAddress(final Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * Get the the label URL of the ScanForm.
     *
     * @return the label URL of the ScanForm.
     */
    public String getLabelUrl() {
        return this.getFormUrl();
    }

    /**
     * Get the status of the ScanForm.
     *
     * @return the status of the ScanForm.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status of the ScanForm.
     *
     * @param status the status of the ScanForm.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Get the URL for the ScanForm.
     *
     * @return the URL for the ScanForm.
     */
    public String getFormUrl() {
        return formUrl;
    }

    /**
     * Set the URL for the ScanForm.
     *
     * @param formUrl the URL for the ScanForm.
     */
    public void setFormUrl(final String formUrl) {
        this.formUrl = formUrl;
    }

    /**
     * Get the label file type of the ScanForm.
     *
     * @return the label file type of the ScanForm.
     */
    public String getLabelFileType() {
        return this.getFormFileType();
    }

    /**
     * Get the form file type of the ScanForm.
     *
     * @return the file type of the ScanForm.
     */
    public String getFormFileType() {
        return formFileType;
    }

    /**
     * Get the form file type of the ScanForm.
     *
     * @param formFileType the file type of the ScanForm.
     */
    public void setFormFileType(final String formFileType) {
        this.formFileType = formFileType;
    }

    /**
     * Get the message of the ScanForm.
     *
     * @return the message of the ScanForm.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message of the ScanForm.
     *
     * @param message the message of the ScanForm.
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Get the tracking codes of the ScanForm.
     *
     * @return the tracking codes of the ScanForm.
     */
    public List<String> getTrackingCodes() {
        return trackingCodes;
    }

    /**
     * Set the tracking codes of the ScanForm.
     *
     * @param trackingCodes the tracking codes of the ScanForm.
     */
    public void setTrackingCodes(final List<String> trackingCodes) {
        this.trackingCodes = trackingCodes;
    }
}
