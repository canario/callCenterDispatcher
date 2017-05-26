package com.almundo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.almundo.call.TakeCall;
import com.almundo.call.TakeCallImpl;

public class Dispatcher {

	private AtomicInteger operators = new AtomicInteger(8);

	private AtomicInteger supervisors = new AtomicInteger(1);

	private AtomicInteger directors = new AtomicInteger(1);
	
	private static Dispatcher instance;
	
	private ExecutorService executor;
	
	private Dispatcher(){
		executor =  Executors.newFixedThreadPool(10);
	}
	
	public static Dispatcher getInstance(){
		if(instance == null){
			instance = new Dispatcher();
		}
		return instance;
		
		
	}

	public void dispatchCall() throws InterruptedException {
		if (operators.get() > 0) {
			operators.decrementAndGet();
			attendCall(operators);
		} else if (supervisors.get() > 0) {
			supervisors.decrementAndGet();
			attendCall(supervisors);
		} else if (directors.get() > 0) {
			directors.decrementAndGet();
			attendCall(directors);
		}
		else{
			System.out.println("No se pudo atender");
		}
	}

	private void attendCall(AtomicInteger employees) throws InterruptedException {
		
		executor.execute(() -> {
			TakeCall call = new TakeCallImpl();
			call.answer();
			System.out.println("Hello World" + employees.get());
			employees.getAndIncrement();
			System.out.println("Hello World" + employees.get());
		});

	}
	
	public void finalize() throws InterruptedException{
		executor.shutdown();
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
	}

}
