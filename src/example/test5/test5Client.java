package example.test5;

import example.person.PersonInterface;
import example.sayhello.SayHelloInterface;
import exception.RemoteException440;
import registry.LocateRegistry440;
import registry.Registry440;

/**
 * test5: tests checked exception during remote method invocation
 * 
 * Client: creates a remote person object by calling .createPerson() method,
 * sets the name  and calls .sayHello(person) object, which will
 * throw an exception when the name of the person is set to 'Kim'
 */
public class test5Client {
	public static void main(String[] args) {
		Registry440 registry = null;
		try {
			registry = LocateRegistry440.getRegistry("128.237.220.250", 1099);
		} catch (RemoteException440 e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SayHelloInterface sayHello = null;
		try {
			sayHello = (SayHelloInterface) registry.lookup("SayHelloOnServerRegistry");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PersonInterface person = sayHello.createPerson();
		
		/* Set name to 'Andy' */
		person.setName("Andy");
		try {
			System.out.println(sayHello.sayHello(person));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* Set name to 'Kim' */
		person.setName("Kim");
		
		/* 
		 * The remote method sayHello(person) throws an checked exception
		 * when the name of the person object is set to 'Kim'
		 */
		try {
			System.out.println(sayHello.sayHello(person));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
