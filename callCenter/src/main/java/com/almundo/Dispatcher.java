package com.almundo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.almundo.call.TakeCall;
import com.almundo.call.TakeCallImpl;
import com.almundo.exception.NoAvailableEmployeeException;
import com.almundo.model.Employee;

/**
 * 
 * @author dmarques
 *
 *         Esta clase se encarga de despachar las llamadas del callcenter
 * 
 *
 */
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

	/**
	 * Singleton Metodo para obtener la instancia del singleton
	 * 
	 * @return Dispatcher
	 */
	public static synchronized Dispatcher getInstance() {
		if (instance == null) {
			instance = new Dispatcher();
		}
		return instance;

	}

	/**
	 * 
	 * Metodo que asigna un empleado y realiza la llamada
	 * 
	 * @throws NoAvailableEmployeeException
	 */
	public void dispatchCall() throws NoAvailableEmployeeException {
		if (!operatorsList.isEmpty()) {
			attendCall(operatorsList);
		} else if (!supervisorsList.isEmpty()) {
			attendCall(supervisorsList);
		} else if (!directorsList.isEmpty()) {
			attendCall(directorsList);
		} else {
			throw new NoAvailableEmployeeException();
		}
	}

	private void attendCall(List<Employee> employees) {
		Employee employee = employees.remove(0);

		executor.execute(() -> {
			TakeCall call = new TakeCallImpl();
			call.answer(employee);
			employees.add(employee);
		});

	}

	/**
	 * Metodo para finalizar los threads de la clase
	 */
	public void finalize() {
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para volver a inicializar los metodos en caso de que se hayan
	 * finalizado
	 */
	public void initialize() {
		int numberOfThreads = operatorsList.size() + supervisorsList.size() + directorsList.size();
		executor = Executors.newFixedThreadPool(numberOfThreads != 0 ? numberOfThreads : 10);
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
