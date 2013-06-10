package com.easypost.exception;

public class EasyPostException extends Exception {

	private static final long serialVersionUID = 1L;
	private final String param;

	public EasyPostException(String message) {
		super(message, null);
		this.param = null;
	}

	public EasyPostException(String message, Throwable e) {
		super(message, e);
		this.param = null;
	}

	public EasyPostException(String message, String param, Throwable e) {
		super(message, e);
		this.param = param;
	}

	public String getParam() {
		return param;
	}
}
