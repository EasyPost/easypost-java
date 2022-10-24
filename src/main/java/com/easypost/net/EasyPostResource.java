/**
 * EasyPostResource.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.easypost.net;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.easypost.EasyPost;

public abstract class EasyPostResource {
    protected enum RequestMethod {
        GET,
        POST,
        DELETE,
        PUT
    }

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

    protected static String instanceURL(final Class<?> clazz, final String id) {
        return String.format("%s/%s", classURL(clazz), id);
    }

    protected static String classURL(final Class<?> clazz) {
        String singleURL = singleClassURL(clazz);
        if (singleURL.charAt(singleURL.length() - 1) == 's' || singleURL.charAt(singleURL.length() - 1) == 'h') {
            return String.format("%ses", singleClassURL(clazz));
        } else {
            return String.format("%ss", singleClassURL(clazz));
        }
    }

    protected static String singleClassURL(final Class<?> clazz) {
        return String.format("%s/%s", EasyPost.API_BASE, className(clazz));
    }

    private static String className(final Class<?> clazz) {
        return clazz.getSimpleName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase().replace("$", "");
    }

    /**
     * Get all static methods for a particular class.
     *
     * @param type Class type to get methods for.
     * @return List of class methods.
     */
    private static List<Method> getAllStaticMethods(Class<?> type) {
        List<Method> allMethods = getAllMethods(type);

        List<Method> staticMethods = new ArrayList<>();
        for (Method method : allMethods) {
            if (Modifier.isStatic(method.getModifiers())) {
                staticMethods.add(method);
            }
        }

        return staticMethods;
    }

    /**
     * Get all methods for a particular class.
     *
     * @param type Class type to get methods for.
     * @return List of class methods.
     */
    private static List<Method> getAllMethods(Class<?> type) {
        return Arrays.asList(type.getMethods());
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return (String) this.getIdString();
    }

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
     * Merge two EasyPostResource objects.
     *
     * @param obj    the base object
     * @param update the object to merge
     */
    public void merge(final EasyPostResource obj, final EasyPostResource update) {
        if (!obj.getClass().isAssignableFrom(update.getClass())) {
            return;
        }

        // get all methods from the obj class and its superclasses
        List<Method> methods = getAllNonStaticMethods(obj.getClass());

        for (Method fromMethod : methods) {
            if (fromMethod.getName().startsWith("get")
                    || Constant.GLOBAL_FIELD_ACCESSORS.contains(fromMethod.getName())) {

                if (fromMethod.isAnnotationPresent(Deprecated.class)) {
                    // skip deprecated methods
                    continue;
                }

                String fromName = fromMethod.getName();
                String toName = fromName.replace("get", "set");

                try {
                    Object value = fromMethod.invoke(update, (Object[]) null);
                    if (value != null) {
                        Method toMethod = obj.getClass().getMethod(toName, fromMethod.getReturnType());
                        toMethod.invoke(obj, value);
                    }
                } catch (Exception e) {
                    // TODO: Address situation below
                    /*
                     * The method getSmartrates() on the Shipment object is causing this exception.
                     * Since it found a method with "get" in the name, it expects there to be a
                     * "set" equivalent.
                     * There is not, causing this exception to be thrown, although nothing wrong has
                     * really happened.
                     * This code block was copy-pasted from StackOverflow:
                     * https://stackoverflow.com/a/7526414/13343799
                     * Per the comments, there are some built-in expectations for how this will
                     * work,
                     * and should eventually be re-written or removed entirely
                     * (explore returning a brand-new object rather than modifying the existing
                     * one).
                     * For now, the easiest fix would be to
                     * a) just ignore this exception, or
                     * b) rename getSmartrates() in the Shipment class to just smartrates()
                     * (similar to how the other methods are named).
                     */
                    // e.printStackTrace();
                }
            }
        }
    }

    /**
     * Get all non-static methods for a particular class.
     *
     * @param type Class type to get methods for.
     * @return List of class methods.
     */
    private static List<Method> getAllNonStaticMethods(Class<?> type) {
        List<Method> allMethods = getAllMethods(type);

        List<Method> nonStaticMethods = new ArrayList<>();
        for (Method method : allMethods) {
            if (!Modifier.isStatic(method.getModifiers())) {
                nonStaticMethods.add(method);
            }
        }

        return nonStaticMethods;
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
