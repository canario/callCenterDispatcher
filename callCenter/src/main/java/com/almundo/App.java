package com.almundo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        Dispatcher d = Dispatcher.getInstance();
        d.dispatchCall();
        d.dispatchCall();
        d.dispatchCall();
        d.dispatchCall();
        d.dispatchCall();
        d.dispatchCall();
        d.dispatchCall();
        d.dispatchCall();
        d.dispatchCall();
        d.dispatchCall();
        d.finalize();
    }
}
