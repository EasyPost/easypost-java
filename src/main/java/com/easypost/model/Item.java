package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Item extends EasyPostResource {
  public String id;
  String mode;
  String reference;
  String name;
  String description;
  String harmonizedCode;
  String countryOfOrigin;
  String warehouseLocation;
  Float length;
  Float width;
  Float height;
  Float weight;
  Float value;
  // ADD YOUR OWN CUSTOM REFERENCES (e.g. sku, upc)


  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getMode() {
    return mode;
  }
  public void setMode(String mode) {
    this.mode = mode;
  }

  public String getReference() {
    return reference;
  }
  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  public String getHarmonizedCode() {
    return harmonizedCode;
  }
  public void setHarmonizedCode(String harmonizedCode) {
    this.harmonizedCode = harmonizedCode;
  }

  public String getCountryOfOrigin() {
    return countryOfOrigin;
  }
  public void setCountryOfOrigin(String countryOfOrigin) {
    this.countryOfOrigin = countryOfOrigin;
  }

  public String getWarehouseLocation() {
    return warehouseLocation;
  }
  public void setWarehouseLocation(String warehouseLocation) {
    this.warehouseLocation = warehouseLocation;
  }

  public Float getWeight() {
    return weight;
  }
  public void setWeight(Float weight) {
    this.weight = weight;
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

  public Float getValue() {
    return value;
  }
  public void setValue(Float value) {
    this.value = value;
  }


  // create
  public static Item create(Map<String, Object> params) throws EasyPostException {
    return create(params, null);
  }
  public static Item create(Map<String, Object> params, String apiKey) throws EasyPostException {
    Map<String, Object> wrappedParams = new HashMap<String, Object>();
    wrappedParams.put("item", params);

    return request(RequestMethod.POST, classURL(Item.class), wrappedParams, Item.class, apiKey);
  }

  // retrieve
  public static Item retrieve(String id) throws EasyPostException {
    return retrieve(id, null);
  }
  public static Item retrieve(String id, String apiKey) throws EasyPostException {
    return request(RequestMethod.GET, instanceURL(Item.class, id), null, Item.class, apiKey);
  }

  // all
  public static ItemCollection all(Map<String, Object> params) throws EasyPostException {
    return all(params, null);
  }
  public static ItemCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
    return request(RequestMethod.GET, classURL(Item.class), params, ItemCollection.class, apiKey);
  }

  // retrieve by custom reference
  public Item retrieveReference(String name, String value) throws EasyPostException {
    return retrieveReference(name, value, null);
  }
  public Item retrieveReference(String name, String value, String apiKey) throws EasyPostException {
    return request(
      RequestMethod.GET,
      String.format("%s/retrieve_reference?%s=%s", instanceURL(Item.class, this.getId()), name, value), null, Item.class, apiKey);
  }

}
