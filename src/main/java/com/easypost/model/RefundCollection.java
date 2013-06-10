package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class RefundCollection extends EasyPostResource {
	List<Refund> data;
	Integer count;
	
	public List<Refund> getData() {
		return data;
	}
	public void setData(List<Refund> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
