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

    public Map<String, Object> getPreviousAttributes() {
        return previousAttributes;
    }

    public void setPreviousAttributes(
            final Map<String, Object> previousAttributes) {
        this.previousAttributes = previousAttributes;
    }

    public EasyPostResource getObject() {
        return object;
    }

    public void setObject(final EasyPostResource object) {
        this.object = object;
    }
}
