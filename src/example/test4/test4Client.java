package example.test4;

import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.person.Person;
import example.person.PersonInterface;

public class test4Client {
	public static void main(String[] args) {
		PersonInterface person = new Person();
		System.out.println("Class Name of person: " + person.getClass().getName());
		
		PersonInterface personStub = null;
		try {
			personStub = (PersonInterface) UnicastRemoteObject440.exportObject(person, 0);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("Class Name of personStub: " + personStub.getClass().getName());
		personStub.setName("Chris");
		personStub.setAge(25);
		Registry440 registryRemote = LocateRegistry440.getRegistry("128.237.220.250", 1099);
		
		Registry440 registry = LocateRegistry440.getRegistry("localhost", 1099);
		registry.rebind("PersonOnClientRegistry", personStub);
		
	}
}
