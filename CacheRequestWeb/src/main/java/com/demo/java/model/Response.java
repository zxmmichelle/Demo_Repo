package com.demo.java.model;

public class Response {
	public boolean succ;
	public String requestId;

	public Response() {
		// TODO Auto-generated constructor stub
	}
	
	public Response(boolean succ, String requestId) {
		// TODO Auto-generated constructor stub
		this.succ = succ;
		this.requestId = requestId;
	}

	public boolean isSucc() {
		return succ;
	}

	public void setSucc(boolean succ) {
		this.succ = succ;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
