/**
 * AddressVerification.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import java.util.List;

public final class AddressVerification {
    private Boolean success;
    private List<Error> errors;
    private AddressDetail details;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(final Boolean success) {
        this.success = success;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(final List<Error> errors) {
        this.errors = errors;
    }

    public AddressDetail getAddressDetail() {
        return details;
    }

    public void setAddressDetail(final AddressDetail details) {
        this.details = details;
    }
}
