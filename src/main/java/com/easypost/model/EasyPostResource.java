/**
 * EasyPostResource.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.easypost.model;

import java.lang.reflect.Field;

import java.util.Date;

import com.easypost.http.Constant;

public abstract class EasyPostResource {
    private String id;
    private String mode;
    private Date createdAt;
    private Date updatedAt;

    /**
     * @return the Date this object was created
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the Date this object was created.
     *
     * @param createdAt the Date this object was created
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the ID of this object
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of this object.
     *
     * @param id the ID of this object
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return the API mode used to create this object
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the API mode used to create this object.
     *
     * @param mode the Mode of this object
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * @return the Date this object was last updated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Set the Date this object was last updated.
     *
     * @param updatedAt the Date this object was last updated
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Returns a string representation of the object.
     * 
     * @return String of the object.
     */
    @Override
    public String toString() {
        return (String) this.getIdString();
    }

    /**
     * Get the ID of the object.
     *
     * @return ID of the object.
     */
    private Object getIdString() {
        try {
            Field idField = this.getClass().getDeclaredField("id");
            return idField.get(this);
        } catch (SecurityException e) {
            return "";
        } catch (NoSuchFieldException e) {
            return "";
        } catch (IllegalArgumentException e) {
            return "";
        } catch (IllegalAccessException e) {
            return "";
        }
    }

    /**
     * Pretty print the JSON representation of the object.
     *
     * @return the JSON representation of the object.
     */
    public String prettyPrint() {
        return String.format("<%s@%s id=%s> JSON: %s", this.getClass().getName(), System.identityHashCode(this),
                this.getIdString(), Constant.PRETTY_PRINT_GSON.toJson(this));
    }

    /**
     * Override the hashCode method because it is needed when overriding equals().
     *
     * @return The hashcode of current object.
     */
    @Override
    public int hashCode() {
        return Constant.GSON.toJson(this).hashCode();
    }

    /**
     * Override the equals method, convert objects to Json strings for comparsion.
     *
     * @param object Object of any class.
     * @return If two objects have the same properties.
     */
    @Override
    public boolean equals(Object object) {
        String currentObject = Constant.GSON.toJson(this);
        String newObject = Constant.GSON.toJson(object);

        return currentObject.equals(newObject);
    }
}
