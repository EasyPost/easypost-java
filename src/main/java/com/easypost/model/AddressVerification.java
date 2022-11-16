package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class AddressVerification {
    private Boolean success;
    private List<Error> errors;
    private AddressDetail details;
}
