package com.easypost.model;

import java.util.Date;

public class TrackingDetail {
	String status;
	String message;
	Date datetime;
	TrackingLocation trackingLocation;
	String statusDetail;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public TrackingLocation getTrackingLocation() { return trackingLocation; }
	public void setTrackingLocation(TrackingLocation location) { this.trackingLocation = location; }

	public String getStatusDetail() {
		return statusDetail;
	}
	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

}
