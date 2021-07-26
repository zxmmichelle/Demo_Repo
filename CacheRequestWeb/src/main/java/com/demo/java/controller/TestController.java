package com.demo.java.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.java.imodel.ILog;
import com.demo.java.model.ALog;
import com.demo.java.model.BLog;
import com.demo.java.model.Response;
import com.demo.java.model.TestLog;
import com.demo.java.service.LogManager;

@RestController
@RequestMapping("/test")
public class TestController {

	protected final LogManager logManager;

	public TestController() {
		// TODO Auto-generated constructor stub
		logManager = new LogManager();
		logManager.init();
		logManager.start();
	}

	@RequestMapping(value = "/call", method = { RequestMethod.POST })
	public Response call(@RequestBody TestLog request) {
		Response response = new Response();
		ILog log = null;
		if (request.type.equals("1")) {
			log = new ALog(UUID.randomUUID().toString(), request.content);
		} else {
			log = new BLog(UUID.randomUUID().toString(), request.content);
		}
		logManager.notify(log);
		response.setRequestId(log.uniqueId());
		response.setSucc(true);
		return response;
	}

	@GetMapping(value = "/get")
	public String get() {
		return "yeah!";
	}
}
