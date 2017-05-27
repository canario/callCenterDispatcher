package com.almundo.model;

public class Employee {
	
	private EmployeeType position;
	
	private String name;
	
	private String lastname;

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
