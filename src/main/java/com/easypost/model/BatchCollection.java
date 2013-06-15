package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class BatchCollection extends EasyPostResource {
	List<Batch> data;
	Integer count;
	
	public List<Batch> getData() {
		return data;
	}
	public void setData(List<Batch> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
