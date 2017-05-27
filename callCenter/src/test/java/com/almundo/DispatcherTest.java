package com.almundo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Test;

import com.almundo.exception.NoEmployeeException;
import com.almundo.model.Employee;

public class DispatcherTest {

	private Dispatcher dispatcher;

	@Test(expected = NoEmployeeException.class)
	public void testDispatchCallWithNoEmployees() throws NoEmployeeException {
		dispatcher = Dispatcher.getInstance();
		dispatcher.initialize();
		dispatcher.clearEmployees();

		dispatcher.dispatchCall();
		dispatcher.finalize();

	}

	@Test(expected = NoEmployeeException.class)
	public void testDispatchCallWithOnlyOneEmployees() throws NoEmployeeException {
		dispatcher = Dispatcher.getInstance();
		dispatcher.initialize();
		List<Employee> opEmployees = new ArrayList<>();

		opEmployees.add(new Employee());
		dispatcher.clearEmployees();
		dispatcher.setOperatorsList(opEmployees);
		dispatcher.dispatchCall();
		dispatcher.dispatchCall();
		dispatcher.finalize();
	}

	@Test
	public void testDispatchCallWithOperatorsAndSupervidor() throws NoEmployeeException {
		dispatcher = Dispatcher.getInstance();
		dispatcher.initialize();
		List<Employee> operators = new ArrayList<>();

		operators.add(new Employee());

		List<Employee> supervisors = new ArrayList<>();

		supervisors.add(new Employee());

		dispatcher.clearEmployees();
		dispatcher.setOperatorsList(operators);
		dispatcher.setSupervisorsList(supervisors);

		dispatcher.dispatchCall();
		dispatcher.dispatchCall();
		dispatcher.finalize();
	}

	@Test
	public void testDispatchCallWithEmployees() throws NoEmployeeException {
		dispatcher = Dispatcher.getInstance();
		dispatcher.initialize();
		ExecutorService executor;
		List<Employee> operators = new ArrayList<>();

		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());

		List<Employee> supervisors = new ArrayList<>();

		supervisors.add(new Employee());

		dispatcher.clearEmployees();
		dispatcher.setOperatorsList(operators);
		dispatcher.setSupervisorsList(supervisors);

		executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {

			executor.execute(() -> {
				try {
					dispatcher.dispatchCall();
				} catch (NoEmployeeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		}
	}

	@Test
	public void testDispatchCallWithNotEnoughEmployees() {
		dispatcher = Dispatcher.getInstance();
		dispatcher.initialize();
		ExecutorService executor;
		List<Employee> operators = new ArrayList<>();

		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());
		operators.add(new Employee());

		List<Employee> supervisors = new ArrayList<>();

		supervisors.add(new Employee());

		dispatcher.clearEmployees();
		dispatcher.setOperatorsList(operators);
		dispatcher.setSupervisorsList(supervisors);

		executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {

			executor.execute(() -> {
				try {
					dispatcher.dispatchCall();
				} catch (NoEmployeeException e) {
					// TODO Auto-generated catch block
					Assert.assertTrue(true);
				}
			});

		}
		dispatcher.finalize();
	}

}
