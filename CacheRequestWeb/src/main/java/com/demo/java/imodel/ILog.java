package com.demo.java.imodel;

import com.demo.java.common.LogType;

public interface ILog {
	LogType logType();
	String uniqueId();

	String content();

}
