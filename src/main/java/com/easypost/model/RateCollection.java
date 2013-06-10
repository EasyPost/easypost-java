package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class RateCollection extends EasyPostResource {
	List<Rate> data;
	Integer count;
	
	public List<Rate> getData() {
		return data;
	}
	public void setData(List<Rate> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
