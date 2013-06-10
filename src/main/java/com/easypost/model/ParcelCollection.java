package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class ParcelCollection extends EasyPostResource {
	List<Parcel> data;
	Integer count;
	
	public List<Parcel> getData() {
		return data;
	}
	public void setData(List<Parcel> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
