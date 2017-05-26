package com.almundo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Dispatcher {

	private AtomicInteger operators = new AtomicInteger(3);

	private AtomicInteger supervisors = new AtomicInteger(1);

	private AtomicInteger directors = new AtomicInteger(1);

	public void dispatchCall() {
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
	}

	private void attendCall(AtomicInteger employees) {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Hello World" + employees.get());
			employees.getAndIncrement();
			System.out.println("Hello World" + employees.get());

		});
		// Runnable task = () -> {
		// String threadName = Thread.currentThread().getName();
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// // // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println("Hello " + threadName);
		// operators.getAndIncrement();
		// };
		//
		// task.run();
		//
		// Thread thread = new Thread(task);
		// thread.start();

		// System.out.println("Done!");
		// ExecutorService executor = Executors.newFixedThreadPool(5);
		// Runnable worker = new WorkerThread(operators);
		// executor.execute(worker);
		// executor.shutdown();

	}

}
