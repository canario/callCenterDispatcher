package com.almundo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.almundo.call.TakeCall;
import com.almundo.call.TakeCallImpl;
import com.almundo.model.Employee;

public class Dispatcher {

	private List<Employee> operatorsList = Collections.synchronizedList(new ArrayList<>());

	private List<Employee> supervisorsList = Collections.synchronizedList(new ArrayList<>());;

	private List<Employee> directorsList = Collections.synchronizedList(new ArrayList<>());;

	private static Dispatcher instance;

	private ExecutorService executor;

	private Dispatcher() {
		executor = Executors.newFixedThreadPool(10);
	}

	public Dispatcher(List<Employee> operators, List<Employee> supervisors, List<Employee> directors) {
		super();
		executor = Executors.newFixedThreadPool(10);
		operatorsList.addAll(operators);
		supervisorsList.addAll(supervisors);
		directorsList.addAll(directors);
	}

	public static Dispatcher getInstance() {
		if (instance == null) {
			instance = new Dispatcher();
		}
		return instance;

	}

	public static Dispatcher getInstance(List<Employee> operators, List<Employee> supervisors,
			List<Employee> directors) {
		if (instance == null) {
			instance = new Dispatcher(operators, supervisors, directors);
		}
		return instance;

	}

	public void dispatchCall() {
		if (operatorsList.size() > 0) {
			attendCall(operatorsList.remove(0), operatorsList);
		} else if (supervisorsList.size() > 0) {
			attendCall(supervisorsList.remove(0), supervisorsList);
		} else if (directorsList.size() > 0) {
			attendCall(directorsList.get(0), directorsList);
		} else {
			System.out.println("No se pudo atender");
		}
	}

	private void attendCall(Employee employee, List<Employee> employees) {

		executor.execute(() -> {
			TakeCall call = new TakeCallImpl();
			call.answer(employee);
			System.out.println("Hello World" + employee);
			// employee.getAndIncrement();
			employees.add(employee);
			System.out.println("Hello World" + employee);
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

	public List<Employee> getOperatorsList() {
		return operatorsList;
	}

	public void setOperatorsList(List<Employee> operatorsList) {
		this.operatorsList = operatorsList;
	}
	
	public void addOperator(Employee operator){
		this.operatorsList.add(operator);
	}

	public List<Employee> getSupervisorsList() {
		return supervisorsList;
	}

	public void setSupervisorsList(List<Employee> supervisorsList) {
		this.supervisorsList = supervisorsList;
	}
	
	public void addSupervisor(Employee supervisor){
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

	
}
