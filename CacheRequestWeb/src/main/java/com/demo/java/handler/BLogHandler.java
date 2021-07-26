package com.demo.java.handler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiConsumer;

import com.demo.java.common.LogType;
import com.demo.java.imodel.ILog;
import com.demo.java.imodel.ILogHandler;
import com.demo.java.service.LogDispatcher;

public class BLogHandler implements ILogHandler, BiConsumer<ILog, Object>, Runnable {
	private final LinkedBlockingQueue<ILog> queue;
	private Thread excuteThread;

	public BLogHandler() {
		// TODO Auto-generated constructor stub
		queue = new LinkedBlockingQueue<ILog>();
		LogDispatcher.regist(LogType.B, this);
	}

	@Override
	public void accept(ILog t, Object u) {
		// TODO Auto-generated method stub
		if (t != null) {
			queue.add(t);
		}
	}


	@Override
	public void handle() {
		// TODO Auto-generated method stub
		ILog log = null;
		try {
			log = queue.take();
			System.out.println("BLog trying to handle it - " + log.uniqueId() + "!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.currentThread().isInterrupted()) {
			handle();
		}
	}

}
