package com.easypost.model;

import lombok.Getter;

@Getter
public final class PostageLabel extends EasyPostResource {
    private int dateAdvance;
    private String integratedForm;
    private int labelResolution;
    private String labelSize;
    private String labelType;
    private String labelUrl;
    private String labelFile;
    private String labelFileType;
    private String labelPdfSize;
    private String labelPdfType;
    private String labelPdfUrl;
    private String labelPdfFileType;
    private String labelEpl2Size;
    private String labelEpl2Type;
    private String labelEpl2Url;
    private String labelEpl2FileType;
    private String labelZplSize;
    private String labelZplType;
    private String labelZplUrl;
    private String labelZplFileType;
}
