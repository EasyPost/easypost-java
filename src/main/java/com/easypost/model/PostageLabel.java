package com.easypost.model;

import com.easypost.net.EasyPostResource;

public final class PostageLabel extends EasyPostResource {
    private String id;
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

    /**
     * Get the date advance of this PostageLabel.
     *
     * @return Date advance of this PostageLabel.
     */
    public int getDateAdvance() {
        return dateAdvance;
    }

    /**
     * Set the date advance of this PostageLabel.
     *
     * @param dateAdvance Date advance of this PostageLabel.
     */
    public void setDateAdvance(final int dateAdvance) {
        this.dateAdvance = dateAdvance;
    }

    /**
     * Get the EPL2 type of this PostageLabel.
     *
     * @return EPL2 type of this PostageLabel.
     */
    public String getEpl2LabelType() {
        return labelEpl2Type;
    }

    /**
     * Get the ID of this PostageLabel.
     *
     * @return ID of this PostageLabel.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of this PostageLabel.
     *
     * @param id ID of this PostageLabel.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the URL of this PostageLabel.
     *
     * @return URL of this PostageLabel.
     */
    public String getLabelUrl() {
        return labelUrl;
    }

    /**
     * Set the URL of this PostageLabel.
     *
     * @param labelUrl URL of this PostageLabel.
     */
    public void setLabelUrl(final String labelUrl) {
        this.labelUrl = labelUrl;
    }

    /**
     * Get the integrated form of this PostageLabel.
     *
     * @return Integrated form of this PostageLabel.
     */
    public String getIntegratedForm() {
        return integratedForm;
    }

    /**
     * Set the integrated form of this PostageLabel.
     *
     * @param integratedForm Integrated form of this PostageLabel.
     */
    public void setIntegratedForm(final String integratedForm) {
        this.integratedForm = integratedForm;
    }

    /**
     * Get the EPL2 file type of this PostageLabel.
     *
     * @return EPL2 file type of this PostageLabel.
     */
    public String getLabelEpl2FileType() {
        return labelEpl2FileType;
    }

    /**
     * Set the EPL2 file type of this PostageLabel.
     *
     * @param labelEpl2FileType EPL2 file type of this PostageLabel.
     */
    public void setLabelEpl2FileType(final String labelEpl2FileType) {
        this.labelEpl2FileType = labelEpl2FileType;
    }

    /**
     * Get the EPL2 size of this PostageLabel.
     *
     * @return EPL2 size of this PostageLabel.
     */
    public String getLabelEpl2Size() {
        return labelEpl2Size;
    }

    /**
     * Set the EPL2 size of this PostageLabel.
     *
     * @param labelEpl2Size EPL2 size of this PostageLabel.
     */
    public void setLabelEpl2Size(final String labelEpl2Size) {
        this.labelEpl2Size = labelEpl2Size;
    }

    /**
     * Get the EPL2 URL of this PostageLabel.
     *
     * @return EPL2 URL of this PostageLabel.
     */
    public String getLabelEpl2Url() {
        return labelEpl2Url;
    }

    /**
     * Set the EPL2 URL of this PostageLabel.
     *
     * @param labelEpl2Url EPL2 URL of this PostageLabel.
     */
    public void setLabelEpl2Url(final String labelEpl2Url) {
        this.labelEpl2Url = labelEpl2Url;
    }

    /**
     * Get the file of this PostageLabel.
     *
     * @return File of this PostageLabel.
     */
    public String getLabelFile() {
        return labelFile;
    }

    /**
     * Set the file of this PostageLabel.
     *
     * @param labelFile File of this PostageLabel.
     */
    public void setLabelFile(final String labelFile) {
        this.labelFile = labelFile;
    }

    /**
     * Get the file type of this PostageLabel.
     *
     * @return File type of this PostageLabel.
     */
    public String getLabelFileType() {
        return labelFileType;
    }

    /**
     * Set the file type of this PostageLabel.
     *
     * @param labelFileType File type of this PostageLabel.
     */
    public void setLabelFileType(final String labelFileType) {
        this.labelFileType = labelFileType;
    }

    /**
     * Get the PDF file type of this PostageLabel.
     *
     * @return PDF file type of this PostageLabel.
     */
    public String getLabelPdfFileType() {
        return labelPdfFileType;
    }

    /**
     * Set the PDF file type of this PostageLabel.
     *
     * @param labelPdfFileType PDF file type of this PostageLabel.
     */
    public void setLabelPdfFileType(final String labelPdfFileType) {
        this.labelPdfFileType = labelPdfFileType;
    }

