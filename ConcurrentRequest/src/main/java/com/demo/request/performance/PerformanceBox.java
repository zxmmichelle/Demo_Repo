package com.demo.request.performance;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.demo.request.http.Http;
import com.demo.request.model.IModelGenerator;

public class PerformanceBox {
	private int parallelCount;
	private int requestTotalCount;
	private String requestUrl;
	private CyclicBarrier cyclicBarrier;
	private ExecutorService pool;
	private Vector<Long> cost;
	private int currentThreadCount;
	private IModelGenerator generator;

	public PerformanceBox(int parallelCount, int requestTotalCount, String url, IModelGenerator generator) {
		// TODO Auto-generated constructor stub
		this.parallelCount = parallelCount;
		this.requestTotalCount = requestTotalCount;
		this.requestUrl = url;
		this.currentThreadCount = parallelCount;
		this.cost = new Vector<>();
		this.pool = Executors.newFixedThreadPool(this.parallelCount);
		this.cyclicBarrier = new CyclicBarrier(this.parallelCount);
		this.generator = generator;
	}

	public void Execute() {
		for (int i = 0; i < this.parallelCount; i++) {
			this.pool.submit(new Task(cyclicBarrier, this.requestTotalCount / this.parallelCount));
		}
		do {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (currentThreadCount > 0);
		this.pool.shutdown();
	}

	class Task implements Runnable {
		private CyclicBarrier barrier;
		private int requestCount;

		public Task(CyclicBarrier barrier, int requestCount) {
			// TODO Auto-generated constructor stub
			this.barrier = barrier;
			this.requestCount = requestCount;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
//				barrier.await();
				send(requestCount);
			} finally {
				delThread();
			}
		}
	}

	private void delThread() {
		currentThreadCount--;
	}

	public void send(int requestCount) {
		for (int i = 0; i < requestCount; i++) {
			System.out.println("--requestCount:" + requestCount);
			Object data = generator.genData();
			Http.post(this.requestUrl, data);
		}
	}
}
