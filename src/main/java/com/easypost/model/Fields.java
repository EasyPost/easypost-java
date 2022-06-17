package com.easypost.model;

import com.easypost.net.EasyPostResource;

public class Fields extends EasyPostResource {
    private Field credentials;
    private Field testCredentials;
    private boolean autoLink;
    private boolean customWorkflow;

    /**
     * Get the credentials for this Fields object.
     * @return the credentials for this Fields object.
     */
    public Field getCredentials() {
        return credentials;
    }

    /**
     * Set the credentials for this Fields object.
     * @param credentials the credentials for this Fields object.
     */
    public void setCredentials(Field credentials) {
        this.credentials = credentials;
    }

    /**
     * Get the test credentials for this Fields object.
     * @return the test credentials for this Fields object.
     */
    public Field getTestCredentials() {
        return testCredentials;
    }

    /**
     * Set the test credentials for this Fields object.
     * @param testCredentials the test credentials for this Fields object.
     */
    public void setTestCredentials(Field testCredentials) {
        this.testCredentials = testCredentials;
    }

    /**
     * Get whether this Fields object is auto-linked.
     * @return true if this Fields object is auto-linked, false otherwise.
     */
    public boolean isAutoLink() {
        return autoLink;
    }

    /**
     * Set whether this Fields object is auto-linked.
     * @param autoLink true if this Fields object is auto-linked, false otherwise.
     */
    public void setAutoLink(boolean autoLink) {
        this.autoLink = autoLink;
    }

    /**
     * Get whether this Fields object is a custom workflow.
     * @return true if this Fields object is a custom workflow, false otherwise.
     */
    public boolean isCustomWorkflow() {
        return customWorkflow;
    }

    /**
     * Set whether this Fields object is a custom workflow
     * @param customWorkflow true if this Fields object is a custom workflow, false otherwise.
     */
    public void setCustomWorkflow(boolean customWorkflow) {
        this.customWorkflow = customWorkflow;
    }
}
