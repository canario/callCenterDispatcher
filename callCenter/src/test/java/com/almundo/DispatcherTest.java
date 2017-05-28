package com.almundo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Test;

import com.almundo.exception.NoAvailableEmployeeException;
import com.almundo.model.Employee;
import com.almundo.model.EmployeeType;

public class DispatcherTest {

	private Dispatcher dispatcher;

	@Test(expected = NoAvailableEmployeeException.class)
	public void testDispatchCallWithNoEmployees() throws NoAvailableEmployeeException {
		dispatcher = Dispatcher.getInstance();
		dispatcher.initialize();
		dispatcher.clearEmployees();

		dispatcher.dispatchCall();
		dispatcher.finalize();

	}

	@Test(expected = NoAvailableEmployeeException.class)
	public void testDispatchCallWithOnlyOneEmployees() throws NoAvailableEmployeeException {
		dispatcher = Dispatcher.getInstance();
		dispatcher.initialize();
		List<Employee> opEmployees = new ArrayList<>();

		opEmployees.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		dispatcher.clearEmployees();
		dispatcher.setOperatorsList(opEmployees);
		dispatcher.dispatchCall();
		dispatcher.dispatchCall();
		dispatcher.finalize();
	}

	@Test
	public void testDispatchCallWithOperatorsAndSupervidor() throws NoAvailableEmployeeException {
		dispatcher = Dispatcher.getInstance();
		dispatcher.initialize();
		List<Employee> operators = new ArrayList<>();

		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));

		List<Employee> supervisors = new ArrayList<>();

		supervisors.add(new Employee(EmployeeType.SUPERVISOR, "John", "Doe"));

		dispatcher.clearEmployees();
		dispatcher.setOperatorsList(operators);
		dispatcher.setSupervisorsList(supervisors);

		dispatcher.dispatchCall();
		dispatcher.dispatchCall();
		dispatcher.finalize();
	}

	@Test
	public void testDispatchCallWithEmployees() throws NoAvailableEmployeeException {
		dispatcher = Dispatcher.getInstance();
		dispatcher.initialize();
		ExecutorService executor;
		List<Employee> operators = new ArrayList<>();

		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));

		List<Employee> supervisors = new ArrayList<>();

		supervisors.add(new Employee(EmployeeType.SUPERVISOR, "John", "Doe"));

		dispatcher.clearEmployees();
		dispatcher.setOperatorsList(operators);
		dispatcher.setSupervisorsList(supervisors);

		executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {

			executor.execute(() -> {
				try {
					dispatcher.dispatchCall();
				} catch (NoAvailableEmployeeException e) {
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

		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));
		operators.add(new Employee(EmployeeType.OPERATOR, "John", "Doe"));

		List<Employee> supervisors = new ArrayList<>();

		supervisors.add(new Employee(EmployeeType.DIRECTOR, "John", "Doe"));

		dispatcher.clearEmployees();
		dispatcher.setOperatorsList(operators);
		dispatcher.setSupervisorsList(supervisors);

		executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {

			executor.execute(() -> {
				try {
					dispatcher.dispatchCall();
				} catch (NoAvailableEmployeeException e) {
					// TODO Auto-generated catch block
					Assert.assertTrue(true);
				}
			});

		}
		dispatcher.finalize();
	}

}
