package com.easypost.model;

public class Field extends EasyPostResource {
    private String key;
    private String visibility;
    private String label;
    private String value;

    /**
     * Get the key of this field.
     * @return the key of this field.
     */
    public String getKey() {
        return key;
    }

    /**
     * Set the key of this field.
     * @param key the key of this field.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Get the visibility of this field.
     * @return the visibility of this field.
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Set the visibility of this field.
     * @param visibility the visibility of this field.
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * Get the label of this field.
     * @return the label of this field.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label of this field.
     * @param label the label of this field.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Get the value of this field.
     * @return the value of this field.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value of this field.
     * @param value the value of this field.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