    /**
     * Get the PDF size of this PostageLabel.
     *
     * @return PDF size of this PostageLabel.
     */
    public String getLabelPdfSize() {
        return labelPdfSize;
    }

    /**
     * Set the PDF size of this PostageLabel.
     *
     * @param labelPdfSize PDF size of this PostageLabel.
     */
    public void setLabelPdfSize(final String labelPdfSize) {
        this.labelPdfSize = labelPdfSize;
    }

    /**
     * Get the PDF URL of this PostageLabel.
     *
     * @return PDF URL of this PostageLabel.
     */
    public String getLabelPdfUrl() {
        return labelPdfUrl;
    }

    /**
     * Set the PDF URL of this PostageLabel.
     *
     * @param labelPdfUrl PDF URL of this PostageLabel.
     */
    public void setLabelPdfUrl(final String labelPdfUrl) {
        this.labelPdfUrl = labelPdfUrl;
    }

    /**
     * Get the resolution of this PostageLabel.
     *
     * @return Resolution of this PostageLabel.
     */
    public int getLabelResolution() {
        return labelResolution;
    }

    /**
     * Set the resolution of this PostageLabel.
     *
     * @param labelResolution Resolution of this PostageLabel.
     */
    public void setLabelResolution(final int labelResolution) {
        this.labelResolution = labelResolution;
    }

    /**
     * Get the size of this PostageLabel.
     *
     * @return Size of this PostageLabel.
     */
    public String getLabelSize() {
        return labelSize;
    }

    /**
     * Set the size of this PostageLabel.
     *
     * @param labelSize Size of this PostageLabel.
     */
    public void setLabelSize(final String labelSize) {
        this.labelSize = labelSize;
    }

    /**
     * Get the type of this PostageLabel.
     *
     * @return Type of this PostageLabel.
     */
    public String getLabelType() {
        return labelType;
    }

    /**
     * Set the type of this PostageLabel.
     *
     * @param labelType Type of this PostageLabel.
     */
    public void setLabelType(final String labelType) {
        this.labelType = labelType;
    }

    /**
     * Get the ZPL file type of this PostageLabel.
     *
     * @return ZPL file type of this PostageLabel.
     */
    public String getLabelZplFileType() {
        return labelZplFileType;
    }

    /**
     * Set the ZPL file type of this PostageLabel.
     *
     * @param labelZplFileType ZPL file type of this PostageLabel.
     */
    public void setLabelZplFileType(final String labelZplFileType) {
        this.labelZplFileType = labelZplFileType;
    }

    /**
     * Get the ZPL size of this PostageLabel.
     *
     * @return ZPL size of this PostageLabel.
     */
    public String getLabelZplSize() {
        return labelZplSize;
    }

    /**
     * Set the ZPL size of this PostageLabel.
     *
     * @param labelZplSize ZPL size of this PostageLabel.
     */
    public void setLabelZplSize(final String labelZplSize) {
        this.labelZplSize = labelZplSize;
    }

    /**
     * Get the ZPL URL of this PostageLabel.
     *
     * @return ZPL URL of this PostageLabel.
     */
    public String getLabelZplUrl() {
        return labelZplUrl;
    }

    /**
     * Set the ZPL URL of this PostageLabel.
     *
     * @param labelZplUrl ZPL URL of this PostageLabel.
     */
    public void setLabelZplUrl(final String labelZplUrl) {
        this.labelZplUrl = labelZplUrl;
    }

    /**
     * Get the PDF type of this PostageLabel.
     *
     * @return PDF type of this PostageLabel.
     */
    public String getPdfLabelType() {
        return labelPdfType;
    }

    /**
     * Get the ZPL type of this PostageLabel.
     *
     * @return ZPL type of this PostageLabel.
     */
    public String getZplLabelType() {
        return labelZplType;
    }

    /**
     * Set the PDF type of this PostageLabel.
     *
     * @param labelPdfType PDF type of this PostageLabel.
     */
    public void setLabelPdfType(final String labelPdfType) {
        this.labelPdfType = labelPdfType;
    }

    /**
     * Set the EPL2 type of this PostageLabel.
     *
     * @param labelEpl2Type EPL2 type of this PostageLabel.
     */
    public void setLabelEpl2Type(final String labelEpl2Type) {
        this.labelEpl2Type = labelEpl2Type;
    }

    /**
     * Set the ZPL type of this PostageLabel.
     *
     * @param labelZplType ZPL type of this PostageLabel.
     */
    public void setLabelZplType(final String labelZplType) {
        this.labelZplType = labelZplType;
    }
}
