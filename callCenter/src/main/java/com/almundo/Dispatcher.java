package com.almundo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.almundo.call.TakeCall;
import com.almundo.call.TakeCallImpl;
import com.almundo.exception.NoEmployeeException;
import com.almundo.model.Employee;

public class Dispatcher {

	private List<Employee> operatorsList;

	private List<Employee> supervisorsList;

	private List<Employee> directorsList;

	private static Dispatcher instance;

	private ExecutorService executor;

	private Dispatcher() {
		executor = Executors.newFixedThreadPool(10);
		operatorsList = Collections.synchronizedList(new ArrayList<>());
		supervisorsList = Collections.synchronizedList(new ArrayList<>());
		directorsList = Collections.synchronizedList(new ArrayList<>());
	}

	public static Dispatcher getInstance() {
		if (instance == null) {
			instance = new Dispatcher();
		}
		return instance;

	}

	public void dispatchCall() throws NoEmployeeException {
		if (operatorsList.size() > 0) {
			attendCall(operatorsList.remove(0), operatorsList);
		} else if (supervisorsList.size() > 0) {
			attendCall(supervisorsList.remove(0), supervisorsList);
		} else if (directorsList.size() > 0) {
			attendCall(directorsList.get(0), directorsList);
		} else {
			throw new NoEmployeeException();
		}
	}

	private void attendCall(Employee employee, List<Employee> employees) {

		executor.execute(() -> {
			TakeCall call = new TakeCallImpl();
			call.answer(employee);
			employees.add(employee);
		});

	}

	public void finalize() {
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void initialize(){
		executor = Executors.newFixedThreadPool(10);
	}

	public List<Employee> getOperatorsList() {
		return operatorsList;
	}

	public void setOperatorsList(List<Employee> operatorsList) {
		this.operatorsList = operatorsList;
	}

	public void addOperator(Employee operator) {
		this.operatorsList.add(operator);
	}

	public List<Employee> getSupervisorsList() {
		return supervisorsList;
	}

	public void setSupervisorsList(List<Employee> supervisorsList) {
		this.supervisorsList = supervisorsList;
	}

	public void addSupervisor(Employee supervisor) {
		this.supervisorsList.add(supervisor);
	}

	public List<Employee> getDirectorsList() {
		return directorsList;
	}

	public void setDirectorsList(List<Employee> directorsList) {
		this.directorsList = directorsList;
	}

	public void addDirector(Employee director) {
		this.directorsList.add(director);
	}

	protected void clearEmployees() {
		operatorsList = Collections.synchronizedList(new ArrayList<>());
		supervisorsList = Collections.synchronizedList(new ArrayList<>());
		directorsList = Collections.synchronizedList(new ArrayList<>());
		
	}

}
