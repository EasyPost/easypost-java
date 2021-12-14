/**
 * EventData.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.Map;

public final class EventData extends EasyPostResource {
    private Map<String, Object> previousAttributes;
    private EasyPostResource object;

    /**
     * Get the previous attributes of this EventData.
     *
     * @return Map of previous attributes
     */
    public Map<String, Object> getPreviousAttributes() {
        return previousAttributes;
    }

    /**
     * Set the previous attributes of this EventData.
     *
     * @param previousAttributes Map of previous attributes
     */
    public void setPreviousAttributes(
            final Map<String, Object> previousAttributes) {
        this.previousAttributes = previousAttributes;
    }

    /**
     * Get the object of this EventData.
     *
     * @return EasyPostResource object
     */
    public EasyPostResource getObject() {
        return object;
    }

    /**
     * Set the object of this EventData.
     *
     * @param object EasyPostResource object
     */
    public void setObject(final EasyPostResource object) {
        this.object = object;
    }
}
