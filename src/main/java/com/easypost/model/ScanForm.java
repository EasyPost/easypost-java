/**
 * ScanForm.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.List;
import java.util.Map;

public class ScanForm extends EasyPostResource {
    private String id;
    private String status;
    private String message;
    private Address fromAddress;
    private List<String> trackingCodes;
    private String formUrl;
    private String formFileType;
    private String confirmation;
    private String batchId;

    /**
     * Get the ID of the ScanForm.
     *
     * @return the ID of the ScanForm.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the ScanForm.
     *
     * @param id the ID of the ScanForm.
     */
    public void setId(final String id) {
        this.id = id;
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
     * Get the the label URL of the ScanForm.
     *
     * @return the label URL of the ScanForm.
     */
    public String getLabelUrl() {
        return this.getFormUrl();
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
     * Get the label file type of the ScanForm.
     *
     * @return the label file type of the ScanForm.
     */
    public String getLabelFileType() {
        return this.getFormFileType();
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
     * Create a ScanForm from a map of parameters.
     *
     * @param params the map of parameters.
     * @return ScanForm object.
     * @throws EasyPostException when the request fails. when the request fails.
     */
    public static ScanForm create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a ScanForm from a map of parameters.
     *
     * @param params the map of parameters.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return ScanForm object.
     * @throws EasyPostException when the request fails. when the request fails.
     */
    public static ScanForm create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.POST, classURL(ScanForm.class), params, ScanForm.class, apiKey);
    }

    /**
     * Retrieve a ScanForm from the API.
     *
     * @param id the id of the ScanForm to retrieve.
     * @return ScanForm object.
     * @throws EasyPostException when the request fails. when the request fails.
     */
    public static ScanForm retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a ScanForm from the API.
     *
     * @param id     the id of the ScanForm to retrieve.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return ScanForm object.
     * @throws EasyPostException when the request fails. when the request fails.
     */
    public static ScanForm retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(ScanForm.class, id), null, ScanForm.class, apiKey);
    }

    /**
     * Get a list of ScanForms from the API.
     *
     * @param params the parameters to send to the API.
     * @return ScanFormCollection object.
     * @throws EasyPostException when the request fails. when the request fails.
     */
    public static ScanFormCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * Get a list of ScanForms from the API.
     *
     * @param params the parameters to send to the API.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return ScanFormCollection object.
     * @throws EasyPostException when the request fails. when the request fails.
     */
    public static ScanFormCollection all(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(ScanForm.class), params, ScanFormCollection.class, apiKey);
    }
}
