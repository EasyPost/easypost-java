package com.easypost.model;

import lombok.Getter;

@Getter
public final class FedexRegistration extends EasyPostResource {
    private String status;
    private String message;
}
