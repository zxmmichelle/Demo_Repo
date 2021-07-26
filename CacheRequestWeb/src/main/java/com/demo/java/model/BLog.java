package com.demo.java.model;

import com.demo.java.common.LogType;
import com.demo.java.imodel.ILog;

public class BLog implements ILog {
	String uniqueId;
	String content;
	LogType logType;

	public BLog(String uniqueId, String content) {
		// TODO Auto-generated constructor stub
		this.uniqueId = uniqueId;
		this.content = content;
		this.logType = LogType.B;
	}

	@Override
	public String uniqueId() {
		// TODO Auto-generated method stub
		return this.uniqueId;
	}

	@Override
	public String content() {
		// TODO Auto-generated method stub
		return this.content;
	}

	@Override
	public LogType logType() {
		// TODO Auto-generated method stub
		return this.logType;
	}
}
