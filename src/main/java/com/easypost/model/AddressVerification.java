package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class AddressVerification {
    private Boolean success;
    private List<Error> errors;
    private AddressDetail details;

    /**
     * Set the success of this address verification object.
     *
     * @param success The success message.
     */
    void setSuccess(final boolean success) {
        this.success = success;
    }

    /**
     * Set the errors of this address verification object.
     *
     * @param errors The errors.
     */
    void setErrors(final List<Error> errors) {
        this.errors = errors;
    }

    /**
     * Set the details of this address verification object.
     *
     * @param details The details.
     */
    void setDetails(final AddressDetail details) {
        this.details = details;
    }
}
