package com.demo.java.imodel;

public interface ILogHandler<T extends ILog> {
	void handle();
}
