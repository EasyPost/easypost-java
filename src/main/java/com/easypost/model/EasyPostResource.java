/**
 * EasyPostResource.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.easypost.model;

import java.util.Date;

import com.easypost.Constants;
import lombok.Getter;

@Getter
public abstract class EasyPostResource {
    private String id;
    private String mode;
    private String object;
    private Date createdAt;
    private Date updatedAt;

    /**
     * Returns a string representation of the object.
     * 
     * @return String of the object.
     */
    @Override
    public String toString() {
        if (this.id == null) {
            return String.format("<%s@%s>", this.getClass().getName(), System.identityHashCode(this));
        }
        return String.format("<%s@%s id=%s>", this.getClass().getName(), System.identityHashCode(this), this.id);
    }

    /**
     * Pretty print the JSON representation of the object.
     *
     * @return the JSON representation of the object.
     */
    public String prettyPrint() {
        String identifier = this.toString();
        String json = Constants.Http.PRETTY_PRINT_GSON.toJson(this);
        return String.format("%s JSON: %s", identifier, json);
    }

    /**
     * Override the hashCode method because it is needed when overriding equals().
     *
     * @return The hashcode of current object.
     */
    @Override
    public int hashCode() {
        return Constants.Http.GSON.toJson(this).hashCode();
    }

    /**
     * Override the Equals method, convert objects to Json strings for comparison.
     *
     * @param object Object of any class.
     * @return If two objects have the same properties.
     */
    @Override
    public boolean equals(Object object) {
        String currentObject = Constants.Http.GSON.toJson(this);
        String newObject = Constants.Http.GSON.toJson(object);

        return currentObject.equals(newObject);
    }
}
