package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class ScanFormCollection extends EasyPostResource {
	List<ScanForm> data;
	Integer count;
	
	public List<ScanForm> getData() {
		return data;
	}
	public void setData(List<ScanForm> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
