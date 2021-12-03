package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.List;

public class ApiKeys extends EasyPostResource {
    public String id;
    List<ApiKey> keys;
    List<ApiKeys> children;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<ApiKey> getKeys() { return keys; }
    public void setKeys(List<ApiKey> keys) { this.keys = keys; }

    public List<ApiKeys> getChildren() { return children; }
    public void setChildren(List<ApiKeys> children) { this.children = children; }

    // all
    public static ApiKeys all() throws EasyPostException {
      return all(null);
    }
    public static ApiKeys all(String apiKey) throws EasyPostException {
      return request(RequestMethod.GET, classURL(ApiKey.class), null, ApiKeys.class, apiKey);
    }

}


