package com.school.administration.app.shared.dto;

import java.io.Serializable;

public class QrenNotifDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2938118245049559568L;
	private String status;
	private String message;
	private String timeStamp;
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
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}