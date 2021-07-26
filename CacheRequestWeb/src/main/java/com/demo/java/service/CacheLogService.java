package com.demo.java.service;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Service;

import com.demo.java.model.TestLog;

@Service
public class CacheLogService {
	private LinkedBlockingQueue<TestLog> cacheQueue;

	public CacheLogService() {
		cacheQueue = new LinkedBlockingQueue<TestLog>();
	}

	private void execute() throws InterruptedException {
		if (cacheQueue != null) {
			cacheQueue.take();
		}
	}

	public void put(TestLog log) throws InterruptedException {
		cacheQueue.put(log);
	}

}
