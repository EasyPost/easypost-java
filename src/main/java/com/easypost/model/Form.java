/**
 * Form.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

public final class Form {
    public String id;
    private String mode;
    private String formType;
    private String formUrl;
    private Boolean submittedElectronically;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(final String mode) {
        this.mode = mode;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(final String formType) {
        this.formType = formType;
    }

    public String getFormUrl() {
        return formUrl;
    }

    public void setFormUrl(final String formUrl) {
        this.formUrl = formUrl;
    }

    public Boolean getSubmittedElectronically() {
        return submittedElectronically;
    }

    public void setSubmittedElectronically(
            final Boolean submittedElectronically) {
        this.submittedElectronically = submittedElectronically;
    }
}
