package com.school.administration.app.ui.model.response;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorMessage {
	
	private String errorCode;
	private String status;
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
	private Date timestamp;

	public ErrorMessage(String errorCode, String status, String message, Date timestamp) {
		super();
		this.errorCode = errorCode;
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
