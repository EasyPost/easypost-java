package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class PostageLabelCollection extends EasyPostResource {
	List<PostageLabel> data;
	Integer count;
	
	public List<PostageLabel> getData() {
		return data;
	}
	public void setData(List<PostageLabel> data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
