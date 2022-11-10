package com.easypost.model;

import lombok.Getter;

@Getter
public final class Form extends EasyPostResource {
    private String formType;
    private String formUrl;
    private Boolean submittedElectronically;
}
