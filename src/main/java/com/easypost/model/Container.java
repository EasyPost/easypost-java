package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Container extends EasyPostResource {
  public String id;
  String name;
  String type;
  String reference;
  Float length;
  Float width;
  Float height;
  Float maxWeight;


  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  public String getReference() {
    return reference;
  }
  public void setReference(String reference) {
    this.reference = reference;
  }

  public Float getMaxWeight() {
    return maxWeight;
  }
  public void setMaxWeight(Float maxWeight) {
    this.maxWeight = maxWeight;
  }

  public Float getLength() {
    return length;
  }
  public void setLength(Float length) {
    this.length = length;
  }

  public Float getWidth() {
    return width;
  }
  public void setWidth(Float width) {
    this.width = width;
  }

  public Float getHeight() {
    return height;
  }
  public void setHeight(Float height) {
    this.height = height;
  }


  // create
  public static Container create(Map<String, Object> params) throws EasyPostException {
    return create(params, null);
  }
  public static Container create(Map<String, Object> params, String apiKey) throws EasyPostException {
    Map<String, Object> wrappedParams = new HashMap<String, Object>();
    wrappedParams.put("container", params);

    return request(RequestMethod.POST, classURL(Container.class), wrappedParams, Container.class, apiKey);
  }

  // retrieve
  public static Container retrieve(String id) throws EasyPostException {
    return retrieve(id, null);
  }
  public static Container retrieve(String id, String apiKey) throws EasyPostException {
    return request(RequestMethod.GET, instanceURL(Container.class, id), null, Container.class, apiKey);
  }

  // all
  public static ContainerCollection all(Map<String, Object> params) throws EasyPostException {
    return all(params, null);
  }
  public static ContainerCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
    return request(RequestMethod.GET, classURL(Container.class), params, ContainerCollection.class, apiKey);
  }

}
