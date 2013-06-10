package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class CustomsInfoCollection extends EasyPostResource {
	List<CustomsInfo> data;
	Integer count;
	
	public List<CustomsInfo> getData() {
		return data;
	}
	public void setData(List<CustomsInfo> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
