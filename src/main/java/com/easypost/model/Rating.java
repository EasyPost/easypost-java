package com.easypost.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Rating extends EasyPostResource {
	List<?> ratings;
	List<ShipmentMessage> messages;

	public List<?>  getRating() {return ratings;}
	public void setRatingList() {this.ratings = ratings;}

	public List<ShipmentMessage> getMessages() { return messages; }
    public void setMessages(List<ShipmentMessage> messages) { this.messages = messages; }

	// create
	public static Rating create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static Rating create(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.POST, "https://api.easypost.com/rating/v1/rates", params, Rating.class, apiKey);
	}

}
