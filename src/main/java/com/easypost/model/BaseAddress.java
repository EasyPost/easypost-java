package com.easypost.model;

import lombok.Getter;

@Getter
public class BaseAddress extends EasyPostResource {
    private String name;
    private String company;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String email;
}
