package com.demo.java.service;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.demo.java.imodel.ILog;
import com.demo.java.imodel.ILogHandler;

public class LogManager {

	public LinkedBlockingQueue<ILog> logQueue;
	private ExecutorService executeService;
	private List<String> nameList;

	public LogManager() {
		logQueue = new LinkedBlockingQueue<>();
	}

	private void scanPackage() {
		nameList = new ArrayList<>();
		String basePackage = "com.demo.java.handler";
		ClassLoader classLoader = getClass().getClassLoader();
		String splashPath = basePackage.replaceAll("\\.", "/");
		URL url = classLoader.getResource(splashPath);
		int pos = url.getFile().indexOf('!');
		File file = new File(pos == -1 ? url.getFile() : url.getFile().substring(5, pos));
		String[] names = file.list();
		if (null == names) {
			return;
		}
		String tempName = "";
		for (String name : names) {
			if (name.endsWith(".class")) {
				tempName = toFullyName(name, basePackage);
				if (nameList.contains(tempName))
					continue;
				nameList.add(tempName);
			}
		}

		for (String name : nameList) {
			System.out.println(name);
		}
	}

	String toFullyName(String shortName, String basePackage) {
		StringBuilder sb = new StringBuilder(basePackage);
		sb.append('.');
		int pos = shortName.indexOf(".");
		shortName = pos == -1 ? shortName : shortName.substring(0, pos);
		pos = shortName.indexOf("$");
		shortName = pos == -1 ? shortName : shortName.substring(0, pos);
		sb.append(shortName);
		return sb.toString();
	}

	protected LogDispatcher createDispatcher() {
		LogDispatcher dispatcher = new LogDispatcher(logQueue);
		return dispatcher;
	}

	public void init() {
		executeService = Executors.newFixedThreadPool(3);
		scanPackage();
		ILogHandler<ILog> handler;
		for (String name : nameList) {
			try {
				handler = (ILogHandler<ILog>) Class.forName(name).getConstructor().newInstance();
				if (handler != null) {
					executeService.submit((Runnable) handler);
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void start() {
		createDispatcher().start();
	}

	public void notify(ILog log) {
		try {
			logQueue.put(log);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
