package com.almundo;

import java.util.ArrayList;
import java.util.List;

import com.almundo.model.Employee;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	List<Employee> opEmployees = new ArrayList<>();
    	opEmployees.add(new Employee());
    	opEmployees.add(new Employee());
    	opEmployees.add(new Employee());
    	opEmployees.add(new Employee());
    	opEmployees.add(new Employee());
        Dispatcher d = Dispatcher.getInstance(opEmployees, new ArrayList<>(), new ArrayList<>());
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
        d.finalize();
    }
}
