package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class ShipmentCollection extends EasyPostResource {
	List<Shipment> data;
	Integer count;
	
	public List<Shipment> getData() {
		return data;
	}
	public void setData(List<Shipment> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
