package example.test2;

import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.person.Person;
import example.person.PersonInterface;

/**
 * test2: This program tests when parameter and return value of remote method
 * are both non-remote serializable object. 
 * 
 * It looks up the stub of a person object named 'PersonOnServerRegistry', 
 * sets the name of the remote object to the String parameter passed in and 
 * finally gets the name of the remote object again after the name is set. 
 * Both of the setter and getter method of the instance variable NAME are 
 * remote method invocations
 * 
 */
public class test2Server {
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
