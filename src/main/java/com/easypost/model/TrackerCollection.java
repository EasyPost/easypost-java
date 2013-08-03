package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class TrackerCollection extends EasyPostResource {
	List<Tracker> data;
	Integer count;
	
	public List<Tracker> getData() {
		return data;
	}
	public void setData(List<Tracker> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
