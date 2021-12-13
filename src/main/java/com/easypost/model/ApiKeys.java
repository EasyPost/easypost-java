/**
 * ApiKeys.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ApiKeys extends EasyPostResource {
    public String id;
    private List<ApiKey> keys;
    private List<ApiKeys> children;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<ApiKey> getKeys() {
        return keys;
    }

    public void setKeys(final List<ApiKey> keys) {
        this.keys = keys;
    }

    public List<ApiKeys> getChildren() {
        return children;
    }

    public void setChildren(final List<ApiKeys> children) {
        this.children = children;
    }

    // all
    public static ApiKeys all() throws EasyPostException {
        return all(null);
    }

    public static ApiKeys all(final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, classURL(ApiKey.class), null,
                ApiKeys.class, apiKey);
    }

}


