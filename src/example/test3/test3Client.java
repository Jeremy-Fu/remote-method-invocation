package example.test3;

import example.person.PersonInterface;
import example.sayhello.SayHelloInterface;
import registry.LocateRegistry440;
import registry.Registry440;

/**
 * test3: tests the case in which the remote method's return value is an
 * exported remote object (the stub of the object is returned instead).
 * 
 * Client: It gets a stub of SayHelloInterface object named 'SayHelloOnServerRegistry',
 * creates a person object (remote object) and returns a stub of person object
 */
public class test3Client {
	public static void main(String[] args) {
		Registry440 registry = LocateRegistry440.getRegistry("128.237.220.250", 1099);
		SayHelloInterface sayHello = null;
		try {
			sayHello = (SayHelloInterface) registry.lookup("SayHelloOnServerRegistry");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PersonInterface person = sayHello.createPerson();
		System.out.println("Class Name of person: " + person.getClass().getName());
		
		person.setName("Andy");
		try {
			System.out.println(sayHello.sayHello(person));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
