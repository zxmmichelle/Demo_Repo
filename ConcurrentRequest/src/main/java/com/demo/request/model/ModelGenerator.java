package com.demo.request.model;

import java.util.Random;

public class ModelGenerator implements IModelGenerator {
	Random random = new Random();

	@Override
	public Object genData() {
		// TODO Auto-generated method stub
		RequestObject obj = new RequestObject(random.nextDouble() > 0.5 ? "1" : "2", "abc");
		return obj;
	}

}
