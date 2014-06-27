package test;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.person.Person;
import example.person.PersonInterface;

public class testPersonServer {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage:\t<service name>");
		}
		
		try {
			String name = args[0];
			Person person = new Person();
			PersonInterface personStub = null;
			personStub = (PersonInterface) UnicastRemoteObject440.exportObject(person, 0);
			Registry440 registryStub = LocateRegistry440.getRegistry("localhost", 1099);
			registryStub.rebind(name, personStub);
			System.out.println(name + " bound to registry");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
