package com.easypost.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;
import com.easypost.model.ApiKeys;

public class User extends EasyPostResource {
    public String id;
    String name;
    String email;
    String phoneNumber;
    String balance;
    Number rechargeAmount;
    Number secondaryRechargeAmount;
    Number rechargeThreshold;
    List<User> children;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getBalance() { return balance; }
    public void setBalance(String balance) { this.balance = balance; }

    public Number getRechargeAmount() { return rechargeAmount; }
    public void setRechargeAmount(Number rechargeAmount) {
      this.rechargeAmount = rechargeAmount;
    }

    public Number getSecondaryRechargeAmount() { return secondaryRechargeAmount; }
    public void setSecondaryRechargeAmount(Number secondaryRechargeAmount) {
      this.secondaryRechargeAmount = secondaryRechargeAmount;
    }

    public Number getRechargeThreshold() { return rechargeThreshold; }
    public void setRechargeThreshold(Number rechargeThreshold) {
      this.rechargeThreshold = rechargeThreshold;
    }

    public List<User> getChildren() { return children; }
    public void setChildren(List<User> children) { this.children = children; }

    // retrieve
    public static User retrieve(String id) throws EasyPostException {
      return retrieve(id, null);
    }
    public static User retrieve(String id, String apiKey) throws EasyPostException {
      return request(RequestMethod.GET, instanceURL(User.class, id),
        null, User.class, apiKey);
    }

    // retrieve me
    public static User retrieveMe() throws EasyPostException {
      return retrieveMe(null);
    }
    public static User retrieveMe(String apiKey) throws EasyPostException {
      return request(RequestMethod.GET, classURL(User.class), null, User.class, apiKey);
    }

    // create
    public static User create() throws EasyPostException {
      return create(null, null);
    }
    public static User create(Map<String, Object> params) throws EasyPostException {
      return create(params, null);
    }
    public static User create(Map<String, Object> params, String apiKey)
        throws EasyPostException
    {
      Map<String, Object> wrappedParams = new HashMap<String, Object>();
      wrappedParams.put("user", params);

      return request(RequestMethod.POST, classURL(User.class), wrappedParams,
          User.class, apiKey);
    }

    // update
    public User update(Map<String, Object> params) throws EasyPostException {
      return update(params, null);
    }
    public User update(Map<String, Object> params, String apiKey) throws EasyPostException {
      User response = request(RequestMethod.PATCH,
        String.format("%s/update", instanceURL(User.class, this.getId())),
        params, User.class, apiKey);

      this.merge(this, response);
      return this;
    }

    // api keys
    public List<ApiKey> apiKeys() throws EasyPostException {
      ApiKeys parentKeys = ApiKeys.all();

      if (this.getId() == parentKeys.getId()) {
        return parentKeys.getKeys();
      }

      for(int i=0; i < parentKeys.children.size(); i++) {
        if(this.getId().equals(parentKeys.children.get(i).getId())) {
          return parentKeys.children.get(i).getKeys();
        }
      }

      throw new EasyPostException("Unable to find api key. Please contact support@easypost.com");
    }
}

