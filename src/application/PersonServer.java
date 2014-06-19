package application;

import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.RemoteObjectRef;
import ror.UnicastRemoteObject440;
import example2.Person;
import example2.PersonInterface;

public class PersonServer {
	public static void main(String[] args) throws UnknownHostException {
		Person person = new Person();
		PersonInterface stub = (PersonInterface) UnicastRemoteObject440.exportObject(person, 0);
		Registry440 registry = LocateRegistry440.getRegistry("localhost", 1099);
		registry.rebind("Person", person);
		System.out.println("bind person");
	}
}
