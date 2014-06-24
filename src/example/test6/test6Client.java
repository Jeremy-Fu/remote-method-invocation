package example.test6;

import example.sayhello.SayHelloInterface;
import registry.LocateRegistry440;
import registry.Registry440;

/**
 * test6: tests unchecked (runtime) exception during remote method invocation
 * 
 * Client: passes null as parameter and the method will throw a runtime
 * exception
 *
 */
public class test6Client {
	public static void main(String[] args) {
		Registry440 registry = LocateRegistry440.getRegistry("128.237.220.250", 1099);
		SayHelloInterface sayHello = null;
		try {
			sayHello = (SayHelloInterface) registry.lookup("SayHelloOnServerRegistry");
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* The method will throw a nullpointer exception */
		sayHello.resetPerson(null);
	}
}
