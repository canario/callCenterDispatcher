package com.almundo.call;

import java.util.Random;

import com.almundo.model.Employee;

public class TakeCallImpl implements TakeCall {

	@Override
	public void answer(Employee employee) {
		Random r = new Random();
		int low = 5;
		int high = 11;
		int result = r.nextInt(high - low) + low;
		try {
			Thread.sleep(result * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
