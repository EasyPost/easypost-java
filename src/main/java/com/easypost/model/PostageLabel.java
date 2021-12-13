/**
 * PostageLabel.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

public final class PostageLabel extends EasyPostResource {
    public String id;
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

    public String getId() {
        return id;
    }

    private void setId(final String id) {
        this.id = id;
    }

    public int getDateAdvance() {
        return dateAdvance;
    }

    private void setDateAdvance(final int dateAdvance) {
        this.dateAdvance = dateAdvance;
    }

    public String getIntegratedForm() {
        return integratedForm;
    }

    private void setIntegratedForm(final String integratedForm) {
        this.integratedForm = integratedForm;
    }

    public int getLabelResolution() {
        return labelResolution;
    }

    private void setLabelResolution(final int labelResolution) {
        this.labelResolution = labelResolution;
    }

    public String getLabelSize() {
        return labelSize;
    }

    private void setLabelSize(final String labelSize) {
        this.labelSize = labelSize;
    }

    public String getLabelType() {
        return labelType;
    }

    private void setLabelType(final String labelType) {
        this.labelType = labelType;
    }

    public String getLabelUrl() {
        return labelUrl;
    }

    private void setLabelUrl(final String labelUrl) {
        this.labelUrl = labelUrl;
    }

    public String getLabelFile() {
        return labelFile;
    }

    private void setLabelFile(final String labelFile) {
        this.labelFile = labelFile;
    }

    public String getLabelFileType() {
        return labelFileType;
    }

    private void setLabelFileType(final String labelFileType) {
        this.labelFileType = labelFileType;
    }

    public String getLabelPdfSize() {
        return labelPdfSize;
    }

    private void setLabelPdfSize(final String labelPdfSize) {
        this.labelPdfSize = labelPdfSize;
    }

    public String getPdfLabelType() {
        return labelPdfType;
    }

    private void setLabelPdfType(final String labelPdfType) {
        this.labelPdfType = labelPdfType;
    }

    public String getLabelPdfUrl() {
        return labelPdfUrl;
    }

    private void setLabelPdfUrl(final String labelPdfUrl) {
        this.labelPdfUrl = labelPdfUrl;
    }

    public String getLabelPdfFileType() {
        return labelPdfFileType;
    }

    private void setLabelPdfFileType(final String labelPdfFileType) {
        this.labelPdfFileType = labelPdfFileType;
    }

    public String getLabelEpl2Size() {
        return labelEpl2Size;
    }

    private void setLabelEpl2Size(final String labelEpl2Size) {
        this.labelEpl2Size = labelEpl2Size;
    }

    public String getEpl2LabelType() {
        return labelEpl2Type;
    }

    private void setLabelEpl2Type(final String labelEpl2Type) {
        this.labelEpl2Type = labelEpl2Type;
    }

    public String getLabelEpl2Url() {
        return labelEpl2Url;
    }

    private void setLabelEpl2Url(final String labelEpl2Url) {
        this.labelEpl2Url = labelEpl2Url;
    }

    public String getLabelEpl2FileType() {
        return labelEpl2FileType;
    }

    private void setLabelEpl2FileType(final String labelEpl2FileType) {
        this.labelEpl2FileType = labelEpl2FileType;
    }

    public String getLabelZplSize() {
        return labelZplSize;
    }

    private void setLabelZplSize(final String labelZplSize) {
        this.labelZplSize = labelZplSize;
    }

    public String getZplLabelType() {
        return labelZplType;
    }

    private void setLabelZplType(final String labelZplType) {
        this.labelZplType = labelZplType;
    }

    public String getLabelZplUrl() {
        return labelZplUrl;
    }

    private void setLabelZplUrl(final String labelZplUrl) {
        this.labelZplUrl = labelZplUrl;
    }

    public String getLabelZplFileType() {
        return labelZplFileType;
    }

    private void setLabelZplFileType(final String labelZplFileType) {
        this.labelZplFileType = labelZplFileType;
    }
}
