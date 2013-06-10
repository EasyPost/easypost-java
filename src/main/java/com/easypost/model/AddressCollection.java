package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class AddressCollection extends EasyPostResource {
	List<Address> data;
	Integer count;
	
	public List<Address> getData() {
		return data;
	}
	public void setData(List<Address> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
