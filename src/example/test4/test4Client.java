package example.test4;

import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.person.Person;
import example.person.PersonInterface;
import example.sayhello.SayHelloInterface;

/**
 * test4: tests two cases: a.the passed in parameter in the remote method is an
 * unexported remote object; b.the passed in parameter is an exported remote
 * object.
 * 
 *  Client: it creates a remote object locally, passed the unexported person
 *  object as a parameter in SayHello stub's resetPerson() method, the 
 *  unexported remote object is passed by value then, the change made to it 
 *  through remote method invocation cannot be seen on client side.
 *  
 *  After the person object is exported, the resetPerson() method passes the
 *  object by reference (its stub), the change made to it through remote 
 *  method invocation can be seen then on client side.
 * 
 *
 */
public class test4Client {
	public static void main(String[] args) {
		PersonInterface person = new Person();
		person.setName("Chris");
		person.setAge(25);
		
		Registry440 registryRemote = LocateRegistry440.getRegistry("128.237.220.250", 1099);
		SayHelloInterface sayHello = null;
		try {
			sayHello = (SayHelloInterface) registryRemote.lookup("SayHelloOnServerRegistry");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		/* parameter is an unexported remote object, which will be passed by value */
		String newName = sayHello.resetPerson(person);
		System.out.println("Person's name on server side: " + newName);
		System.out.println("Person's name on client side: " + person.getName());
		
		System.out.println("Now exported person object");
		PersonInterface personStub = null;
		try {
			personStub = (PersonInterface) UnicastRemoteObject440.exportObject(person, 0);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		Registry440 registry = LocateRegistry440.getRegistry("localhost", 1099);
		registry.rebind("PersonOnClientRegistry", personStub);
		
		/* parameter is an exported remote object, which will be passed by reference (its stub) */
		newName = sayHello.resetPerson(person);
		System.out.println("Person's name on server side: " + newName);
		System.out.println("Person's name on client side: " + person.getName());
		
		
	}
}