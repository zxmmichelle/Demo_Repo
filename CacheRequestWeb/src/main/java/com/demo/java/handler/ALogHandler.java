package com.demo.java.handler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiConsumer;

import com.demo.java.common.LogType;
import com.demo.java.imodel.ILog;
import com.demo.java.imodel.ILogHandler;
import com.demo.java.model.ALog;
import com.demo.java.service.LogDispatcher;

public class ALogHandler implements ILogHandler<ALog>, BiConsumer<ILog, Object>, Runnable {
	private final LinkedBlockingQueue<ALog> queue;
	private Thread excuteThread;

	public ALogHandler() {
		// TODO Auto-generated constructor stub
		queue = new LinkedBlockingQueue<>();
		LogDispatcher.regist(LogType.A, this);
	}

	@Override
	public void accept(ILog t, Object u) {
		// TODO Auto-generated method stub
		if (t != null) {
			try {
				queue.put((ALog) t);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
		ILog log = null;
		try {
			log = queue.take();
			System.out.println("ALog trying to handle it - " + log.uniqueId() + "!");
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
