package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class EventCollection extends EasyPostResource {
	List<Event> data;
	Integer count;
	
	public List<Event> getData() {
		return data;
	}
	public void setData(List<Event> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	} 
}