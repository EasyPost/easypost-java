package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class CustomsItemCollection extends EasyPostResource {
	List<CustomsItem> data;
	Integer count;
	
	public List<CustomsItem> getData() {
		return data;
	}
	public void setData(List<CustomsItem> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
