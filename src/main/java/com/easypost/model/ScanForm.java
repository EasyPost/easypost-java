package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public class ScanForm extends EasyPostResource {
    private String status;
    private String message;
    private Address fromAddress;
    private List<String> trackingCodes;
    private String formUrl;
    private String formFileType;
    private String confirmation;
    private String batchId;
}
