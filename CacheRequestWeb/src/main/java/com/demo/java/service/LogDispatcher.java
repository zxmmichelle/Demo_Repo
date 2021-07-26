package com.demo.java.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiConsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.demo.java.common.LogType;
import com.demo.java.imodel.ILog;
import com.demo.java.imodel.ILogHandler;

public class LogDispatcher {
	private final LinkedBlockingQueue<ILog> logQueue;
//	private final ExecutorService handlerPool;
	protected List<Class<? extends ILogHandler<ILog>>> handlerClassList;
//	private final List<String> handlerNames;
	protected static final Map<LogType, BiConsumer<ILog, Object>> logHandlersMap = new HashMap<>();
	private Thread runningThread;

	public LogDispatcher(LinkedBlockingQueue<ILog> logQueue) {
		this.logQueue = logQueue;
//		this.handlerPool = handlerPool;
//		this.handlerNames = names;
	}

	Runnable run() {
		return new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (!Thread.currentThread().isInterrupted()) {
					ILog log = null;
					try {
						log = logQueue.take();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					if (log != null) {
						dispatch(log);
					}
				}
			}
		};
	}
	

	protected void dispatch(ILog log) {
		LogType type = log.logType();
		try {
			BiConsumer<ILog, Object> consumer = logHandlersMap.get(type);
			if (consumer != null) {
				consumer.accept(log, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void regist(LogType type, BiConsumer<ILog, Object> consumer) {
		if (!logHandlersMap.containsKey(type)) {
			logHandlersMap.put(type, consumer);
		}
	}

	public void start() {
		runningThread = new Thread(run());
		runningThread.start();
		System.out.println("Dispatcher is running");
	}

	public void stop() {
		if (runningThread != null) {
			runningThread.interrupt();
		}
	}
}
