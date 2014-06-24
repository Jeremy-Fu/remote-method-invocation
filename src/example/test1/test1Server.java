package example.test1;

import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.person.Person;
import example.person.PersonInterface;

/**
<<<<<<< HEAD
 * test1: This program tests the case when parameter and return value of 
 * remote method are both primitive types. 
=======
 * test1: This program tests when parameter and return value of remote method
 * are both primitive types. 
 * 
 * Server: It instantiates a Person object, exports it and rebinds it with
 * the registry. Due to the object is referenced the by proxy dispatcher 
 * thread, the person object won't be collected by garbage collection.
>>>>>>> 23229162f7c326a7924f854c45fe9a0faf85c40e
 * 
 */
public class test1Server {
	public static void main(String[] args) {
		Person person = new Person();
		PersonInterface personStub = null;
		try {
			personStub = (PersonInterface) UnicastRemoteObject440.exportObject(person, 0);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if (personStub == null) {
			System.err.println("Export remote object failed. Now exit the process.");
			System.exit(-1);
		}
		Registry440 registryStub = LocateRegistry440.getRegistry("localhost", 1099);
		registryStub.rebind("PersonOnServerRegistry", personStub);
		System.out.println("test1Server.main():\tPerson has been bound to registry");
	}
	
	
}
