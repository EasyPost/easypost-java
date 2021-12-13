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

public final class ScanForm extends EasyPostResource {
    public String id;
    private String status;
    private String message;
    private Address fromAddress;
    private List<String> trackingCodes;
    private String formUrl;
    private String formFileType;
    private String confirmation;
    private String batchId;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(final Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    public List<String> getTrackingCodes() {
        return trackingCodes;
    }

    public void setTrackingCodes(final List<String> trackingCodes) {
        this.trackingCodes = trackingCodes;
    }

    public String getFormUrl() {
        return formUrl;
    }

    public String getLabelUrl() {
        return this.getFormUrl();
    }

    public void setFormUrl(final String formUrl) {
        this.formUrl = formUrl;
    }

    public String getFormFileType() {
        return formFileType;
    }

    public String getLabelFileType() {
        return this.getFormFileType();
    }

    public void setFormFileType(final String formFileType) {
        this.formFileType = formFileType;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(final String confirmation) {
        this.confirmation = confirmation;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(final String batchId) {
        this.batchId = batchId;
    }

    // create
    public static ScanForm create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static ScanForm create(final Map<String, Object> params,
                                  final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.POST, classURL(ScanForm.class), params,
                ScanForm.class, apiKey);
    }

    // retrieve
    public static ScanForm retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static ScanForm retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(ScanForm.class, id), null,
                ScanForm.class, apiKey);
    }

    // all
    public static ScanFormCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return all(params, null);
    }

    public static ScanFormCollection all(final Map<String, Object> params,
                                         final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(ScanForm.class), params,
                ScanFormCollection.class, apiKey);
    }

}
