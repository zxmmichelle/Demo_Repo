package com.demo.request;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.request.http.Http;
import com.demo.request.model.ModelGenerator;
import com.demo.request.performance.PerformanceBox;

@SpringBootApplication
public class ConcurrentRequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrentRequestApplication.class, args);
		PerformanceBox box = new PerformanceBox(50, 400, "http://localhost:8080/test/call", new ModelGenerator());
		box.Execute();
	}

}
