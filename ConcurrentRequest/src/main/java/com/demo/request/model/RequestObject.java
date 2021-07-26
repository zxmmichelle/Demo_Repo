package com.demo.request.model;

import java.io.Serializable;

public class RequestObject implements Serializable {
	public String type;
	public String content;

	public RequestObject(String type, String content) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
