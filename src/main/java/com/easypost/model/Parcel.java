package com.easypost.model;

import lombok.Getter;

@Getter
public final class Parcel extends EasyPostResource {
    private String predefinedPackage;
    private Float weight;
    private Float length;
    private Float width;
    private Float height;
}
