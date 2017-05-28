package com.almundo.model;

public class Employee {
	
	private EmployeeType position;
	
	private String name;
	
	private String lastname;

	public Employee(EmployeeType position, String name, String lastname) {
		super();
		this.position = position;
		this.name = name;
		this.lastname = lastname;
	}

	public EmployeeType getPosition() {
		return position;
	}

	public void setPosition(EmployeeType position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
