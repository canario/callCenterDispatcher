package com.almundo;

import java.util.ArrayList;
import java.util.List;

import com.almundo.exception.NoEmployeeException;
import com.almundo.model.Employee;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) 
    {
    	List<Employee> opEmployees = new ArrayList<>();
    	opEmployees.add(new Employee());
    	opEmployees.add(new Employee());
    	opEmployees.add(new Employee());
    	opEmployees.add(new Employee());
    	opEmployees.add(new Employee());
        Dispatcher d = Dispatcher.getInstance();
        try {
			d.dispatchCall();
	        d.dispatchCall();
	        d.dispatchCall();
	        d.dispatchCall();
	        d.dispatchCall();
	        d.dispatchCall();
	        d.dispatchCall();
	        d.dispatchCall();
	        d.dispatchCall();
	        d.addSupervisor(new Employee());
	        d.dispatchCall();

		} catch (NoEmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        d.finalize();
    }
}
