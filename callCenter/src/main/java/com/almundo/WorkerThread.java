package com.almundo;

import java.util.concurrent.atomic.AtomicInteger;

public class WorkerThread implements Runnable {

	private String command;

	AtomicInteger operators;

	public WorkerThread(String s) {
		this.command = s;
	}

	public WorkerThread(AtomicInteger operators) {
		this.operators = operators;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Start. Command = " + operators.get());
		processCommand();
		System.out.println(Thread.currentThread().getName() + " End." + operators.get());
	}

	private void processCommand() {
		try {
			Thread.sleep(5000);
			operators.getAndIncrement();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return this.command;
	}
}
