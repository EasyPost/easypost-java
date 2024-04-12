package com.easypost.model;

import lombok.Getter;

@Getter
public class Field extends EasyPostResource {
    private String visibility;
    private String label;
    private String value;
}
