package com.project.utils;

public class Response {
	private String message;
	private Object obj;
	
	public Response() {
		
	}
	
	public Response(String message, Object obj) {
		super();
		this.message = message;
		this.obj = obj;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
